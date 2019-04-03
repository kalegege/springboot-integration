package com.wasu.springboot.integration.common.handler;

import com.wasu.springboot.integration.constants.CommonCodeConstant;
import com.wasu.springboot.integration.constants.SystemCodeConstant;
import com.wasu.springboot.integration.exceptions.CommonCode;
import com.wasu.springboot.integration.exceptions.CommonCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseGlobalExceptionHandler {

    protected ModelAndView handlerViewError(String viewName,Throwable throwable){
        logError(throwable);

        ModelAndView mav=new ModelAndView(viewName);
        if(throwable instanceof CommonCodeException){
            CommonCodeException commonCodeException=(CommonCodeException) throwable;
            mav.addObject("code",commonCodeException.getCode());
            mav.addObject("msg", commonCodeException.getMsg());
            mav.addObject("subCode",commonCodeException.getSubCode());
            mav.addObject("subMsg", commonCodeException.getSubMsg());
            mav.addObject("data", null);
        }else{
            mav.addObject("code", SystemCodeConstant.SERVICE_EXCEPTION_CODE);
            mav.addObject("msg", SystemCodeConstant.SERVICE_EXCEPTION_MSG);
            mav.addObject("subCode",SystemCodeConstant.ServiceEnum.BUSINESS_UNKNOWN_ERROR.getSubCode());
            mav.addObject("subMsg", SystemCodeConstant.ServiceEnum.BUSINESS_UNKNOWN_ERROR.getSubMsg());
            mav.addObject("data", null);
        }
        return mav;
    }

    protected ModelAndView handlerAjaxError(Throwable throwable){
        logError(throwable);

        Map<String,Object> attribute =new HashMap<>();
        if(throwable instanceof CommonCodeException){
            CommonCodeException commonCodeException=(CommonCodeException) throwable;
            attribute.put("code",commonCodeException.getCode());
            attribute.put("msg", commonCodeException.getMsg());
            attribute.put("subCode",commonCodeException.getSubCode());
            attribute.put("subMsg", commonCodeException.getSubMsg());
            attribute.put("data", null);
        }else{
            attribute.put("code", SystemCodeConstant.SERVICE_EXCEPTION_CODE);
            attribute.put("msg", SystemCodeConstant.SERVICE_EXCEPTION_MSG);
            attribute.put("subCode",SystemCodeConstant.ServiceEnum.BUSINESS_UNKNOWN_ERROR.getSubCode());
            attribute.put("subMsg", SystemCodeConstant.ServiceEnum.BUSINESS_UNKNOWN_ERROR.getSubMsg());
            attribute.put("data", null);
        }
        MappingJackson2JsonView view =new MappingJackson2JsonView();
        view.setAttributesMap(attribute);

        ModelAndView mav=new ModelAndView();
        mav.setView(view);
        return mav;
    }

    private void logError(Throwable throwable) {
        if(throwable instanceof CommonCodeException){
            CommonCodeException commonCodeException=(CommonCodeException) throwable;
            log.warn(commonCodeException.print(),commonCodeException);
        }else{
            log.error(throwable.getMessage(),throwable);
        }
    }
}
