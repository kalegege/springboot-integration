package com.wasu.springboot.integration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String say(ModelMap modelMap){
        modelMap.addAttribute("msg","this is my first freemarker");
        return "hello";
    }

    @RequestMapping("/goodbye")
    public String goodbye(){
        return "goodbye world!";
    }
}
