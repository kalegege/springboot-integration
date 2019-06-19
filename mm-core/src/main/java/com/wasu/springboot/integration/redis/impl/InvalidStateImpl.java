package com.wasu.springboot.integration.redis.impl;

import com.wasu.springboot.integration.redis.MasterSlaveOperationCallback;
import com.wasu.springboot.integration.redis.MasterSlavePoolEntry;
import com.wasu.springboot.integration.redis.MasterSlaveState;
import com.wasu.springboot.integration.redis.MasterSlaveStateEnum;

public class InvalidStateImpl implements MasterSlaveState {
    @Override
    public MasterSlaveStateEnum getState() {
        return MasterSlaveStateEnum.INVALID;
    }

    @Override
    public void doMaster(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.enlistMaster(current.getName());
    }

    @Override
    public void doSlave(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.enlistSlave(current.getName());
    }

    @Override
    public void doInvalid(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {

    }
}
