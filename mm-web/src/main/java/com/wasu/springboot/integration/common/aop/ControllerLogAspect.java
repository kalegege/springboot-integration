package com.wasu.springboot.integration.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wasu.springboot.integration.constants.SystemCodeConstant;
import com.wasu.springboot.integration.utils.IpUtils;
import com.wasu.springboot.integration.utils.JsonResultUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：dinkfamily
 * @version :  1.0.0$
 * @date ：Created in 2019/5/6 11:25
 * @description ：日志拦截器
 * @modified By  ：wuguoqing
 */
@Aspect
@Component
public class ControllerLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    private final String HTTP_POINT_CUT = "@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)";

    @Pointcut(HTTP_POINT_CUT)
    public void controllerLog() {
        logger.info("为了安全扫描而加的打印");
    }

    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws IOException {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = proceedingJoinPoint.getArgs();
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Map<String, Object> argMaps = new LinkedHashMap<>();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse)
            {
                continue;
            }
            argMaps.put(parameters[i].getName(), args[i]);
        }

        String inArgs= JSON.toJSONString(argMaps);
        logger.info("{}-url:{},method:{},ip:{},请求参数：{}", LogFlag.getRequestedUniqueFlag(), request.getRequestURI(), proceedingJoinPoint.getSignature().toShortString(), IpUtils.getRemoteIp(request),inArgs );
        Object result = null;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            result = proceedingJoinPoint.proceed();
            stopWatch.stop();
            logger.info("{}- 花费：{}毫秒，url:{},method:{},ip:{},请求参数：{},结果：{}", LogFlag.getRequestedUniqueFlag(), stopWatch.getTotalTimeMillis(),request.getRequestURI(), proceedingJoinPoint.getSignature().toShortString(), IpUtils.getRemoteIp(request),inArgs, JSON.toJSONString(result));
            //一定要有return,否则controller不返回数据
            return result;
        } catch (Throwable e) {
            logger.error("{}-url:{},method:{},ip:{},请求参数：{}", LogFlag.getRequestedUniqueFlag(),request.getRequestURI(),proceedingJoinPoint.getSignature().toShortString(), IpUtils.getRemoteIp(request),inArgs,e);
            HttpServletResponse res = attributes.getResponse();
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().write(JSONObject.toJSONString(JsonResultUtils.error(SystemCodeConstant.SERVICE_EXCEPTION_MSG)));
            return null;
        }
    }

}
