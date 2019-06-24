package com.wasu.springboot.integration.utils;

import java.util.regex.Pattern;

public class RegularUtils {

    public RegularUtils() {
    }

    /**
     * 密码规则
     */
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9]+$";

    /**
     * 密码编译
     */
    private static final Pattern PASSWORD_PATTERN=Pattern.compile(PASSWORD_REGEX);

    /**
     * 邮件规则
     */
    private static final String EMAIL_REGEX="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";

    /**
     * 邮件编译
     */
    private static final Pattern EMAIL_PATTERN=Pattern.compile(EMAIL_REGEX);

    /**
     * 18位身份证号
     */
    public static final String IDENTIFY_PATTERN = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";

    /**
     * 年月日格式
     */
    public static final String YEAR_MONTH_DAY_PATTERN = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";

    /**
     * ip地址格式
     */
    public static final String IP_PATTERN = " (2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}";

    /**
     * 自定义ip校验
     * @param ip
     * @return
     */
    public static boolean customMatcherIp(String ip){
        if(StringUtils.isBlank(ip)){
            return false;
        }
        if(ip.length() > 15){
            return false;
        }
        String[] partArray = ip.split("\\.");
        if(partArray.length != 4){
            return false;
        }
        try{
            Integer partValue;
            for(String part:partArray){
                partValue = Integer.valueOf(part);
                if(partValue > 255){
                    return false;
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
