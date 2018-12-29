package com.wasu.springboot.integration.utils;

public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static final String CHARSET_ENCODING="UTF-8";

    public static boolean intern(String str1,String str2){
        if(str1 == null) return str2 == null;

        str1 = str2.intern();

        return str1 == str2;
    }

    public static String propertyToFileName(String str){
        if(isEmpty(str)) return str;

        if((str.charAt(0) > 'A') &&(str.charAt(0) < 'Z')) return str;

        if(Character.isUpperCase(str.charAt(0)))
            str = Character.toString(Character.toLowerCase(str.charAt(0)))+str.substring(1);

        for(int i =1; i< str.length(); i++){
            char a = str.charAt(i);
            if((a >= 'A') && (a <= 'Z') && (str.charAt(i -1) != '_')){
                str = str.replace(String.valueOf(a),"_".concat(String.valueOf(a).toLowerCase()));
            }
        }

        return str;
    }

    public static String omit(String str,int num){
        if(isBlank(str)) return null;
        if(str.length()<num) return str;

        return num > 0 ? str.substring(0,num):str;
    }
}
