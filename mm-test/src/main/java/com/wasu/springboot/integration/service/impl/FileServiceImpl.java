package com.wasu.springboot.integration.service.impl;

import com.wasu.springboot.integration.dao.FileDao;
import com.wasu.springboot.integration.entity.file.FileEntity;
import com.wasu.springboot.integration.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    @Override
    public FileEntity queryByName(String name) {
        return fileDao.queryByName(name);
    }
}
