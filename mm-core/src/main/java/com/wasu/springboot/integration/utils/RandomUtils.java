package com.wasu.springboot.integration.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    /**
     * 获取几位随机数
     * @param num
     * @return
     */
    public static String getValidateCode(int num){
        ThreadLocalRandom random=ThreadLocalRandom.current();
        String hash = Integer.toHexString(random.nextInt());
        String validateCode=hash.substring(num);
        return validateCode;
    }
}
