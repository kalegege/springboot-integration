package com.wasu.springboot.integration.common.batch.aop;

import com.wasu.springboot.integration.batch.service.BatchTaskService;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.constants.ApplicationConstants;
import com.wasu.springboot.integration.entity.batch.BatchTaskEntity;
import com.wasu.springboot.integration.utils.AnnotationUtils;
import com.wasu.springboot.integration.utils.StringUtils;
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

    @Autowired
    private BatchTaskService batchTaskService;

    @Pointcut("@annotation(com.wasu.springboot.integration.common.batch.aop.TaskLogAnnotation)")
    private void init(){

    }

    @Around(POINT_METHOD)
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        if(ApplicationConstants.TASK_LOG_OFF.equals(dynamicConfig.getTaskLogSwitch())){
            joinPoint.proceed();
            return;
        }

        String methodName=joinPoint.getSignature().getName();
        String taskName = AnnotationUtils.getMethodAnnotationValue(joinPoint.getTarget().getClass(),methodName,TaskLogAnnotation.class,"taskName").toString();

        BatchTaskEntity taskEntity=new BatchTaskEntity();
        taskEntity.setTaskName(taskName);
        taskEntity.setUseStatus(1);
        taskEntity=batchTaskService.getBatchTask(taskEntity);

        if(null != taskEntity){
            //todo 完成日志打印操作
        }else{

        }
        joinPoint.proceed();
    }
}
