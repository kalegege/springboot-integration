package com.wasu.springboot.integration.signature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;


/**
 * 
 * 数字签名生成与验证demo，生成数字签名，验证数字签名. <li>
 * 
 * 
 */
public class SignatureSha1Worker {
	
	public static final Logger log = LoggerFactory.getLogger(SignatureSha1Worker.class);

	/**
	 * 输入参数必须为utf-8编码
	 * @param str
	 * @return 根据输入字符串返回sha1的string串
	 * @throws UnsupportedEncodingException
	 */
	public static String signSha1Str(String str) throws Exception{

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			throw new Exception("sha1签名失败,签名串:"+str);
		}
	}

	/**
	 * 输入参数必须为utf-8编码
	 * @param str
	 * @return 根据输入字符串返回sha1的byte数组
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] signSha1Byte(String str) throws Exception{

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			return md;
		} catch (Exception e) {
			throw new Exception("sha1签名失败,签名串:"+str);
		}
	}

	/**
	 * 二进制转十六进制字符串
	 *
	 * @param bytes
	 * @return
	 */
	public static String byte2hex(byte[] bytes){
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++){
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if ( hex.length() == 1 ){
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}
}
