package com.wasu.springboot.integration.redis;

import java.util.TimerTask;
import java.util.concurrent.Executor;

public class MasterSlaveHolderRefreshTask extends TimerTask{

    public final static int DELAY_SECONDS = 5;

    public final static int PERIOD_SECONDS = 20;

    private Executor executor;
    private MasterSlaveHolder masterSlaveHolder;

    public MasterSlaveHolderRefreshTask(Executor executor, MasterSlaveHolder masterSlaveHolder) {
        this.executor=executor;
        this.masterSlaveHolder=masterSlaveHolder;
    }

    @Override
    public void run() {
        MasterSlaveHolderRefresh.refresh(executor,masterSlaveHolder);
    }
}
