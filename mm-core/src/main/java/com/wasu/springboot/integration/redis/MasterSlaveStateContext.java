package com.wasu.springboot.integration.redis;

import com.wasu.springboot.integration.redis.impl.InvalidStateImpl;
import com.wasu.springboot.integration.redis.impl.MasterStateImpl;
import com.wasu.springboot.integration.redis.impl.SlaveStateImpl;

public class MasterSlaveStateContext {
    public final static MasterSlaveState STATE_MASTER=new MasterStateImpl();
    public final static MasterSlaveState STATE_SLAVE=new SlaveStateImpl();
    public final static MasterSlaveState STATE_INVALID=new InvalidStateImpl();

}
