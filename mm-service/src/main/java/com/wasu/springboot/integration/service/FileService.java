package com.wasu.springboot.integration.service;

import com.wasu.springboot.integration.entity.file.FileEntity;

import java.util.List;

public interface FileService {
    void batchInsertFile(List<FileEntity> fileList);
}
