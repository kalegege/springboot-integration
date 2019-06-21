package com.wasu.springboot.integration.common.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.wasu.springboot.integration.common.tag.ConstantTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("constant",new ConstantTag());
        configuration.setSharedVariable("shiro",new ShiroTags());
    }
}
