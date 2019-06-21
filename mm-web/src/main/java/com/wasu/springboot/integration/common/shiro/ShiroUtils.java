package com.wasu.springboot.integration.common.shiro;

import com.wasu.springboot.integration.entity.system.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


public final class ShiroUtils {

    private ShiroUtils(){}

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static Session getSession(){
        return getSubject().getSession();
    }

    public static ActiveUser getActiveUser(){
        return (ActiveUser)getSubject().getPrincipal();
    }

    public static void setAttribute(Object key,Object value){
        getSession().setAttribute(key,value);
    }

    public static  Object getAttribute(Object key){
        return getSession().getAttribute(key);
    }

    public static void logout(){
        getSubject().logout();
    }
}
