package com.wasu.springboot.integration.utils;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

public class MapUtils extends org.apache.commons.collections.MapUtils {

    public static Map<String,Object> populate(Object data){
        return populateMap(new HashMap<>(), data);
    }

    public static Map<String,Object> populateMap(Map<String,Object> populateMap,Object data){
        if(populateMap == null || data == null)
            throw new RuntimeException("populateMap and data is nnot empty!");

        BeanUtilsBean beanUtil =BeanUtilsBean.getInstance();
        PropertyDescriptor[] pds=beanUtil.getPropertyUtils().getPropertyDescriptors(data);
        try{
            for(PropertyDescriptor pd : pds){
                if("class".equals(pd.getName())) continue;

                String name=pd.getName();
                Object value=beanUtil.getPropertyUtils().getSimpleProperty(data,name);
                if(value == null){
                    continue;
                }
                populateMap.put(name,value);
            }
        }catch(Exception e){
            throw new RuntimeException("unable to get the value of the subject!");
        }
        return populateMap;
    }
}
