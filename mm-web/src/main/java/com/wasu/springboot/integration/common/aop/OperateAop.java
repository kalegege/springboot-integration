package com.wasu.springboot.integration.common.aop;

import com.wasu.springboot.integration.common.shiro.ShiroUtils;
import com.wasu.springboot.integration.constants.CommonCodeConstant;
import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.entity.system.ActiveUser;
import com.wasu.springboot.integration.entity.system.PubOperateRecord;
import com.wasu.springboot.integration.enums.OperationTypeEnum;
import com.wasu.springboot.integration.exceptions.ServiceException;
import com.wasu.springboot.integration.system.remoting.OperateRecordRemoting;
import com.wasu.springboot.integration.utils.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Order(1)
public class OperateAop {
    private static final Logger LOGGER= LoggerFactory.getLogger(OperateAop.class);

    @Autowired
    private OperateRecordRemoting operateRecordRemoting;

    @Pointcut("@annotation(com.wasu.springboot.integration.common.aop.OperationAnnotation)")
    private void point(){

    }

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ActiveUser activeUser= ShiroUtils.getActiveUser();
        Long userId=activeUser == null ? 0L:activeUser.getUserid();
        String ipAddr= WebUtils.getIpAddr(request);
        String params=WebUtils.transRequestToString(request);
        StringBuffer requestURL=request.getRequestURL();
        String  methodName=joinPoint.getSignature().getName();
        String operateName= AnnotationUtils.getMethodAnnotationValue(joinPoint.getTarget().getClass(),methodName,
                OperationAnnotation.class,"operateName").toString();
        Object operateTypeObj=AnnotationUtils.getMethodAnnotationValue(joinPoint.getTarget().getClass(),methodName,
                OperationAnnotation.class,"operateType");
        Short operateType=0;
        if(operateTypeObj != null){
            OperationTypeEnum operationTypeEnum=(OperationTypeEnum)operateTypeObj;
            operateType=operationTypeEnum.getType();
        }
        PubOperateRecord pubOperateRecord=new PubOperateRecord();
        pubOperateRecord.setUserId(userId);
        pubOperateRecord.setIpAddress(ipAddr);
        pubOperateRecord.setParam(StringUtils.subString(params,1024,false));
        pubOperateRecord.setRequestUrl(StringUtils.subString(methodName+";"+requestURL.toString(),128,false));
        pubOperateRecord.setOperateName(StringUtils.subString(operateName,128,false));
        pubOperateRecord.setOperateType(operateType);
        String msg="";
        Short isSuccess=2;
        Object result;
        try{
            result=joinPoint.proceed();
            if(result != null){
                JsonResult jsonResult=null;
                try{
                    if(result instanceof JsonResult){
                        jsonResult=(JsonResult) result;
                    }else if(result instanceof String){
                        String resultStr=(String) result;
                        jsonResult= JSONUtils.parseObject(resultStr,JsonResult.class);
                    }
                }catch(Exception e){
                    LOGGER.warn("operateAop error",e.getMessage());
                }finally {
                    if(jsonResult != null){
                        if(CommonCodeConstant.SUCCESS_CODE.equals(jsonResult.getCode())){
                            isSuccess =1;
                        }
                        msg=jsonResult.getSubMsg();
                    }
                }
            }
        }catch(ServiceException e){
            msg=e.getSubMsg();
            throw new ServiceException(msg);
        }catch(Exception e){
            msg=e.getMessage();
            throw new ServiceException(msg);
        }finally {
            pubOperateRecord.setIsSuccess(isSuccess);
            pubOperateRecord.setMsg(StringUtils.subString(msg,512,false));
            operateRecordRemoting.insert(pubOperateRecord);
        }
        return result;
    }
}
