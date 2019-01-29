package com.wasu.springboot.integration.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtils {

    /**
     * 获取服务器ip地址
     * @return
     */
    public static String getLocalHostAddress(){
        InetAddress inet=null;
        try{
            inet=InetAddress.getLocalHost();
        }catch(UnknownHostException e){

        }
        return inet != null? inet.getHostAddress():"";
    }
}
