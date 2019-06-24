package com.wasu.springboot.integration.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wasu.springboot.integration.common.filter.XssValueFilter;
import com.wasu.springboot.integration.system.remoting.OperateRecordRemoting;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
@Aspect
@Order(-1)
public class XssAop {
    private static final Logger LOGGER= LoggerFactory.getLogger(XssAop.class);

    private static final SerializerFeature[] features = {
            SerializerFeature.SkipTransientField,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty
    };

    private static final XssValueFilter XSS_VALUE_FILTER=new XssValueFilter();

    @Autowired
    private OperateRecordRemoting operateRecordRemoting;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    private void point(){}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result ;

        result=joinPoint.proceed();
        if(result != null){
            JsonResult jsonResult=null;
            String resultStr;
            try{
                if(result instanceof JsonResult){
                    jsonResult=(JsonResult) result;
                    resultStr= JSONObject.toJSONString(jsonResult,XSS_VALUE_FILTER,features);
                    result= JSONUtils.parseObject(resultStr,JsonResult.class);
                }else if (result instanceof String){
                    resultStr = (String) result;
                    try{
                        jsonResult = JSONUtils.parseObject(resultStr,JsonResult.class);
                        result=JSONObject.toJSONString(jsonResult,XSS_VALUE_FILTER,features);
                    }catch(Exception e){
                        result= HtmlUtils.htmlEscape(resultStr);
                    }
                }
            }catch(Exception e){
                LOGGER.warn("xss error",e.getMessage());
            }
        }
        return result;
    }
}
