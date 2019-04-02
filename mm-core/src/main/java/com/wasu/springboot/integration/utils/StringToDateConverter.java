package com.wasu.springboot.integration.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        if((source.equals("")) || (source.equals(null)))
            return null;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try{
            return dateFormat.parse(source);
        }catch(ParseException e){
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try{
                return dateFormat.parse(source);
            }catch(ParseException ee){
                ee.printStackTrace();
            }
        }
        return null;
    }
}
