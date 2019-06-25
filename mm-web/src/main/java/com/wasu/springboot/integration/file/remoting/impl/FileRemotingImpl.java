package com.wasu.springboot.integration.file.remoting.impl;

import com.wasu.springboot.integration.base.BaseRemoting;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.entity.file.FileEntity;
import com.wasu.springboot.integration.file.remoting.FileRemoting;
import com.wasu.springboot.integration.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("fileRemoting")
public class FileRemotingImpl extends BaseRemoting implements FileRemoting {

    @Autowired
    private DynamicConfig dynamicConfig;

    /**
     * 上传文件
     * @param mFile
     * @param fileEntity
     * @return
     */
    @Override
    public JsonResult upload(MultipartFile mFile, FileEntity fileEntity) {
        Map<String,Object> param =new HashMap<>();
        param.put("creater",fileEntity.getCreater());
        param.put("fileType",fileEntity.getFileType());
        param.put("orgId",fileEntity.getOrgId());
        String invokeResult = null;
        try{
            invokeResult=postForOneFile(dynamicConfig.getSystemServiceName(),"file/uploadFile",param,"mFile",
                    mFile.getInputStream(),mFile.getOriginalFilename(),String.class);
        }catch(IOException e){

        }
        JsonResult jsonResult=checkInvokeSuccess(invokeResult);
        return jsonResult;
    }

    /**
     * 删除文件
     * @param fileEntity
     * @return
     */
    @Override
    public JsonResult deleteFile(FileEntity fileEntity) {
        String invokeResult=postForObject(dynamicConfig.getSystemServiceName(),"file/deleteFile",fileEntity,String.class);
        JsonResult result=checkInvokeSuccess(invokeResult);
        return result;
    }
}
