package com.wasu.springboot.integration.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtils {

    private static final String[] NUMBERS={"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};

    private static final String[] IUNIT={"元","拾","佰","仟","万","拾","佰","仟","亿",
            "拾","佰","仟","万","拾","佰","仟"};

    private static final String[] DUNIT={"角","分","厘"};

    public static String format(String v){
        return format(new BigDecimal(v));
    }

    public static String format(double v){
        return format(BigDecimal.valueOf(v));
    }

    public static String format(BigDecimal v){
        return new DecimalFormat("#,##0.00").format(round(v,2,BigDecimal.ROUND_HALF_UP));
    }

    public static int round(double v){
        return round(BigDecimal.valueOf(v),0,BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static BigDecimal round(BigDecimal v,int scale,int roundingMode){
        if(scale < 0 || roundingMode < 0){
            throw new IllegalArgumentException("scale must be a positive integer");
        }
        return v.divide(BigDecimal.ONE,scale,roundingMode);
    }

    public static BigDecimal add(String v1,String v2){
        return add(new BigDecimal(v1),new BigDecimal(v2));
    }

    public static BigDecimal add(double v1,double v2){
        return add(BigDecimal.valueOf(v1),BigDecimal.valueOf(v2));
    }

    public static BigDecimal add(BigDecimal v1,BigDecimal v2){
        return v1.add(v2);
    }

    public static BigDecimal subtract(BigDecimal v1,BigDecimal v2){
        return v1.subtract(v2);
    }

    public static BigDecimal multiply(BigDecimal v1,BigDecimal v2){
        return v1.multiply(v2);
    }

    public static BigDecimal divide(BigDecimal v1,BigDecimal v2){
        return divide(v1,v2,2,BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divide(BigDecimal v1,BigDecimal v2,int scale,int roundingMode){
        return v1.divide(v2,scale,roundingMode);
    }

    public static BigDecimal defaultRound(BigDecimal v){
        return round(v,2,BigDecimal.ROUND_HALF_UP);
    }

    public static String toChinese(String str){
        String tag="";
        if(str.indexOf("-") > -1){
            tag="负";
            str=str.replaceAll("-","");
        }
        str=str.replaceAll(",","");
        String decimalStr;
        String integerStr;
        if(str.indexOf(".") > 0){
            integerStr=str.substring(0,str.indexOf("."));
            decimalStr=str.substring(str.indexOf(".")+1);
        }else{
            if(str.indexOf(".") == 0){
                integerStr="";
                decimalStr=str.substring(1);
            }else{
                integerStr=str;
                decimalStr="";
            }
        }
        if(!integerStr.equals("")){
            integerStr=Long.toString(Long.parseLong(integerStr));
            if(integerStr.equals("0")){
                integerStr="";
            }
        }

        if(integerStr.length() > IUNIT.length){
            System.out.println(str+"超出处理能力");
            return str;
        }
        int[] integers = toArray(integerStr);
        boolean isMust5= isMust5(integerStr);
        int[] decimals=toArray(decimalStr);
        return tag+ getChineseInteger(integers,isMust5) + getChineseDecimal(decimals);
    }

    private static int[] toArray(String number){
        int[] array=new int[number.length()];
        for(int i=0;i<number.length();i++){
            array[i]=Integer.parseInt(number.substring(i,i+1));
        }
        return array;
    }

    private static String getChineseInteger(int[] integers,boolean isMust5){
        StringBuffer chineseInteger=new StringBuffer("");
        int length = integers.length;
        for(int i=0;i<length;i++){
            String key ="";
            if(integers[i] == 0){
                if(length - i == 13){
                    key = IUNIT[4];
                }else if (length - i == 9){
                    key = IUNIT[8];
                }else if ((length - i == 5) && (isMust5)){
                    key = IUNIT[4];
                }else if (length - i == 1){
                    key = IUNIT[0];
                }
                if((length - i > 1) && (integers[(i+1)] != 0)){
                    key=key+NUMBERS[0];
                }
            }
            chineseInteger.append(NUMBERS[integers[i]] + IUNIT[(length-i - 1)]);
        }
        return chineseInteger.toString();
    }

    private static String getChineseDecimal(int[] decimals){
        StringBuffer chineseDecimal = new StringBuffer("");
        for(int i=0;i<decimals.length;i++){
            if(i == 3){
                break;
            }
            chineseDecimal.append(NUMBERS[decimals[i]] + DUNIT[i]);
        }
        return chineseDecimal.toString();
    }

    private static boolean isMust5(String integerStr){
        int length = integerStr.length();
        if(length > 4){
            String subInteger = "";
            if(length > 8){
                subInteger=integerStr.substring(length - 8,length - 4);
            }else{
                subInteger=integerStr.substring(0,length - 4);
            }
            return Integer.parseInt(subInteger) > 0;
        }
        return false;
    }
}
