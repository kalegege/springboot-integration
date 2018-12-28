package com.wasu.springboot.integration.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String say(){
        return "hello world!";
    }

    @RequestMapping("/goodbye")
    public String goodbye(){
        return "goodbye world!";
    }
}
