package com.wasu.springboot.integration.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinUtils {

    public static int pinyinCompare(String o1,String o2){
        if(o1 == null || o2 == null){
            return (o1 == null ? 0 : 1) - (o2 == null ? 0 : 1);
        }
        for(int i =0 ; i< o1.length() && i < o2.length() ; i++){
            int codePoint1=o1.charAt(i);
            int codePoint2=o2.charAt(i);

            if(codePoint1 != codePoint2){
                if(Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)){
                    return codePoint1 - codePoint2;
                }

                String pinyin1=pinyin((char)codePoint1);
                String pinyin2=pinyin((char)codePoint2);

                if(pinyin1 != null && pinyin2 != null){
                    if(!pinyin1.equals(pinyin2)){
                        return pinyin1.compareTo(pinyin2);
                    }
                }else{
                    return codePoint1 - codePoint2;
                }
            }
        }
        return o1.length() - o2.length();
    }

    private static String pinyin(char c) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
        return  pinyins == null ? null : pinyins[0];
    }
}
