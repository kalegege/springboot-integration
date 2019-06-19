package com.wasu.springboot.integration.redis;

public interface MasterSlaveState {
    MasterSlaveStateEnum getState();

    void doMaster(MasterSlavePoolEntry current,MasterSlaveOperationCallback callback);

    void doSlave(MasterSlavePoolEntry current,MasterSlaveOperationCallback callback);

    void doInvalid(MasterSlavePoolEntry current,MasterSlaveOperationCallback callback);

}
