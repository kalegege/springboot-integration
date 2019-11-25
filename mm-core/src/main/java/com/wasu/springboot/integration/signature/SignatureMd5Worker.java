package com.wasu.springboot.integration.signature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * 数字签名生成与验证demo，包括生成DSA私钥/公钥，生成数字签名，验证数字签名. <li>
 * 订购接口WABP方会将WABP方的公钥，提前提供给各AP，供接口交互时校验WABP发送的数据及签名正确性.<li>
 * 退订接口AP提前将公钥提供给WABP侧，供接口交互时校验AP发送的数据及签名正确性.
 * 
 * 
 */
public class SignatureMd5Worker {
	
	public static final Logger log = LoggerFactory.getLogger(SignatureMd5Worker.class);

	public static String signMD5(String key, SignatureMap map) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		//TODO:参数异常处理
		return signMD5(map.sortAsc() + key);
	}

	/**
	 * 输入参数必须为utf-8编码
	 * @param source
	 * @return 根据输入字符串返回md5值
	 * @throws UnsupportedEncodingException
	 */
	public static String signMD5(String source) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] bt = source.getBytes("UTF-8");
		messageDigest.update(bt);
		return BytesHexTransform.bytesToHexString(messageDigest.digest());
	}

	/**
	 * 输入参数必须为utf-8编码
	 * @param str
	 * @return 根据输入字符串返回sha1的string串
	 * @throws UnsupportedEncodingException
	 */
	public static String getSha1Str(String str) throws Exception{

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
	public static byte[] getSha1Byte(String str) throws Exception{

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
}
