package com.wasu.springboot.integration.service;

import com.wasu.springboot.integration.entity.file.FileEntity;

public interface FileService {
    FileEntity queryByName(String name);
}
