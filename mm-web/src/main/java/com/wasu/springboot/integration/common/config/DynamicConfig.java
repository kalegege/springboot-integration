package com.wasu.springboot.integration.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration("dynamicConfig")
public class DynamicConfig {
    @Value("${mm.name}")
    private String webServiceName;

    @Value("mm-service.name")
    private String systemServiceName;

    public String getWebServiceName() {
        return webServiceName;
    }

    public String getSystemServiceName() {
        return systemServiceName;
    }
}
