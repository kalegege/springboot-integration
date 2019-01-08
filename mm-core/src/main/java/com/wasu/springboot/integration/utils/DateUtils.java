package com.wasu.springboot.integration.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String[] DAYNAMES={"星期日","星期一"};
    private static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:ss:SSS";

    public static String formatDateTime(Date date, String format){
        if(date == null) return null;

        DateFormat dateFormat=new SimpleDateFormat(format);

        return dateFormat.format(date);
    }
}
