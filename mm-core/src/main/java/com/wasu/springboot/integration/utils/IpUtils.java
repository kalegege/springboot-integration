package com.wasu.springboot.integration.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class IpUtils {

    public static String getIpStr(){

        Set<String> ipSet=new HashSet<>();
        Enumeration<NetworkInterface> interfaces=null;
        try{
            interfaces=NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()){
                NetworkInterface ni =interfaces.nextElement();

                Enumeration<InetAddress> ips=ni.getInetAddresses();
                while(ips.hasMoreElements()){
                    String ip=ips.nextElement().getHostAddress();
                    if(ip.split("\\.").length == 4){
                        ipSet.add(ni.getName()+"/"+ip);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ipSet.toString();
    }

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

    public static String getRemoteIp(HttpServletRequest request){
        String ipAddress=null;
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            ipAddress=request.getHeader("x-forwardeded-for");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            ipAddress=request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            ipAddress=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            ipAddress=request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                ipAddress=getLocalHostAddress();
            }
        }
        if(StringUtils.isBlank(ipAddress)) return "";

        if(ipAddress != null && ipAddress.length() > 15){
            if(ipAddress.indexOf(",") > 0){
                ipAddress=ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
