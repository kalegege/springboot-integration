package com.wasu.springboot.integration.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("redisService")
public class MasterSlaveRedisUtil implements RedisUtil {

    private final static Logger LOGGER= LoggerFactory.getLogger(MasterSlaveRedisUtil.class);

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public void setEx(String key, String value, Integer timeToIdleSeconds) {

    }

    @Override
    public byte[] getBytes(String key) {
        return new byte[0];
    }

    @Override
    public void setBytes(String key, byte[] value) {

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

    }

    @Override
    public void removeKey(String key) {

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
        return null;
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
        return null;
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        return null;
    }
}
