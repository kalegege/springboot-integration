package com.wasu.springboot.integration.service;

import com.wasu.springboot.integration.entity.file.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    void batchInsertFile(List<FileEntity> fileList);

    FileEntity uploadFile(MultipartFile mFile,FileEntity file);

    FileEntity getFileById(FileEntity file);

    void deleteFile(FileEntity file);
}
