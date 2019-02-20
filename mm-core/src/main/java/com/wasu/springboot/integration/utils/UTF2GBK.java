package com.wasu.springboot.integration.utils;

public class UTF2GBK {
    public static String gbk2utf8(String gbk){
        String lTemp=gbk2Unicode(gbk);
        lTemp=unicodeToUtf8(lTemp);
        return lTemp;
    }

    private static String unicodeToUtf8(String dataStr) {
        int index=0;
        StringBuffer buffer=new StringBuffer();

        int liLen=dataStr.length();
        while(index<liLen){
            if(index >= liLen -1 || !"\\u".equals(dataStr.substring(index,index+2))){
                buffer.append(dataStr.charAt(index));
                index++;
                continue;
            }
            String charStr="";
            charStr=dataStr.substring(index+2,index+6);

            char letter=(char)Integer.parseInt(charStr,16);

            buffer.append(letter);
            index+=6;
        }
        return buffer.toString();
    }

    private static String gbk2Unicode(String str) {
        StringBuffer result=new StringBuffer();
        for(int i=0;i<str.length();i++){
            char chr1=(char)str.charAt(i);
            if(!isNeedConvert(chr1)){
                result.append(chr1);
                continue;
            }
            result.append("\\u"+Integer.toHexString((int)chr1));
        }
        return result.toString();
    }

    private static boolean isNeedConvert(char para) {
        return ((para &(0x00FF)) != para);
    }


}
