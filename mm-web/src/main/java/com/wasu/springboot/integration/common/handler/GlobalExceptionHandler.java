package com.wasu.springboot.integration.common.handler;

import com.wasu.springboot.integration.batch.controller.HelloController;
import com.wasu.springboot.integration.utils.AnnotationUtils;
import com.wasu.springboot.integration.utils.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionHandler extends BaseGlobalExceptionHandler{

    private static final Logger LOGGER= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    public ModelAndView handlerError(HttpServletRequest request, HttpServletResponse response,Throwable throwable){
        String uri=request.getRequestURI();
        String message= StringUtils.getStackTraceAsString(throwable);

        if(null != throwable){
            LOGGER.info("note error");
        }

        try{
            HandlerExecutionChain chain=handlerMapping.getHandler(request);
            Object obj=chain.getHandler();
            if(null != obj && obj instanceof HandlerMethod){
                HandlerMethod handlerMethod=(HandlerMethod)obj;
                if(AnnotationUtils.chechAnnotationExist(handlerMethod.getBeanType(),
                        handlerMethod.getMethod().getName(), ResponseBody.class)){
                    return handlerAjaxError(throwable);
                }else{
                    if(AnnotationUtils.chechAnnotationExist(handlerMethod.getBeanType(),
                            ResponseBody.class, RestController.class)){
                        return handlerAjaxError(throwable);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return handlerViewError("error/error",message,throwable);
    }


    /**
     * 页面跳转请求处理封装
     * @param viewName
     * @param errorStack
     * @param throwable
     * @return
     */
    private ModelAndView handlerViewError(String viewName, String errorStack, Throwable throwable) {
        ModelAndView mav=new ModelAndView(viewName);
        mav.addObject("exception",errorStack);
        if(throwable instanceof UnauthorizedException){
            mav.addObject("message","该用户没有授予此权限");
        }else{
            return super.handlerViewError(viewName,throwable);
        }
        return mav;
    }
}
