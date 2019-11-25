package com.wasu.springboot.integration.signature.coder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author 吴国庆
 * @date 2019/5/9-10:15
 * @description: Base64编码器
 */
public class Base64Coder {

    /**
     * byte经base64编码生成密文
     * @param data byte
     * @return
     */
    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * 明文经base64编码生成密文
     * @param data 明文
     * @param charset 明文字符编码类型
     * @return
     */
    public static String encode(String data, Charset charset) {
        return Base64.getEncoder().encodeToString(data.getBytes(charset));
    }

    /**
     * 密文经base64解码生成byte
     * @param data 密文
     * @return
     */
    public static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    /**
     * 密文经base64解码生成明文
     * @param data 密文
     * @param charset 明文字符编码类型
     * @return
     */
    public static String decode(String data, Charset charset) {
        return new String (Base64.getDecoder().decode(data), charset);
    }

    public static void main(String [] args) {
        String data = "330782198610010835";
        String cipher = encode(data, StandardCharsets.UTF_8);
        String plain = decode("NDExMTIxMTk5MDA3MTAwMDM1", StandardCharsets.UTF_8);

        System.out.println("明文：" + data);
        System.out.println("密文：" + cipher);
        System.out.println("解码：" + plain);
    }
}
