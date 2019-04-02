package com.wasu.springboot.integration.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration("freeMarkerWebConfig")
public class FreeMarkerWebConfig extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {

    @Value("${web.src-root:/project/}")
    private String srcRoot;

    public FreeMarkerWebConfig() {
        FreeMarkerConfigurer configurer=super.freeMarkerConfigurer();
        Map<String,Object> map=new HashMap<>();
        map.put("src_root",srcRoot);
        configurer.setFreemarkerVariables(map);
    }
}
