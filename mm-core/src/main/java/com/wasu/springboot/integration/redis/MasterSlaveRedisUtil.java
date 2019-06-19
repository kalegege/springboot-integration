package com.wasu.springboot.integration.redis;

import com.wasu.springboot.integration.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("redisService")
public class MasterSlaveRedisUtil implements RedisUtil {

    private final static Logger LOGGER= LoggerFactory.getLogger(MasterSlaveRedisUtil.class);

    private String prefix;
    private int maxTotal;
    private int maxIdle;
    private long maxWaitMillis;
    private String ip;
    private String password;

    private MasterSlaveHolder holder;

    public void init(){
        if(StringUtils.isBlank(ip)){
            LOGGER.error("redis init error:ip is null");
            return ;
        }

        try{
            String[] ips=ip.split(",");

            holder=new MasterSlaveHolder();
            holder.setMaster(resolveHost(ips[0]),resolvePort(ips[0]),password,maxTotal,maxIdle,maxWaitMillis);

            if(ips.length > 1){
                for(int i=1;i< ips.length;i++){
                    holder.addSlave(resolveHost(ips[i]),resolvePort(ips[i]),password,maxTotal,maxIdle,maxWaitMillis);
                }
            }
            holder.openScheduledTasks();
        }catch(Exception e){
            LOGGER.error("redis init error:"+ip);
        }
    }

    private int resolvePort(String ips) {
        String[] uri=ips.split(":");
        if(uri != null && uri.length > 0){
            return Integer.valueOf(uri[0]);
        }
        return 0;
    }

    private String resolveHost(String ips) {
        String[] uri=ips.split(":");
        if(uri != null && uri.length > 0){
            return uri[0];
        }
        return null;
    }

    @Override
    public String get(String key) {
        key = prefix+key;
        try(Jedis jedis=holder.getReadResource()){
            return jedis.get(key);
        }
    }

    @Override
    public void set(String key, String value) {
        key = prefix+key;
        try(Jedis jedis=holder.getWriteResource()){
             jedis.set(key,value);
        }
    }

    @Override
    public void setEx(String key, String value, Integer timeToIdleSeconds) {
        key = prefix+key;
        try(Jedis jedis=holder.getWriteResource()){
            jedis.setex(key,timeToIdleSeconds,value);
        }
    }

    @Override
    public byte[] getBytes(String key) {
        key = prefix+key;
        try(Jedis jedis=holder.getReadResource()){
            return jedis.get(key.getBytes());
        }
    }

    @Override
    public void setBytes(String key, byte[] value) {
        key = prefix+key;
        try(Jedis jedis=holder.getWriteResource()){
            jedis.set(key.getBytes(),value);
        }
    }

    @Override
    public void setMap(String key, String field, String value) {

    }

    @Override
    public String getMap(String key, String field) {
        return null;
    }

    @Override
    public byte[] getMapBytes(String key, String field) {
        return new byte[0];
    }

    @Override
    public void setMapBytes(String key, String field, byte[] value) {

    }

    @Override
    public void removeMapField(String key, String... fields) {

    }

    @Override
    public void expire(String key, Integer timeToIdleSeconds) {
        key = prefix+key;
        try(Jedis jedis=holder.getWriteResource()){
            jedis.expire(key,timeToIdleSeconds);
        }
    }

    @Override
    public void removeKey(String key) {
        key = prefix+key;
        try(Jedis jedis=holder.getWriteResource()){
             jedis.del(key);
        }
    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public List<String> getKeys(String pattern) {
        return null;
    }

    @Override
    public Long setNx(String key, String value) {
        key = prefix+key;
        try(Jedis jedis=holder.getWriteResource()){
            return jedis.setnx(key,value);
        }
    }

    @Override
    public Set<String> getMapKeys(String key) {
        return null;
    }

    @Override
    public List<String> hmget(String key, String... field) {
        return null;
    }

    @Override
    public List<String> getMapValue(String key, String... field) {
        return null;
    }

    @Override
    public void setMap(String key, Map<String, String> hash) {

    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub) {

    }

    @Override
    public void subscribe(JedisPubSub listener, String... channles) {

    }

    @Override
    public String info() {
        try(Jedis jedis=holder.getReadResource()){
            return jedis.info();
        }
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        for(int i=0;i<keys.size();i++){
            keys.set(i,prefix+keys.get(i));
        }
        try(Jedis jedis=holder.getReadResource()){
            return jedis.eval(script,keys,args);
        }
    }
}
