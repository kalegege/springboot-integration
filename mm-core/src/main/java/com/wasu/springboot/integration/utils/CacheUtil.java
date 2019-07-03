package com.wasu.springboot.integration.utils;

public interface CacheUtil {
    public String get(String s);

    void setBytes(String key,byte[] value);

    void expire(String key,int expore);

    void remove(String key);

    byte[] getBytes(String key);
}
