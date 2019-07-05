package com.wasu.springboot.integration.utils.redis;

import com.wasu.springboot.integration.redis.RedisUtil;
import com.wasu.springboot.integration.utils.CacheUtil;

public class RediCacheUtil implements CacheUtil {

    private RedisUtil redisUtil;


    public RediCacheUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public String get(String key) {
        return redisUtil.get(key);
    }

    @Override
    public void setBytes(String key, byte[] value) {
        redisUtil.setBytes(key,value);
    }

    @Override
    public void expire(String key, int expire) {
        redisUtil.expire(key,expire);
    }

    @Override
    public void remove(String key) {
        redisUtil.removeKey(key);
    }

    @Override
    public byte[] getBytes(String key) {
        return redisUtil.getBytes(key);
    }
}
