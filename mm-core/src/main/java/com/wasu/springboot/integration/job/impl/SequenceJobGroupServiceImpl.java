package com.wasu.springboot.integration.job.impl;

import com.wasu.springboot.integration.job.api.JobContext;
import com.wasu.springboot.integration.job.api.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class SequenceJobGroupServiceImpl extends AbstractJobGroupServiceImpl{
    private static final Logger LOGGER= LoggerFactory.getLogger(SequenceJobGroupServiceImpl.class);

    public SequenceJobGroupServiceImpl() {
    }

    @Override
    protected void doExecute() {
        List<SimpleJob>  listJobGroup=this.getListJobGroup();
        Iterator var2=listJobGroup.iterator();

        while(var2.hasNext()){
            SimpleJob simpleJob=(SimpleJob)var2.next();
            LOGGER.info("start================"+simpleJob.getJobname());

            try{
                simpleJob.execute(new JobContext());
            }catch(Exception var5){
                LOGGER.warn(var5.getMessage(),var5);
            }
        }
    }
}
