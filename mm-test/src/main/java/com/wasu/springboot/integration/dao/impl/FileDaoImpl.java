package com.wasu.springboot.integration.dao.impl;

import com.wasu.springboot.integration.dao.FileDao;
import com.wasu.springboot.integration.entity.file.FileEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class FileDaoImpl implements FileDao {

    @Resource
    private SqlSession sqlSession;

    @Override
    public FileEntity queryByName(String name) {
        return sqlSession.selectOne(FileDaoImpl.class.getName()+".queryByName",name);
    }
}
