package com.wasu.springboot.integration.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {
    private static final SerializerFeature[] features={
            SerializerFeature.SkipTransientField,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty
    };

    public static <T> T parseObject(String json,Class<T> clazz){
        return JSONObject.parseObject(json,clazz);
    }

    public static String toJsonString(Object object){
        return JSONObject.toJSONString(object,features);
    }
}
