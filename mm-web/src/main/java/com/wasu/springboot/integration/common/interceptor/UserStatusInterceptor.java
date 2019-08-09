package com.wasu.springboot.integration.common.interceptor;

import com.wasu.springboot.integration.common.shiro.ShiroUtils;
import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.entity.system.ActiveUser;
import com.wasu.springboot.integration.exceptions.UserSsoException;
import com.wasu.springboot.integration.redis.RedisUtil;
import com.wasu.springboot.integration.utils.DateUtils;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import com.wasu.springboot.integration.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

public class UserStatusInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatusInterceptor.class);

    @Autowired
    RedisUtil cacheUtil;

    @Autowired
    private ApplicationContext applicationContext;

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method handlerMethod=((HandlerMethod)handler).getMethod();
        Annotation anno=handlerMethod.getAnnotation(ResponseBody.class);
        try{
//            AbstractHandlerMethodMapping<RequestMappingInfo> mapping=(AbstractHandlerMethodMapping<RequestMappingInfo>)applicationContext.getBean("requestMappingHandlerMapping");
//            Map<RequestMappingInfo,HandlerMethod> mapRet = mapping.getHandlerMethods();
//
//            boolean flag=false;
//            for(RequestMappingInfo mappingInfo: mapRet.keySet()){
//                RequestMappingInfo info=mappingInfo.getMatchingCondition(request);
//                if(info != null){
//                    flag=true;
//                    break;
//                }
//            }
//            if(flag){
//                HttpSession session =request.getSession();
//                //todo
//                ActiveUser activeUser=(ActiveUser) session.getAttribute(CommonConstant.USER_INFO_SESSION);
//                if(null == activeUser){
//                    response.sendRedirect("/logout");
//                    return false;
//                }
//                if(anno == null){
//                    if(activeUser.isFlag()){
//                        activeUser.setFlag(false);
//                        ShiroUtils.setAttribute(CommonConstant.USER_INFO_SESSION,activeUser);
//                        response.sendRedirect("/logout");
//                        return false;
//                    }
//                }else{
//                    if(activeUser.isFlag()){
//                        JsonResult jsonResult=new JsonResult();
//                        jsonResult.setCode(-9999);
//                        writeToResponse(request,response,jsonResult);
//                        return false;
//                    }
//                }
//
//                if(anno == null){
//                    cacheUtil.setMap(CommonConstant.LOGINSTATETYPE+activeUser.getOrgId(),activeUser.getUserid().toString(),
//                            DateUtils.formatDateTime(DateUtils.getNow(),DateUtils.DATE_TIME_FORMAT));
//                }else{
//                    String requestURI=request.getRequestURI();
//                    if(!requestURI.endsWith("/error")){
//                        cacheUtil.setMap(CommonConstant.LOGINSTATETYPE+activeUser.getOrgId(),activeUser.getUserid().toString(),
//                                DateUtils.formatDateTime(DateUtils.getNow(),DateUtils.DATE_TIME_FORMAT));
//                    }
//                }
//            }
            return super.preHandle(request, response, handler);
        }catch(Exception e){
            LOGGER.info(e.getMessage(),e);
            return handlerException(request,response,handler,e);
        }
    }

    private boolean handlerException(HttpServletRequest request, HttpServletResponse response,
                                     Object handler, Exception e) throws  Exception{
        Method handlerMethod =((HandlerMethod)handler).getMethod();
        Annotation anno=handlerMethod.getAnnotation(ResponseBody.class);
        if(anno == null){
            if(e instanceof UserSsoException){
                cacheUtil.expire(CommonConstant.SESSION_KEY_PREFIX+request.getSession().getId(),3);
                request.getSession().setAttribute(CommonConstant.USER_INFO_SESSION,null);
            }
            throw new Exception(e.getMessage());
        }else{
            UserSsoException userSsoException=(UserSsoException) e;
            JsonResult result=new JsonResult(userSsoException);
            this.outputJSON(response,JSONUtils.toJsonString(result));
            return false;
        }
    }

    private void outputJSON(HttpServletResponse response, String result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        PrintWriter writer=null;
        try{
            writer=response.getWriter();
            writer.println(result);
            writer.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(null != writer){
                writer.close();
                writer=null;
            }
        }
    }

    private void writeToResponse(HttpServletRequest request, HttpServletResponse response,
                                 JsonResult jsonResult) throws IOException{
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        out.println(JSONUtils.toJsonString(jsonResult));
        out.flush();
        out.close();
    }
}
