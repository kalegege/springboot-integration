package com.wasu.springboot.integration.lock;

public interface LockService {
    void releaseLock(String resourceName,String randomToken);

    boolean tryLock(String resourceName,String randomToken,LockMode lockMode,int expireSeconds);
}
