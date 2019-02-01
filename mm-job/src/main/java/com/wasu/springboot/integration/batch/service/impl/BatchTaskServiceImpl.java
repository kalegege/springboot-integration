package com.wasu.springboot.integration.batch.service.impl;

import com.wasu.springboot.integration.batch.service.BatchTaskService;
import com.wasu.springboot.integration.entity.batch.BatchTaskEntity;
import com.wasu.springboot.integration.entity.page.PageBean;
import com.wasu.springboot.integration.entity.page.PageParam;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BatchTaskServiceImpl implements BatchTaskService{


    @Override
    public PageBean<BatchTaskEntity> listPageBatchTask(PageParam pageParam, String taskName, Date executeDate, int dependonid) {

        return null;
    }

    @Override
    public BatchTaskEntity getBatchTask(BatchTaskEntity entity) {
        return null;
    }
}
