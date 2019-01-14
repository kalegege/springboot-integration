package com.wasu.springboot.integration.controller;

import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class HelloController {

    @RequestMapping("/hello")
    public String say(){
        return JSONUtils.toJsonString(JsonResultUtils.error("组织id不能为空"));
    }

    @RequestMapping("/goodbye")
    public String goodbye(){
        return "goodbye world!";
    }
}
