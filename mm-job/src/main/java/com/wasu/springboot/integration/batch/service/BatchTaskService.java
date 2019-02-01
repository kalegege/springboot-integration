package com.wasu.springboot.integration.batch.service;

import com.wasu.springboot.integration.entity.batch.BatchLogEntity;
import com.wasu.springboot.integration.entity.batch.BatchTaskEntity;
import com.wasu.springboot.integration.entity.page.PageBean;
import com.wasu.springboot.integration.entity.page.PageParam;

import java.util.Date;

public interface BatchTaskService {

    /**
     * 分页查询跑批task对象
     * @param pageParam
     * @param taskName
     * @param executeDate
     * @param dependonid
     * @return
     */
    PageBean<BatchTaskEntity> listPageBatchTask(PageParam pageParam, String taskName, Date executeDate, int dependonid);


    /**
     * 查询跑批task对象
     * @param entity
     * @return
     */
    BatchTaskEntity getBatchTask(BatchTaskEntity entity);
}
