package com.wasu.springboot.integration.file.controller;

import com.wasu.springboot.integration.base.BaseController;
import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.constants.FileConstant;
import com.wasu.springboot.integration.entity.file.FileEntity;
import com.wasu.springboot.integration.entity.system.ActiveUser;
import com.wasu.springboot.integration.file.remoting.FileRemoting;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import com.wasu.springboot.integration.utils.StringUtils;
import com.wasu.springboot.integration.utils.file.ImageZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileRemoting fileRemoting;

    public String fileManage(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "file",required = false)MultipartFile mFile){
        JsonResult jsonResult=null;

        String type=validFileRequestParams(request,mFile);

        if(type.equals(FileConstant.FILE_CODE_UPLOAD)){
            //文件上传
            return JSONUtils.toJsonString(uploadFile(request,mFile));
        }else if(type.equals(FileConstant.FILE_CODE_DELETE)){
            //删除文件
            return JSONUtils.toJsonString(deleteFile(request,mFile));
        }else if(type.equals(FileConstant.FILE_CODE_LIST)){
            //文件列表
        }
        jsonResult=new JsonResult("非法请求",null);
        return JSONUtils.toJsonString(jsonResult);
    }

    /**
     * 删除文件
     * @param request
     * @param mFile
     * @return
     */
    private JsonResult deleteFile(HttpServletRequest request, MultipartFile mFile) {
        String fileId=request.getParameter("fileId");
        String type=request.getParameter("fileType");
        ActiveUser activeUser=(ActiveUser)request.getSession().getAttribute(CommonConstant.USER_INFO_SESSION);
        FileEntity fileEntity=new FileEntity();
        fileEntity.setCreater(activeUser.getUserid());
        fileEntity.setId(Long.valueOf(fileId));
        fileEntity.setFileType(Integer.valueOf(type));
        JsonResult fileJsonResult=fileRemoting.deleteFile(fileEntity);
        return fileJsonResult;
    }

    /**
     * 文件上传入口
     * @param request
     * @param mFile
     * @return
     */
    private JsonResult uploadFile(HttpServletRequest request, MultipartFile mFile) {
        JsonResult jsonResult=null;
        String fileName=mFile.getOriginalFilename();
        String fileType=fileName.substring(fileName.lastIndexOf("."),fileName.length());
        if(null == fileType){
            LOGGER.warn("上传的我文件没有明显的格式，文件名为："+fileName);
        }
        List<String> typeList= Arrays.asList(FileConstant.FILE_IMG_TYPES);
        if(null != fileType && typeList.contains(fileType.toLowerCase())){
            try{
                ImageZipUtil.zipImageFile(mFile.getInputStream(),"图片",400,400);
            }catch(IOException e){
                LOGGER.error(e.getMessage(),e);
                jsonResult=new JsonResult("上传的图片文件不是标准的图片格式",null);
                return jsonResult;
            }
        }
        FileEntity fileEntity=new FileEntity();
        ActiveUser activeUser=(ActiveUser) request.getSession().getAttribute(CommonConstant.USER_INFO_SESSION);

        fileEntity.setCreater(activeUser.getUserid());
        fileEntity.setOrgId(activeUser.getOrgId());
        fileEntity.setFileType(Integer.valueOf(request.getParameter("fileType")));
        JsonResult fileJsonResult=fileRemoting.upload(mFile,fileEntity);
        return fileJsonResult;
    }

    /**
     * 请求参数校验
     * @param request
     * @param mFile
     * @return
     */
    private String validFileRequestParams(HttpServletRequest request, MultipartFile mFile) {
        JsonResult jsonResult=null;
        String type=request.getParameter("type");
        if(StringUtils.isEmpty(type)){
            LOGGER.warn("文件管理请求类别参数type为空");
            jsonResult=new JsonResult("非法请求",null);
            return JSONUtils.toJsonString(jsonResult);
        }
        if(type.equals(FileConstant.FILE_CODE_UPLOAD)){
            //上传请求
            if(null == mFile){
                jsonResult=new JsonResult("没有待上传文件，请重新操作",null);
                return JSONUtils.toJsonString(jsonResult);
            }
            String fileType=request.getParameter("fileType");
            if(StringUtils.isEmpty(fileType)){
                LOGGER.warn("文件管理请求文件类别参数fileType为空");
                jsonResult=new JsonResult("非法请求",null);
                return JSONUtils.toJsonString(jsonResult);
            }
        }else if(type.equals(FileConstant.FILE_CODE_DOWNLOAD) || type.equals(FileConstant.FILE_CODE_VIEW)
                            || type.equals(FileConstant.FILE_CODE_DELETE)){
            String fileId=request.getParameter("fileId");
            if(StringUtils.isEmpty(fileId)){
                LOGGER.warn("文件管理请求文件类别参数fileId为空");
                jsonResult=new JsonResult("非法请求",null);
                return JSONUtils.toJsonString(jsonResult);
            }
            String fileType=request.getParameter("fileType");
            if(StringUtils.isEmpty(fileType)){
                LOGGER.warn("文件管理请求文件类别参数fileType为空");
                jsonResult=new JsonResult("非法请求",null);
                return JSONUtils.toJsonString(jsonResult);
            }
        }else if( type.equals(FileConstant.FILE_CODE_LIST)){
            //文件查询功能
        }else{
            type="noType";
        }
        return type;
    }
}
