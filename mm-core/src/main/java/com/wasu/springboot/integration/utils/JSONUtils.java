package com.wasu.springboot.integration.utils;

import com.alibaba.fastjson.JSONObject;

public class JSONUtils {
    public static <T> T parseObject(String json,Class<T> clazz){
        return JSONObject.parseObject(json,clazz);
    }
}
