package com.wasu.springboot.integration.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
import java.net.URLEncoder;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DESEncryptForYpt
{
	private static String strDefaultKey = "FB50C865"; //默认密钥

	/**
	 * 加密字节数组
	 *
	 * @param arrB
	 *            需加密的字节数组
	 * @param key
	 *            密钥
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] arrB, String key) throws Exception {
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
		byte[] ivkey = key.getBytes();
		// "DES" 说明 加密算法是什么对称算法
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec ivp = new IvParameterSpec(ivkey);

		Cipher encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivp);

		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 *
	 * @param xml
	 *            需加密的字符串
	 * @param key
	 *            密钥
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encrypt(String xml, String key) throws Exception {
		// getBytes() 编码不一样 得到的结果不一样
//		return  ToSpecialBase64(encrypt(xml.getBytes(), key));
		return  ToSpecialBase64(encrypt(xml.getBytes("UTF-16LE"), key));
//		return  ToSpecialBase64(encrypt(xml.getBytes("UTF-8"), key));
	}

	/**
	 * 使用默认公钥加密字符串
	 * @param xml 需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encrypt(String xml) throws Exception {
		return encrypt(xml, strDefaultKey);
	}

	/**
	 * 解密字节数组
	 *
	 * @param arrB
	 *            需解密的字节数组
	 * @param key
	 *            密钥
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] arrB, String key) throws Exception {
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
		byte[] ivkey = key.getBytes();
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec ivp = new IvParameterSpec(ivkey);

		Cipher decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivp);

		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 *
	 * @param xml
	 *            需解密的字符串
	 * @param key
	 *            密钥
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decrypt(String xml, String key) throws Exception {
		byte [] source = DESEncryptForYpt.fromSpecialBase64(xml);
		byte[] data=decrypt(source, key);//new byte[]{66, 52,  51,  48,53, 69,  69,  67};
		byte[] temData = new byte[65535];
		int kk=0;
		for(int i=0;i<data.length;i++){
			if(data[i]>0){
				temData[kk]=data[i];
				kk++;
			}
		}
		String sourceStr = new String(temData,"UTF-8");
		return sourceStr;
	}
	/// <summary>
	/// Base64特定转换 +转换为!  /转换为 -   = 转换为 _  ps : 加入 url 加密之后 辨别出来
	/// </summary>
	/// <param name="data"></param>
	/// <returns></returns>
	public static String ToSpecialBase64(byte[] data)
	{
		String result = "";
		if (data != null)
		{
			// 字节转成字符串
			result = DatatypeConverter.printBase64Binary(data);
//         	result = DatatypeConverter.printHexBinary(data);
			// 去掉符号 加入url 的时候，防止字符串冲突
			result= result.replaceAll("\\+", "!");
			result = result.replaceAll("/", "-");
			result = result.replaceAll("=", "_");

		}
		return result;
	}
	/**
	 * 转成指定 字节
	 */
	public static byte[]  fromSpecialBase64(String base64)
	{
		base64 = base64.replaceAll("\\!", "+");
		base64 = base64.replaceAll("\\-", "/");
		base64 = base64.replaceAll("_", "=");
		return DatatypeConverter.parseBase64Binary(base64);
////	        return DatatypeConverter.parseHexBinary(base64);
	}
	/**
	 * 使用默认公钥解密字符串
	 * @param xml 需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decrypt(String xml) throws Exception {
		return decrypt(xml, strDefaultKey);
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 *
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws Exception
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	/**
	 * 获取默认密钥
	 * @return
	 */
	public static String getDesKey() {
		return DESEncryptForYpt.strDefaultKey;
	}

	public static void main(String[] args) {
		try {
			//测试密匙
//			DESEncryptForYpt desForYpt = new DESEncryptForYpt();
//			String Username = "kangmin";
//			String ShowName = "康领导";
//			String Role = "测试小组组长";
//			String Department = "厅领导";
//
//			Date now = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			Date afterDate = new Date(now .getTime() + 3600000000000L);
//
//			String jumpurl = "/main.html#/officehall";
//			String codeString= "浙政钉;" + Username + ";" + (ShowName == null ? "" : ShowName.replace(";", "；"))
//					+ ";" + Role + ";" + sdf.format(afterDate);
//			if (jumpurl != null)
//				codeString += ";" + jumpurl+";"+Department;
//			String token = desForYpt.encrypt(URLEncoder.encode(codeString,"UTF8"), "FB50C865");//  这个加密后值
//



			// 通过OA系统 访问各自系统   URL 后面跟token

//			String url = "http://202.107.205.5:8705/stbrain/relativeUserLogin.do?token="+token;
//			System.out.println(url);

			String key = "FB50C865";
//			DESEncryptForYpt dd = new DESEncryptForYpt();
//			String ew = dd.decrypt(token,key);
//			System.out.println(ew);
			String token1="K6JxoIKjKoHiDi3GUEZGGp03Cn9nJJNw!IhL8MfPytR3YcxLijSOU6snFsMNVkY7xWxrMUAsD0JH45KzmKWBACfwH0-OSB36BMK1x5PVcwNc9bAv3XpiJlh2DoyuUNldfpVClVTvUcYxaFllklB!U1FgER-LzKntvR1nuEKPjCIAR44eG3dAN1BKSYZiGWWmUUgFVOWMd7q6tPiZccQEqfbBiz7wI52hOSUz1-678ogKi4G2SUm9Tdt9pGOckhm-go8JIajh7lZUWgMSMzpIcLHAy5Co-F!6cSRkU2B1bZI!fcNU4D!Y4IHaV56YdKLL4MkbgWno72yA69DN582OoIZu9jh4kd!FzukdeWHeoU72s-NqrKYDsgP-cHu6U5fg-0nVkWA8gPdwqDPm5dUaucMPSSc2cCKF";
			String token="K6JxoIKjKoHiDi3GUEZGGp03Cn9nJJNw!IhL8MfPytR3YcxLijSOU6snFsMNVkY7xWxrMUAsD0KhxNIpw3TDSWnQfTbhkO-sNw06MEtZhDby3wSYU5sNxYTEz0BZHk1!Dus3ed2jqZsQpFUKDblt9CbyGN9TxC7jtnqJSO!pJSpxA1a324QN4MwtjVc4hlnWp12Bxy5ouGCql!PEYA2drXzCChWPg3gVTKkFz4buUhnVRgqhCUPzkWnM-kePCKWrv98-2XIQz0hn5e-6xV9HgowPrpoV4lqzNAjqIi!32UDfyNpGeceQDF1n5VgVrQaSC!m!Qt3X9EJ1HtpEimseHWg8HjdAJwRCknT3zUtykf17TQyoHVpVsLschYNWg0EP0hFTrrps8fGS3yl7jPbfsVS8nHknWYySJAED-4AzJIs_";
//				String key = "QYQWINGS";
			String ew2 = DESEncryptForYpt.decrypt(token1);
	        System.out.println(ew2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}