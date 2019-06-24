package com.wasu.springboot.integration.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

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

    public static String getStackTraceAsString(Throwable e) {
        StringWriter stringWriter=null;
        PrintWriter printWriter=null;
        StringBuffer error=null;
        try{
            stringWriter=new StringWriter();

            printWriter=new PrintWriter(stringWriter);

            e.printStackTrace(printWriter);

            error=stringWriter.getBuffer();
            return error.toString();
        }catch(Throwable localThrowable){
            try{
                if(printWriter != null){
                    printWriter.flush();
                    printWriter.close();
                }
            }catch(Exception e1){
                e1.printStackTrace();
            }
            try{
                if(stringWriter != null){
                    stringWriter.flush();
                    stringWriter.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        } finally{
            try{
                if(printWriter != null){
                    printWriter.flush();
                    printWriter.close();
                }
            }catch(Exception e1){
                e1.printStackTrace();
            }
            try{
                if(stringWriter != null){
                    stringWriter.flush();
                    stringWriter.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static String subString(String str,int length,boolean trim){
        if(isBlank(str)){
            return str;
        }
        str=trim?str.trim():str;
        str=str.length() > length? str.substring(0,length) : str;
        return str;
    }
}
