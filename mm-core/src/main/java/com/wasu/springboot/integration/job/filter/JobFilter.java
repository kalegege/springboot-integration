package com.wasu.springboot.integration.job.filter;

public interface JobFilter {
    boolean preHandler();

    void afterHandler();
}
