package com.wasu.springboot.integration.signature;

import com.wasu.springboot.integration.signature.coder.Base64Coder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author 吴国庆
 * @date 2019/6/5-18:34
 * @description: Hmac签名
 */
public class SignatureHmacWorker {

    private static Logger logger = LoggerFactory.getLogger(SignatureHmacWorker.class);
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String HMAC_SHA256 = "HmacSHA256";

    public static String sign (String signInput, String key, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        Mac mac = Mac.getInstance(algorithm);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        mac.init(secretKey);
        byte[] bytes = mac.doFinal(signInput.getBytes(StandardCharsets.UTF_8));
        URLEncoder.encode(Base64Coder.encode(bytes), StandardCharsets.UTF_8.name());
        String sign = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        return sign;
    }
}
