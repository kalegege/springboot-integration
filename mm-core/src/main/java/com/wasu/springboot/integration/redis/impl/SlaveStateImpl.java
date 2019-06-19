package com.wasu.springboot.integration.redis.impl;

import com.wasu.springboot.integration.redis.MasterSlaveOperationCallback;
import com.wasu.springboot.integration.redis.MasterSlavePoolEntry;
import com.wasu.springboot.integration.redis.MasterSlaveState;
import com.wasu.springboot.integration.redis.MasterSlaveStateEnum;

public class SlaveStateImpl implements MasterSlaveState {
    @Override
    public MasterSlaveStateEnum getState() {
        return MasterSlaveStateEnum.SLAVE;
    }

    @Override
    public void doMaster(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.delistSlave(current.getName());
        callback.enlistMaster(current.getName());
    }

    @Override
    public void doSlave(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {

    }

    @Override
    public void doInvalid(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.delistSlave(current.getName());
    }
}
