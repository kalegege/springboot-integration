package com.wasu.springboot.integration.redis;

public interface MasterSlaveOperationCallback {
    void delistMaster(String name);

    void enlistMaster(String name);

    void delistSlave(String name);

    void enlistSlave(String name);
}
