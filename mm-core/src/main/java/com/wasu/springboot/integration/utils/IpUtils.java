package com.wasu.springboot.integration.utils;

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
}
