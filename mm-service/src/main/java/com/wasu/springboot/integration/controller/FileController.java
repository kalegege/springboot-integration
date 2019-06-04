package com.wasu.springboot.integration.controller;

import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.entity.file.FileEntity;
import com.wasu.springboot.integration.service.FileService;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file/")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传附件
     * @param mFile
     * @param file
     * @return
     */
    @RequestMapping("uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile mFile, FileEntity file){
        JsonResult jsonResult=null;
        if(null == mFile){
            JsonResult result=new JsonResult("没有需要上传的文件。",null);
            return JSONUtils.toJsonString(result);
        }
        FileEntity fileTemp=fileService.uploadFile(mFile,file);
        if(null == fileTemp || null == fileTemp.getId()){
            JsonResult result=new JsonResult("文件上传失败。",null);
            return JSONUtils.toJsonString(result);
        }
        JsonResult result=new JsonResult(CommonConstant.SUCCESS,fileTemp);
        return JSONUtils.toJsonString(result);
    }

    /**
     * 获取文件信息
     * @param file
     * @return
     */
    @RequestMapping("getFileInfo")
    @ResponseBody
    public String getFileInfo(FileEntity file){
        JsonResult jsonResult=null;
        if(null == file || null == file.getId()){
            jsonResult=new JsonResult("参数为空，无法获取文件。",null);
            return JSONUtils.toJsonString(jsonResult);
        }
        FileEntity fileTemp=fileService.getFileById(file);
        if(null == fileTemp ){
            jsonResult=new JsonResult("文件不存在。",null);
            return JSONUtils.toJsonString(jsonResult);
        }
        jsonResult=new JsonResult(CommonConstant.SUCCESS,fileTemp);
        return JSONUtils.toJsonString(jsonResult);
    }

    /**
     * 删除文件
     * @param file
     * @return
     */
    @RequestMapping("deleteFile")
    @ResponseBody
    public String deleteFile(FileEntity file){
        JsonResult jsonResult=null;
        FileEntity fileTemp=fileService.getFileById(file);
        if(null != fileTemp){
            fileService.deleteFile(file);
        }
        jsonResult=new JsonResult(CommonConstant.SUCCESS,1);
        return JSONUtils.toJsonString(jsonResult);
    }

}
