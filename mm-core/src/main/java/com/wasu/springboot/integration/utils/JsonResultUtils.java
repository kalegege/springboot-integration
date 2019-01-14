package com.wasu.springboot.integration.utils;

import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.exceptions.BaseCode;

public class JsonResultUtils {

    public static JsonResult error(String errorMsg){
        return new JsonResult(new BaseCode(CommonConstant.ERROR_FLAG,errorMsg));
    }

    public static JsonResult success(Object data){
        return new JsonResult(data);
    }
}
