package com.wasu.springboot.integration.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatUtils {

    public static String formatPattern(String pattern,String number){
        BigDecimal v=new BigDecimal(number);
        return new DecimalFormat(pattern).format(v);
    }

    public static String format(String amount){
        return formatPattern("#,##0.00;(#)",amount);
    }
}
