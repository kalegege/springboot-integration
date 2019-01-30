package com.wasu.springboot.integration.common.config;

import com.wasu.springboot.integration.constants.JobConstant;
import com.wasu.springboot.integration.job.JobQuartzAdapterService;
import com.wasu.springboot.integration.job.api.JobContext;
import com.wasu.springboot.integration.job.api.SimpleJob;
import com.wasu.springboot.integration.job.filter.JobFilter;
import com.wasu.springboot.integration.job.impl.SequenceJobGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Configuration
public class SchedledConfiguration {

    @Autowired
    private BatchMachineJobFilter batchMachineJobFilter;

    private static final int COREPOOLSIZE=10;
    private static final int MAXPOOLSIZE=100;
    private static final int QUEUECAPACITY=10;
    private static final int KEEPALIVESECONDS=300;

    @Bean(name="concurrenttaskExecutor")
    @Qualifier("concurrenttaskExecutor")
    public Executor concurrenttaskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(COREPOOLSIZE);
        executor.setMaxPoolSize(MAXPOOLSIZE);
        executor.setQueueCapacity(QUEUECAPACITY);
        executor.setKeepAliveSeconds(KEEPALIVESECONDS);
        return executor;
    }

    /**
     * 每天凌晨一点触发
     * @return
     */
    @Bean(name="everyDay0100ConcurrentJobGroupService")
    @Qualifier("everyDay0100ConcurrentJobGroupService")
    public JobQuartzAdapterService everyDay0100ConcurrentJobGroupService(){
        SequenceJobGroupServiceImpl jobGroupService=new SequenceJobGroupServiceImpl();
        List<SimpleJob> listSimpleJob=new ArrayList<>();
        listSimpleJob.add(null);
        jobGroupService.setListJobGroup(listSimpleJob);

        List<JobFilter> listJobFilter =new ArrayList<>();
        listJobFilter.add(batchMachineJobFilter);
        jobGroupService.setListJobFilter(listJobFilter);
        return jobGroupService;
    }

    @Bean(name="everyDay0100FactoryBran")
    @Qualifier("everyDay0100FactoryBran")
    public MethodInvokingJobDetailFactoryBean everyDay0100FactoryBran(){
        MethodInvokingJobDetailFactoryBean bean=new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(everyDay0100ConcurrentJobGroupService());
        bean.setTargetMethod("execute");
        bean.setConcurrent(false);
        return bean;
    }

    public CronTriggerFactoryBean everyDay0100TriggerBean(){
        CronTriggerFactoryBean trigger=new CronTriggerFactoryBean();
        trigger.setJobDetail(everyDay0100FactoryBran().getObject());
        try{
            trigger.setCronExpression(JobConstant.JOB_EVERY_DAY_1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return trigger;
    }

}
