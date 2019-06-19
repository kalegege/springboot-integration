package com.wasu.springboot.integration.controller;

import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.entity.file.FileEntity;
import com.wasu.springboot.integration.service.FileService;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

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

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:5000/");//建立HttpPost对象,改成自己的地址
        File file = new File("C:\\temp.png");//相对路径使用不了的话,使用图片绝对路径
        if(!file.exists()){//判断文件是否存在
            System.out.print("文件不存在");
            return;
        }
        FileBody bin = new FileBody(file, ContentType.create("image/png", Consts.UTF_8));//创建图片提交主体信息
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .setCharset(Charset.forName("utf-8"))
                .addPart("file", bin)//添加到请求
                .build();
        httpPost.setEntity(entity);
        HttpResponse response= null;//发送Post,并返回一个HttpResponse对象
        try {
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200) {//如果状态码为200,就是正常返回
                String result = EntityUtils.toString(response.getEntity());
                System.out.print(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(response);
        System.out.print("结束");
    }
}
