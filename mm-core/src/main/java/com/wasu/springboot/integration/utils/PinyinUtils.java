package com.wasu.springboot.integration.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {

    private static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

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

    /**
     * 传入字符串 返回拼音字符串
     * @param str
     * @return
     */
    public static String getStringPinYin(String str){
        StringBuilder sb = new StringBuilder();
        String tempPinyin = null;
        for(int i = 0; i < str.length(); ++i){
            tempPinyin =getCharacterPinYin(str.charAt(i));
            if(tempPinyin == null){
                // 如果str.charAt(i)非汉字，则保持原样
                sb.append(str.charAt(i));
            }else{
                sb.append(tempPinyin);
            }
        }
        return sb.toString();
    }

    private static String getCharacterPinYin(char c) {
        String[] pinyin =null;
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try{
            pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
        }catch(BadHanyuPinyinOutputFormatCombination e){
            e.printStackTrace();
        }
        // 如果c不是汉字，toHanyuPinyinStringArray会返回null
        if(pinyin == null || pinyin.length <= 0) return null;
        // 只取一个发音，如果是多音字，仅取第一个发音
        return pinyin[0];
    }

    public static void main(String[] args){
        String message="荆溪白石出，Hello 天寒红叶稀。android 山路元无雨，What's up?   空翠湿人衣。";
        String gushi = "锄禾日当午，汗滴禾下土，谁知盘中餐，粒粒皆辛苦";
        System.out.println(getStringPinYin(gushi));
    }
}
