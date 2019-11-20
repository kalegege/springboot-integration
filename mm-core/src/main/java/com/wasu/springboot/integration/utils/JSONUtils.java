package com.wasu.springboot.integration.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static <T> List<T> parseArray(JSONArray json,Class<T> clazz){
            return json.toJavaList(clazz);
    }

    public static String toJsonString(Object object){
        return JSONObject.toJSONString(object,features);
    }

    public static void main(String[] args) throws Exception{
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("name", Arrays.asList("12","21"));
        paramMap.put("sex","man");
        String str=JSONUtils.toJsonString(paramMap);
        System.out.println(str);
        Object o=JSONObject.parse(str);
        System.out.println(o);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> m = mapper.readValue(str, Map.class);
        System.out.println(m);
    }
}
