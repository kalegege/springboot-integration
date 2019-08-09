package com.wasu.springboot.integration.batch.controller;

import com.netflix.discovery.converters.Auto;
import com.wasu.springboot.integration.entity.system.UserInfo;
import com.wasu.springboot.integration.system.remoting.OperateRecordRemoting;
import com.wasu.springboot.integration.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RefreshScope
public class HelloController {

    private static final Logger LOGGER= LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private OperateRecordRemoting operateRecordRemoting;

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

    @RequestMapping("/getPage")
    public void getPage(ModelMap modelMap,HttpServletResponse response)throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(operateRecordRemoting.getPage());
//        response.setCharacterEncoding("utf-8");
//        modelMap.addAttribute("html",operateRecordRemoting.getPage());
//        return "getPage";
    }

    @RequestMapping("/getPage2")
    public String getPage2(ModelMap modelMap,HttpServletResponse response)throws Exception{
//        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().write(operateRecordRemoting.getPage());
//        response.setCharacterEncoding("utf-8");
        modelMap.addAttribute("html",operateRecordRemoting.getPage());
        return "getPage";
    }
}
