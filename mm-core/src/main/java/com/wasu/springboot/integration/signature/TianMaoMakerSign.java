package com.wasu.springboot.integration.signature;


import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cg
 * @title: TianMaoMakerSign
 * @description: TODO
 * @date 2019/10/16 2:22 PM
 */
public class TianMaoMakerSign {

    private static final  String SIGN_METHOD_MD5 = "md5";

    private static final  String SIGN_METHOD_HMAC = "hmac_md5";

    private static final  String CHARSET_UTF8 = "UTF-8";

    public static String signTopRequest(Map<String, String> params, String secret, String signMethod) throws IOException, NoSuchAlgorithmException {


        // 第一步:检查参数是否已经排序

        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(key)) {
                query.append(key).append(value);
            }
        }
        // 第三步：使用 MD5/HMAC 加密
        byte[] bytes;
        if (SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }
        // 第四步：把二进制转化为大写的十六进制(正确签名应该为 32 大写字符串，此方法需要时使用)
        return byte2hex(bytes);
    };


    public static byte[] encryptHMAC(String data, String secret) throws
            IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new
                    SecretKeySpec(secret.getBytes(CHARSET_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }


    public static byte[] encryptMD5(byte[] input) throws NoSuchAlgorithmException {
        // MD5 or "SHA-1"
        MessageDigest alg = MessageDigest.getInstance("MD5");
        alg.update(input);
        byte[] digest = alg.digest();
        return digest;
    }

    public static byte[] encryptMD5(String data) throws IOException, NoSuchAlgorithmException {
        return encryptMD5(data.getBytes(CHARSET_UTF8));
    }
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }



    public static void main(String[] args) {

       String token="80b7a5b496e34c19a2ef416206ec0719";
        String appId="91d831e6";
        String secret="9b47d0ba53194ba492bbdd0a2359cfc4";
         String exchangeId="343962908377096192";
        String actionId="343959530058162176";
        String orderId ="1121212";
        String mobile ="15010642191";
        //组装权益订购请求参数
        Map<String,String> map = new HashMap<>(6);
        map.put("actionId", "343959530058162176");
        map.put("exchangeId", "343962908377096192");
        map.put("reqOrderId","1231");
        map.put("mobile", "15010642191");
        map.put("appId", "91d831e6");
        map.put("token", "80b7a5b496e34c19a2ef416206ec0719");
        String sign = "";

        try {
            sign = signTopRequest(map,secret,SIGN_METHOD_MD5);
            System.out.println("sign:"+sign);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println();

        String baseUrl ="http://124.239.216.233:19028";
        String orderApi ="/top/exchange-centre/exchange";


    }

}
