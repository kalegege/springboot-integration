package com.wasu.springboot.integration.task;

import com.wasu.springboot.integration.job.api.JobContext;
import com.wasu.springboot.integration.job.api.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonJsTask implements SimpleJob {

    private static final Logger LOGGER= LoggerFactory.getLogger(CommonJsTask.class);

    private static final String createJsPath="";

    @Override
    public void execute(JobContext jobContext) {
        LOGGER.info("start---------------");
        LOGGER.info("end-----------------");
    }

    @Override
    public String getJobname() {
        return "commonJsTask";
    }
}
