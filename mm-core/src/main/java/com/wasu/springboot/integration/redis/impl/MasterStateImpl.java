package com.wasu.springboot.integration.redis.impl;

import com.wasu.springboot.integration.redis.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MasterStateImpl implements MasterSlaveState{
    private final static Logger LOGGER= LoggerFactory.getLogger(MasterStateImpl.class);

    @Override
    public MasterSlaveStateEnum getState() {
        return MasterSlaveStateEnum.MASTER;
    }

    @Override
    public void doMaster(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {

    }

    @Override
    public void doSlave(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.delistMaster(current.getName());
        callback.enlistSlave(current.getName());
    }

    @Override
    public void doInvalid(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.delistMaster(current.getName());
    }
}
