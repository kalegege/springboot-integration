package com.wasu.springboot.integration.dao;

import com.wasu.springboot.integration.entity.file.FileEntity;

public interface FileDao {
    FileEntity queryByName(String name);
}
