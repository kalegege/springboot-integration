package com.wasu.springboot.integration.batch.dao.impl;


import com.wasu.springboot.integration.base.dao.WebBaseDao;
import com.wasu.springboot.integration.batch.dao.BatchTaskDao;
import com.wasu.springboot.integration.batch.service.BatchTaskService;
import com.wasu.springboot.integration.entity.batch.BatchTaskEntity;
import com.wasu.springboot.integration.entity.page.PageBean;
import com.wasu.springboot.integration.entity.page.PageParam;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class BatchTaskDaoImpl extends WebBaseDao implements BatchTaskDao {


    @Override
    public List<BatchTaskEntity> getList(Map<String, Object> item) {
        return null;
    }

    @Override
    public void updateTime(Map<String, Object> item) {

    }
}
