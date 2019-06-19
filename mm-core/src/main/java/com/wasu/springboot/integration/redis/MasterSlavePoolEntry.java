package com.wasu.springboot.integration.redis;

import redis.clients.jedis.Jedis;

public interface MasterSlavePoolEntry {
    /**
     * 获取当前资源的名称
     * @return
     */
    String getName();

    MasterSlaveState getState();

    void setState(MasterSlaveState state);

    Jedis getResource();

    MasterSlavePoolHealth getHealth();
}
