package com.wasu.demo;

import java.util.regex.Pattern;

public class MainTest {

    private static final String PATTERN_IP=" (2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}";

    public static void main(String[] args){
        String ip="127.0.0.1";
        boolean result= Pattern.compile(PATTERN_IP).equals(ip);

    }
}
