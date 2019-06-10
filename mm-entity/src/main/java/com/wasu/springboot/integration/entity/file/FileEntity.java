package com.wasu.springboot.integration.entity.file;

import com.wasu.springboot.integration.base.BaseEntity;
import lombok.Data;

@Data
public class FileEntity extends BaseEntity{
    private static final long serialVersionUID=-1;

    private Integer fileType;

    private String filePath;

    private String fileName;

    private String fileGroup;

    private Long fileSize;
}
