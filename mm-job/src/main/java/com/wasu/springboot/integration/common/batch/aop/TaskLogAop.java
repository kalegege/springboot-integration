package com.wasu.springboot.integration.common.batch.aop;

import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.constants.ApplicationConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TaskLogAop {

    private static final String POINT_METHOD="init()";

    @Autowired
    private DynamicConfig dynamicConfig;

    @Pointcut("@annotation(com.wasu.springboot.integration.common.batch.aop.TaskLogAnnotation)")
    private void init(){

    }

    @Around(POINT_METHOD)
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        if(ApplicationConstants.TASK_LOG_OFF.equals(dynamicConfig.getTaskLogSwitch())){
            joinPoint.proceed();
            return;
        }

    }
}
