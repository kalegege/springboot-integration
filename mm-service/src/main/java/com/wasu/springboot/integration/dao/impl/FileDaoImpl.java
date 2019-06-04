package com.wasu.springboot.integration.dao.impl;

import com.wasu.springboot.integration.base.dao.WebBaseDao;
import com.wasu.springboot.integration.dao.FileDao;
import org.springframework.stereotype.Repository;

@Repository("fileDao")
public class FileDaoImpl extends WebBaseDao implements FileDao {
}
