package com.wasu.springboot.integration.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String CHARSET_ENCODING = "UTF-8";

    public static boolean intern(String str1, String str2) {
        if (str1 == null) return str2 == null;

        str1 = str2.intern();

        return str1 == str2;
    }

    public static String propertyToFileName(String str) {
        if (isEmpty(str)) return str;

        if ((str.charAt(0) > 'A') && (str.charAt(0) < 'Z')) return str;

        if (Character.isUpperCase(str.charAt(0)))
            str = Character.toString(Character.toLowerCase(str.charAt(0))) + str.substring(1);

        for (int i = 1; i < str.length(); i++) {
            char a = str.charAt(i);
            if ((a >= 'A') && (a <= 'Z') && (str.charAt(i - 1) != '_')) {
                str = str.replace(String.valueOf(a), "_".concat(String.valueOf(a).toLowerCase()));
            }
        }

        return str;
    }

    public static String omit(String str, int num) {
        if (isBlank(str)) return null;
        if (str.length() < num) return str;

        return num > 0 ? str.substring(0, num) : str;
    }

    public static String limit(String str, int byteLength) {
        if (isBlank(str)) return null;
        if (byteLength <= 0) return null;
        try {
            if (str.getBytes(CHARSET_ENCODING).length <= byteLength) return str;
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        int index = 0;

        char[] arr = new char[1];
        while ((byteLength > 0) && (index < str.length())) {
            char c = str.charAt(index);
            arr[0] = c;
            if (!isChineseString(new String(arr))) {
                byteLength--;
            } else {
                byteLength--;
                byteLength--;
            }
            buffer.append(c);
            index++;
        }
        buffer.append("...");
        return buffer.toString();
    }

    private static boolean isChineseString(String string) {
        if (isBlank(string)) return false;

        String patternRange = "[\\u4E00-\\u9FA5]+";
        Pattern pattern = Pattern.compile(patternRange);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static String trimStr(String str) {
        return trimToEmpty(str).replaceAll("[\\r\\n]", "");
    }

    public static String trimForNotEmpty(String content) {
        if (null == content || content.length() == 0) {
            return null;
        }
        content = trimStr(content);
        return content.length() == 0 ? null : content;
    }

    /**
     * 过滤掉空白的字符串
     */
    public static String splitForFilterBlankStr(String content, String regex) {
        if (isBlank(regex) || isBlank(content)) {
            return content;
        }
        String[] array = content.split(regex);
        int length = array.length;
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < length; i++) {
            if (isBlank(array[i])) {
                continue;
            }
            sb.append(array[i]);
            if (i < length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }


    public static boolean stringListContain(String[] list, String value) {
        Boolean c = Boolean.valueOf(false);
        String[] arrayOfString = list;
        int j = list.length;
        for (int i = 0; i < j; i++) {
            String s = arrayOfString[i];
            if (s.equals(value)) {
                c = Boolean.valueOf(true);
                break;
            }
        }
        return c;
    }

    public static String getStackTraceAsString(Throwable e) {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;
        StringBuffer error = null;
        try {
            stringWriter = new StringWriter();

            printWriter = new PrintWriter(stringWriter);

            e.printStackTrace(printWriter);

            error = stringWriter.getBuffer();
            return error.toString();
        } catch (Throwable localThrowable) {
            try {
                if (printWriter != null) {
                    printWriter.flush();
                    printWriter.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                if (stringWriter != null) {
                    stringWriter.flush();
                    stringWriter.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.flush();
                    printWriter.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                if (stringWriter != null) {
                    stringWriter.flush();
                    stringWriter.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static String subString(String str, int length, boolean trim) {
        if (isBlank(str)) {
            return str;
        }
        str = trim ? str.trim() : str;
        str = str.length() > length ? str.substring(0, length) : str;
        return str;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 判断对象数组是否为空
     * @param object
     * @return
     */
    public static boolean isEmpty(Object[] object){
        return null == object || 1 == object.length;
    }

    /**
     * 判断对象是否为空
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object){
        if(null == object){
            return true;
        }
        if(object instanceof  String){
            return ((String) object).trim().isEmpty();
        }
        return !(object instanceof Number) ? false : false;
    }

    /**
     * 判断集合是否为空
     * @param object
     * @return
     */
    public static boolean isEmpty(List<?> object){
        return null == object || object.isEmpty();
    }

    /**
     * 判断map集合是否为空
     * @param object
     * @return
     */
    public static boolean isEmpty(Map<?,?> object){
        return null == object || object.isEmpty();
    }

    /**
     * 判断一个字符串是否完全由纯数字组成，字符串为空也返回false
     * @param str
     * @return
     */
    public static boolean isNumberic(String str){
        if(StringUtils.isBlank(str)){
            return false;
        }else{
            return str.matches("\\d*");
        }
    }

    /**
     * 合并对象为一个字符串
     * @param delim
     * @param objs
     * @return
     */
    public static String merageWithDelim(String delim,Object... objs){
        if(null == delim)delim="";

        StringBuilder sb=new StringBuilder();
        for(Object obj:objs){
            if(null == obj){
                sb.append("null");
            }else{
                if(obj.getClass().isArray()){
                    continue;
                }
                sb.append(obj.toString());
            }
            sb.append(delim);
        }
        return sb.toString();
    }

    /**
     * 替换参数中的特殊字符
     * @param value
     * @return
     */
    public static String replaceAllXss(String value){
        if(value == null) return null;
        value=value.replaceAll("<","&lt;").replaceAll(">","&gt;");
        value=value.replaceAll("\\(","&#40;").replaceAll("\\)","&#41;");
        value=value.replaceAll("'","&#39;");
        value=value.replaceAll("eval\\((.*)\\)","");
        value=value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']","\"\"");
        value=value.replaceAll("script","");
        return value;
    }

    /**
     * 字符串转对应的list 默认逗号分隔
     * @param str
     * @return
     */
    public static List<String> stringToList(String str){
        List<String> list=new ArrayList<>();
        if(isNotBlank(str)){
            list.addAll(Arrays.asList(str.split("\\s*,\\s*")));
        }
        return list;
    }

    /**
     * 字符串转对应的long类型的list 默认逗号分隔
     * @param str
     * @return
     */
    public static List<Long> stringToLongList(String str){
        List<Long> list=new ArrayList<>();
        if(isNotBlank(str)){
            List<String> list1=Arrays.asList(str.split("\\s*,\\s*"));
            for(String item:list1){
                list.add(Long.parseLong(item));
            }
        }
        return list;
    }

    /**
     * 字符串转对应的short类型的list 默认逗号分隔
     * @param str
     * @return
     */
    public static List<Short> stringToShortList(String str){
        List<Short> list=new ArrayList<>();
        if(isNotBlank(str)){
            List<String> list1=Arrays.asList(str.split("\\s*,\\s*"));
            for(String item:list1){
                list.add(Short.parseShort(item));
            }
        }
        return list;
    }

    /**
     * 驼峰法转下划线
     * @param para
     * @return
     */
    public static String hump2Underline(String para){
        if(isBlank(para)) return para;

        Pattern p=Pattern.compile("[A-Z]");
        Matcher m=p.matcher(para);
        StringBuffer stringBuffer=new StringBuffer();
        while(m.find()){
            m.appendReplacement(stringBuffer,"_"+m.group(0).toLowerCase());
        }
        m.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * 替换掉字符串中的下划线和百分号
     * @param context
     * @return
     */
    public static String replaceRegex(String context){
        return isBlank(context) ? null : context.replaceAll("\\_","\\\\_")
                .replaceAll("\\%","\\\\%");
    }

    /**
     * 验证规则是否匹配
     * @param str
     * @param patternStr
     * @return
     */
    public static boolean regularStr(String str,String patternStr){
        Pattern pattern=Pattern.compile(patternStr);
        Matcher matcher=pattern.matcher(str);
        return matcher.matches();
    }

}
