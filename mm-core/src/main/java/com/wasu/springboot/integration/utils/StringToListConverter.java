package com.wasu.springboot.integration.utils;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class StringToListConverter implements Converter<String,List<Object>> {
    @Override
    public List<Object> convert(String source) {
        List result=new ArrayList();
        if(!StringUtils.isEmpty(source)){
            String[] array = source.split(",");
            for(Object obj:array){
                result.add(obj);
            }
        }
        return result;
    }
}
