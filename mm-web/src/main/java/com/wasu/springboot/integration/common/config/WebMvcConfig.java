package com.wasu.springboot.integration.common.config;

import com.wasu.springboot.integration.common.interceptor.UserStatusInterceptor;
import com.wasu.springboot.integration.common.interceptor.UserUriRequestPermissionInterceptor;
import com.wasu.springboot.integration.utils.StringToDateConverter;
import com.wasu.springboot.integration.utils.StringToListConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration=registry.addInterceptor(userStatusInterceptor());
        InterceptorRegistration userUriRequestPermissionInterceptor =registry.addInterceptor(userUriRequestPermissionInterceptor());

        super.addInterceptors(registry);
    }

    @Bean
    public UserUriRequestPermissionInterceptor userUriRequestPermissionInterceptor() {
        return new UserUriRequestPermissionInterceptor();
    }

    @Bean
    public UserStatusInterceptor userStatusInterceptor() {
        return new UserStatusInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/staticJs/**").addResourceLocations("file:"+"./staticJs/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new StringToListConverter());
        super.addFormatters(registry);
    }
}
