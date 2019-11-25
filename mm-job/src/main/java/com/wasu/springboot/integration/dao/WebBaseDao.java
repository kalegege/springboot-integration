package com.wasu.springboot.integration.dao;

import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;

public class WebBaseDao extends BaseDaoImpl implements BaseDao {

    @Resource(name="sqlSessionTemplate")
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setReadSessionTemplate(sqlSessionTemplate);
        super.setWriteSessionTemplate(sqlSessionTemplate);
    }
}
