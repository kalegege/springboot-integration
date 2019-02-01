package com.wasu.springboot.integration.redis;

import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisUtil {
    String get(String key);

    void set(String key,String value);

    void setEx(String key,String value,Integer timeToIdleSeconds);

    byte[] getBytes(String key);

    void setBytes(String key,byte[] value);

    void setMap(String key,String field,String value);

    String getMap(String key,String field);

    byte[] getMapBytes(String key,String field);

    void setMapBytes(String key,String field,byte[] value);

    void removeMapField(String key,String... fields);

    void expire(String key,Integer timeToIdleSeconds);

    void removeKey(String key);

    boolean exists(String key);

    List<String> getKeys(String pattern);

    Long setNx(String key,String value);

    Set<String> getMapKeys(String key);

    List<String> hmget(String key,String... field);

    List<String> getMapValue(String key,String... field);

    void setMap(String key,Map<String,String> hash);

    Long incr(String key);

    void psubscribe(JedisPubSub jedisPubSub);

    void subscribe(JedisPubSub listener,String... channles);

    String info();

    Object eval(String script,List<String> keys,List<String> args);
}
