package com.wasu.springboot.integration.common.config;

import com.wasu.springboot.integration.utils.StringToDateConverter;
import com.wasu.springboot.integration.utils.StringToListConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new StringToListConverter());
        super.addFormatters(registry);
    }
}
