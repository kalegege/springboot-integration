package com.wasu.springboot.integration.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicConfig {
    @Value("${task.log.switch:off}")
    private String taskLogSwitch;

    @Value("${invest-research-gateway.appkey}")
    private String gatewayAppkey;

    @Value("${invest-research-gateway.appsecret}")
    private String gatewayAppsecret;

    @Value("${invest-research-gateway.url}")
    private String gatewayUrl;

    @Value("${mm-service.name}")
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getGatewayAppkey() {
        return gatewayAppkey;
    }

    public void setGatewayAppkey(String gatewayAppkey) {
        this.gatewayAppkey = gatewayAppkey;
    }

    public String getGatewayAppsecret() {
        return gatewayAppsecret;
    }

    public void setGatewayAppsecret(String gatewayAppsecret) {
        this.gatewayAppsecret = gatewayAppsecret;
    }

    public String getTaskLogSwitch() {
        return taskLogSwitch;
    }

    public void setTaskLogSwitch(String taskLogSwitch) {
        this.taskLogSwitch = taskLogSwitch;
    }
}
