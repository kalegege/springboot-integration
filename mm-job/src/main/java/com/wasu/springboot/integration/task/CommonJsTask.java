package com.wasu.springboot.integration.task;

import com.wasu.springboot.integration.job.api.JobContext;
import com.wasu.springboot.integration.job.api.SimpleJob;
import com.wasu.springboot.integration.remoting.file.FileRemoting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Configurable
@EnableScheduling
public class CommonJsTask implements SimpleJob {

    private static final Logger LOGGER= LoggerFactory.getLogger(CommonJsTask.class);

    @Autowired
    private FileRemoting fileRemoting;

    private static final String createJsPath="";

    @Override
    public void execute(JobContext jobContext) throws InterruptedException {
        LOGGER.info("start---------------");
        System.out.println(fileRemoting.test().getName());;
        Thread.sleep(1);
        List<String> targetList= Arrays.asList("1","3","2");
        System.out.println(fileRemoting.pushMessage(targetList));
        LOGGER.info("end-----------------");
    }

    @Override
    public String getJobname() {
        return "commonJsTask";
    }
}
