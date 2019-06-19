package com.wasu.springboot.integration.redis.impl;

import com.wasu.springboot.integration.redis.MasterSlavePoolEntry;
import com.wasu.springboot.integration.redis.MasterSlavePoolHealth;
import com.wasu.springboot.integration.redis.MasterSlaveState;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MasterSlavePoolEntryImpl implements MasterSlavePoolEntry{

    private final static int DEFAULT_TIMEOUT=15000;

    private String host;

    private int port;

    private String password;

    private int maxTotal;

    private int maxIdle;

    private long maxWaitMillis;

    private JedisPool pool;

    private MasterSlaveState state;

    private MasterSlavePoolHealth health;

    public MasterSlavePoolEntryImpl(MasterSlaveState state,String host, int port, String password, int maxTotal, int maxIdle, long maxWaitMillis ) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.maxWaitMillis = maxWaitMillis;
        this.state = state;
    }

    private void initPool(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(false);
        if(password != null){
            pool = new JedisPool(config,host,port,DEFAULT_TIMEOUT,password);
        }else{
            pool = new JedisPool(config,host,port,DEFAULT_TIMEOUT);
        }
    }
    @Override
    public String getName() {
        return this.host+":"+this.port;
    }

    @Override
    public MasterSlaveState getState() {
        return state;
    }

    @Override
    public void setState(MasterSlaveState state) {
        this.state=state;
    }

    @Override
    public Jedis getResource() {
        return pool.getResource();
    }

    @Override
    public MasterSlavePoolHealth getHealth() {
        return health;
    }

    public void setHealth(MasterSlavePoolHealth health) {
        this.health = health;
    }
}
