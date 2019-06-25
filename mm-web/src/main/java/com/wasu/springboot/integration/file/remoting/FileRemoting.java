package com.wasu.springboot.integration.file.remoting;

import com.wasu.springboot.integration.entity.file.FileEntity;
import com.wasu.springboot.integration.utils.JsonResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileRemoting {
    /**
     * 上传文件
     * @param mFile
     * @param fileEntity
     * @return
     */
    JsonResult upload(MultipartFile mFile, FileEntity fileEntity);

    /**
     * 删除文件
     * @param fileEntity
     * @return
     */
    JsonResult deleteFile(FileEntity fileEntity);
}
