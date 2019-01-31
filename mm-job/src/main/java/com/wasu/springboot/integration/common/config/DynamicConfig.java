package com.wasu.springboot.integration.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicConfig {
    @Value("${task.log.switch:on}")
    private String taskLogSwitch;

    public String getTaskLogSwitch() {
        return taskLogSwitch;
    }

    public void setTaskLogSwitch(String taskLogSwitch) {
        this.taskLogSwitch = taskLogSwitch;
    }
}
