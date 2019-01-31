package com.wasu.springboot.integration.batch.dao;


import com.wasu.springboot.integration.base.dao.BaseDao;
import com.wasu.springboot.integration.entity.batch.BatchTaskEntity;

import java.util.List;
import java.util.Map;

public interface BatchTaskDao extends BaseDao {

    List<BatchTaskEntity> getList(Map<String,Object> item);

    void updateTime(Map<String,Object> item);

}
