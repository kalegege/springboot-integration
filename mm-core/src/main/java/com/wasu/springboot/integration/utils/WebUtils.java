package com.wasu.springboot.integration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class WebUtils {
    private static final Logger LOGGER= LoggerFactory.getLogger(WebUtils.class);

    public static String getIpAddr(HttpServletRequest request){
        return IpUtils.getRemoteIp(request);
    }

    public static String getBasePath(HttpServletRequest request){
        String path=request.getContextPath();
        String basePath=request.getScheme() + "://"+request.getServerName()
                +":"+request.getServerPort()+path+"/";
        return basePath;
    }

    /**
     * 从request中取出参数并封装成string
     * @param request
     * @return
     */
    public static String transRequestToString(HttpServletRequest request){
        if(request == null){
            return null;
        }
        StringBuffer result=new StringBuffer();
        Enumeration<String> parameterNames=request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            String parameter =request.getParameter(key);
            result.append(key).append("=").append(null == parameter ? "":parameter)
                    .append(parameterNames.hasMoreElements()? ";":"");
        }
        return result.toString();
    }
}
