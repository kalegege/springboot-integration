package com.wasu.springboot.integration.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtils {

    /**
     * 设置cookie（生成时间为30分钟）
     * @param response
     * @param name
     * @param value
     */
    public static void setCookies(HttpServletResponse response,String name,String value){
        setCookies(response,name,value,60*30);
    }

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param path
     */
    public static void setCookies(HttpServletResponse response,String name,String value,String path){
        setCookie(response,name,value,path,60*30);
    }

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookies(HttpServletResponse response,String name,String value,int maxAge){
        setCookie(response,name,value,"/",maxAge);
    }

    public static void setCookies(HttpServletResponse response,String name,String value,int maxAge,String domain){
        setCookie(response,name,value,"/",maxAge,domain);
    }

    public static void setCookie(HttpServletResponse response,String name,String value,String path,int maxAge,String domain){
        Cookie cookie=new Cookie(name,null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        try{
            cookie.setValue(URLEncoder.encode(value,"utf-8"));
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        response.addCookie(cookie);
    }

    /**
     * 设置Cookie值
     * @param response
     * @param name
     * @param value
     * @param path
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response,String name,String value,String path,int maxAge){
        Cookie cookie=new Cookie(name,null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        try{
            cookie.setValue(URLEncoder.encode(value,"utf-8"));
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        response.addCookie(cookie);
    }

    /**
     * 获得制定Cookie值
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name){
        return getCookie(request,null,name,false);
    }

    /**
     * 获取指定Cookie并删除
     * @param request
     * @param response
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request,HttpServletResponse response, String name){
        return getCookie(request,response,name,true);
    }

    /**
     * 获得制定Cookies的值
     * @param request
     * @param response
     * @param name
     * @param isRemove
     * @return
     */
    public static String getCookie(HttpServletRequest request,HttpServletResponse response, String name,boolean isRemove){
        String value=null;
        Cookie[] cookies=request.getCookies();
        if(cookies != null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals(name)){
                    try{
                        value= URLDecoder.decode(cookie.getValue(),"utf-8");
                    }catch(UnsupportedEncodingException e){
                        e.printStackTrace();
                    }
                    if(isRemove){
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
        return value;
    }
}
