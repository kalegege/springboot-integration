package com.wasu.springboot.integration.batch.controller;

import com.wasu.springboot.integration.entity.system.UserInfo;
import com.wasu.springboot.integration.utils.StringUtils;
import  org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RefreshScope
public class HelloController {

    private static final Logger LOGGER= LoggerFactory.getLogger(HelloController.class);

    @Value("${mm-service}")
    private String fromValue;

    @RequestMapping("/hello")
    public String say(ModelMap modelMap){
        modelMap.addAttribute("msg","this is my first freemarker");
        return "hello"+fromValue;
    }
    @RequestMapping("/panel")
    public String panel(ModelMap modelMap){
        modelMap.addAttribute("msg","this is my first freemarker");
        return "websocket/panel";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/redirect")
    public void redirect(HttpServletResponse response) throws Exception{
        System.out.println("tttt");
        response.sendRedirect("https://www.baidu.com");
    }

    @RequestMapping("/goodbye")
    @ResponseBody
    public String goodbye(){
        LOGGER.info("enter goodbye ---");
        UserInfo result=new UserInfo();
        result.setName(StringUtils.omit("徐健",1));
        result.setAge(100);
        result.setPasswd("123456");

        LOGGER.info("---leave goodbye !");
        return result.toString();
    }
}
