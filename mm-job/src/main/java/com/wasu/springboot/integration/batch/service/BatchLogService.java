package com.wasu.springboot.integration.batch.service;

import com.wasu.springboot.integration.entity.batch.BatchLogEntity;

public interface BatchLogService {

    void insertBatchLog(BatchLogEntity entity);
}
