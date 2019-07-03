package com.wasu.springboot.integration.common.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class BoardSessionFactory implements SessionFactory {

    public static final String OLD_SESSION_ID="oldSessionId";
    private static final String REQUEST_KEY="org.apache.shiro.web.session.mgt.DefaultWebSessionContext.SERVLET_REQUEST";

    @Override
    public Session createSession(SessionContext initData) {
        Session session =null;
        if(initData != null){
            String host=initData.getHost();
            if(host != null){
                session = new SimpleSession(host);
            }
            if(initData.get(REQUEST_KEY) != null){
                String oldSessionId=null;
                Cookie[] cookies=((ShiroHttpServletRequest)initData.get(REQUEST_KEY)).getCookies();
                if(cookies != null){
                    for(Cookie cookie:cookies){
                        if(cookie.getName().equals("SHAREJESSIONID")){
                            try{
                                oldSessionId= URLDecoder.decode(cookie.getValue(),"utf-8");
                            }catch(UnsupportedEncodingException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
                session.setAttribute(OLD_SESSION_ID,oldSessionId);
            }
        }else{
            session=new SimpleSession();
        }
        return session;
    }
}
