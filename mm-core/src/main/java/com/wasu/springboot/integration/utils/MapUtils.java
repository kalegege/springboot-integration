package com.wasu.springboot.integration.utils;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.Map.Entry;

public class MapUtils extends org.apache.commons.collections.MapUtils {

    public static Map<String,Object> populateMap(Object data){
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

    /**
     * 将一个bean转为map
     * @param populateMap
     * @param data
     * @return
     */
    public static Map<String,Object> populateMapString(Map<String,Object> populateMap,Object data){
        if(populateMap == null || data == null) throw new RuntimeException("populateMap and data is not empty!");

        BeanUtilsBean beanUtilsBean=BeanUtilsBean.getInstance();
        PropertyDescriptor[] pds =beanUtilsBean.getPropertyUtils().getPropertyDescriptors(data);
        try{
            for(PropertyDescriptor pd:pds){
                if("class".equals(pd.getName())) continue;

                String name=pd.getName();
                Object value=beanUtilsBean.getPropertyUtils().getSimpleProperty(data,name);
                if(value == null){
                    continue;
                }
                populateMap.put(name,value);
            }
        }catch(Exception e){
            throw new RuntimeException("unable to get the value of the subject");
        }
        return populateMap;
    }

    public static String transMapToString(Map map){
        if(map == null){
            return "";
        }

        Entry entry;
        StringBuffer sb=new StringBuffer();
        for(Iterator iterator=map.entrySet().iterator();iterator.hasNext();){
            entry=(Entry)iterator.next();
            sb.append(entry.getKey().toString()).append("=").append(null == entry.getValue()?"":entry.getValue().toString()).append(iterator.hasNext()?";":"");
        }
        return sb.toString();
    }

    public static <K,V> List<V> valueToList(Map<K,V> map){
        if(MapUtils.isEmpty(map)){
            throw new RuntimeException("map can not be empty!");
        }
        List<V> list=new ArrayList<V>();
        for(Entry<K,V> entry:map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

    public static <K,V> List<K> keyToList(Map<K,V> map){
        if(MapUtils.isEmpty(map)){
            throw new RuntimeException("map can not be empty!");
        }
        List<K> list=new ArrayList<K>();
        for(Entry<K,V> entry:map.entrySet()){
            list.add(entry.getKey());
        }
        return list;
    }
}
