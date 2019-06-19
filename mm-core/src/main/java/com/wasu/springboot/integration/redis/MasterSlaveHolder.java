package com.wasu.springboot.integration.redis;

import com.wasu.springboot.integration.redis.impl.MasterSlavePoolEntryImpl;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MasterSlaveHolder implements MasterSlaveOperationCallback{
    private final static Logger LOGGER= LoggerFactory.getLogger(MasterSlaveHolder.class);

    private final static int DEFAULT_LOCK_WAIT_SECOND=3;

    /**
     * 可用的主redis
     */
    private MasterSlavePoolEntry master;
    private ReentrantReadWriteLock masterLock=new ReentrantReadWriteLock();

    /**
     * 可用的从redis
     */
    private List<MasterSlavePoolEntry> listSlavePoolEntry = new ArrayList<>();
    private ReentrantReadWriteLock slaveLock=new ReentrantReadWriteLock();

    /**
     * 所有的redis
     */
    private Map<String,MasterSlavePoolEntry> allMasterSlavePoolEntryMap = new ConcurrentHashMap<>();

    /**
     * redis 定时任务
     */
    private ScheduledExecutorService scheduler;

    private Executor executor= Executors.newCachedThreadPool();

    public MasterSlaveHolder() {
        scheduler=Executors.newSingleThreadScheduledExecutor();
    }

    public void openScheduledTasks(){
        scheduler.scheduleAtFixedRate(new MasterSlaveHolderRefreshTask(executor,this),
                MasterSlaveHolderRefreshTask.DELAY_SECONDS,MasterSlaveHolderRefreshTask.PERIOD_SECONDS, TimeUnit.SECONDS);
    }

    public void setMaster(String host,int port,String password,int maxTotal,int maxIdle,long maxWaitMillis){
        MasterSlavePoolEntry newObject=new MasterSlavePoolEntryImpl(MasterSlaveStateContext.STATE_MASTER,host,port,password,maxTotal,maxIdle,maxWaitMillis);
        this.master=newObject;
        allMasterSlavePoolEntryMap.put(newObject.getName(),newObject);
    }

    public void addSlave(String host,int port,String password,int maxTotal,int maxIdle,long maxWaitMillis){
        MasterSlavePoolEntry newObject=new MasterSlavePoolEntryImpl(MasterSlaveStateContext.STATE_SLAVE,host,port,password,maxTotal,maxIdle,maxWaitMillis);
        this.master=newObject;
        allMasterSlavePoolEntryMap.put(newObject.getName(),newObject);
    }

    public Jedis getWriteResource(){
        ReentrantReadWriteLock.ReadLock lock=masterLock.readLock();
        if(lock.tryLock()){
            try{
                return master.getResource();
            }finally {
                lock.unlock();
            }
        }
        return  null;
    }

    public Jedis getReadResource(){
        ReentrantReadWriteLock.ReadLock lock=slaveLock.readLock();
        if(lock.tryLock()){
            try{
                int size=listSlavePoolEntry.size();
                if(size == 0){
                    return getWriteResource();
                }
                if(size == 1){
                    return listSlavePoolEntry.get(0).getResource();
                }
                int index= RandomUtils.nextInt(0,size - 1);
                return listSlavePoolEntry.get(index).getResource();
            }finally {
                lock.unlock();
            }
        }
        return null;
    }
    @Override
    public void delistMaster(String name) {
        if(null == master || null == name){
            return;
        }

        ReentrantReadWriteLock.WriteLock lock=masterLock.writeLock();
        try{
            if(lock.tryLock(DEFAULT_LOCK_WAIT_SECOND,TimeUnit.SECONDS)){
                try{
                    if(master.getName().equals(name)){
                        loggerSwap(master.getName(),master.getState().getState(),MasterSlaveStateContext.STATE_INVALID.getState());
                        master.setState(MasterSlaveStateContext.STATE_INVALID);
                        master=null;
                        return;
                    }
                }finally {
                    lock.unlock();
                }
            }
        }catch (InterruptedException e){
            LOGGER.error(e.getMessage(),e);
        }
        return;
    }

    private void loggerSwap(String name, MasterSlaveStateEnum before, MasterSlaveStateEnum after) {
        StringBuffer sb=new StringBuffer();
        sb.append(name);
        sb.append(":");
        sb.append(MasterSlaveStateEnum.getStateName(before));
        sb.append("===>");
        sb.append(MasterSlaveStateEnum.getStateName(after));
        LOGGER.info(sb.toString());
    }

    @Override
    public void enlistMaster(String name) {
        if(null == name){
            return;
        }

        ReentrantReadWriteLock.WriteLock lock=masterLock.writeLock();
        try{
            if(lock.tryLock(DEFAULT_LOCK_WAIT_SECOND,TimeUnit.SECONDS)){
                try {
                    if(null != master && !master.getName().equals(name)){
                        loggerSwap(master.getName(),master.getState().getState(),MasterSlaveStateContext.STATE_INVALID.getState());
                        master.setState(MasterSlaveStateContext.STATE_INVALID);
                    }
                    master=allMasterSlavePoolEntryMap.get(name);
                    loggerSwap(master.getName(),master.getState().getState(),MasterSlaveStateContext.STATE_INVALID.getState());
                    master.setState(MasterSlaveStateContext.STATE_MASTER);
                }finally {
                    lock.unlock();
                }

            }
        }catch (InterruptedException e){
            LOGGER.error(e.getMessage(),e);
        }
    }

    @Override
    public void delistSlave(String name) {
        if(null == name){
            return;
        }

        ReentrantReadWriteLock.WriteLock lock=slaveLock.writeLock();
        try{
            if(lock.tryLock(DEFAULT_LOCK_WAIT_SECOND,TimeUnit.SECONDS)){
                try {
                    Iterator<MasterSlavePoolEntry> itor=listSlavePoolEntry.iterator();
                    while(itor.hasNext()){
                        MasterSlavePoolEntry slave=itor.next();
                        if(slave.getName().equals(name)){
                            loggerSwap(slave.getName(),slave.getState().getState(),MasterSlaveStateContext.STATE_INVALID.getState());
                            slave.setState(MasterSlaveStateContext.STATE_INVALID);
                            itor.remove();
                        }
                    }
                }finally {
                    lock.unlock();
                }

            }
        }catch (InterruptedException e){
            LOGGER.error(e.getMessage(),e);
        }
    }

    @Override
    public void enlistSlave(String name) {
        if(null == name){
            return;
        }

        ReentrantReadWriteLock.WriteLock lock=slaveLock.writeLock();
        try{
            if(lock.tryLock(DEFAULT_LOCK_WAIT_SECOND,TimeUnit.SECONDS)){
                try {
                    MasterSlavePoolEntry slave=allMasterSlavePoolEntryMap.get(name);
                    loggerSwap(slave.getName(),slave.getState().getState(),MasterSlaveStateContext.STATE_SLAVE.getState());
                    slave.setState(MasterSlaveStateContext.STATE_SLAVE);
                    listSlavePoolEntry.add(slave);
                }finally {
                    lock.unlock();
                }

            }
        }catch (InterruptedException e){
            LOGGER.error(e.getMessage(),e);
        }
    }

    public List<MasterSlavePoolEntry> getListSlavePoolEntry() {
        return listSlavePoolEntry;
    }

    public void setListSlavePoolEntry(List<MasterSlavePoolEntry> listSlavePoolEntry) {
        this.listSlavePoolEntry = listSlavePoolEntry;
    }
}
