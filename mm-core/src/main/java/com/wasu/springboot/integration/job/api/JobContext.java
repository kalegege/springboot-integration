package com.wasu.springboot.integration.job.api;

public class JobContext {
    private String taskId;
    private String taskparameter;

    public JobContext() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskparameter() {
        return taskparameter;
    }

    public void setTaskparameter(String taskparameter) {
        this.taskparameter = taskparameter;
    }
}
