//package com.wasu.springboot.integration.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//
//@Configuration
//@EnableWebMvc
//public class MvcConfig extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public FreeMarkerViewResolver freeMarkerViewResolver() {
//        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//        resolver.setPrefix("");
//        resolver.setSuffix(".ftl");
//        resolver.setContentType("text/html; charset=UTF-8");
//        resolver.setRequestContextAttribute("request");
//        return resolver;
//    }
//}
