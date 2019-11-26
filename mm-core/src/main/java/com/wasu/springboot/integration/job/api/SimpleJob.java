package com.wasu.springboot.integration.job.api;

public interface SimpleJob {
    void execute(JobContext var1) throws InterruptedException;

    String getJobname();
}
