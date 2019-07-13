package com.wasu.springboot.integration.controller;

import com.wasu.springboot.integration.entity.message.JsonResult;
import com.wasu.springboot.integration.service.FileService;
import com.wasu.springboot.integration.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试类
 */
@RestController
public class Filecontroller {

    @Resource
    private FileService fileService;

    @RequestMapping("/queryByName")
    public JsonResult queryByName(String name){
        if(StringUtils.isEmpty(name)){
            return JsonResult.builError();
        }
        JsonResult result=JsonResult.builSuccess();
        result.setData(fileService.queryByName(name));
        return result;
    }
}
