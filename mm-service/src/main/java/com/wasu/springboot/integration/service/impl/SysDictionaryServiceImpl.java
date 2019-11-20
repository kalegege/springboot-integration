package com.wasu.springboot.integration.service.impl;

import com.wasu.springboot.integration.dao.SysDictionaryDao;
import com.wasu.springboot.integration.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    @Autowired
    private SysDictionaryDao sysDictionaryDao;
}
