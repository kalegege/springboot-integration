package com.wasu.springboot.integration.controller;

import com.wasu.springboot.integration.entity.system.UserInfo;
import com.wasu.springboot.integration.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    private static final Logger LOGGER= LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String say(ModelMap modelMap){
        modelMap.addAttribute("msg","this is my first freemarker");
        return "hello";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
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
