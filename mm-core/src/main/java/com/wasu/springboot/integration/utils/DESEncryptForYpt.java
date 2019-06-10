package newdb.util;

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
			DESEncryptForYpt desForYpt = new DESEncryptForYpt();
			String Username = "kangmin";
			String ShowName = "康大敏";
			String Role = "测试小组组长";
			String Department = "测大金融终端开发部";

			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date afterDate = new Date(now .getTime() + 3600000000000L);

			String jumpurl = "/main.html#/officehall";
			String codeString= "浙政钉;" + Username + ";" + (ShowName == null ? "" : ShowName.replace(";", "；"))
					+ ";" + Role + ";" + sdf.format(afterDate);
			if (jumpurl != null)
				codeString += ";" + jumpurl+";"+Department;
			String token = desForYpt.encrypt(URLEncoder.encode(codeString,"UTF8"), "FB50C865");//  这个加密后值




			// 通过OA系统 访问各自系统   URL 后面跟token

			String url = "http://202.107.205.5:8705/stbrain/relativeUserLogin.do?token="+token;
			System.out.println(url);

			String key = "FB50C865";
			DESEncryptForYpt dd = new DESEncryptForYpt();
			String ew = dd.decrypt(token,key);
			System.out.println(ew);

//				String key = "QYQWINGS";
//	           DESEncryptForYpt dd = new DESEncryptForYpt();
//	           String ew = dd.decrypt("7Di4C5AiqyeGYa-IzU9IGjczvWwjEJo0UYJoq-r-ibB9j61PeWunu8eWiPjvohrL65VPxf5WfDPbmUR-YaQofZakaOA7w9ywJeiRCxLIK0m-IWc8dkfSx8LOzGfyT4ITa5SNs7vmFc7AVJ2TIaUpItaGgmunux-g7i5W-ZblGsvGF9PXBdbnCSxGY8Fc6EI8tGr!yE!rfSUYhA20rIXi3FweptyA-BrS9iDMGagZhbsVsYLkRRgvfJm1mzMPu989Om88hHRmprZEygyIQK7GxiysA7aYSN2H-nFQZXopPkR1Ee76TiOr4nFdg2AJAll1aqQufKK3kY61kXjPYve6Vo6QeDgMl0iRrMlQvD0RhiJGI-M6-6czu3ChOW2BEQMofw3Rxeitg-M2Nfk6R4lr2v51szunil0ekd6dZohNrZ93Ks2s0X!LXSG6V1SvSqzLjmKq5q4nkrOrQRHSpIiOoNmszVTGRAVB7tkKwVeXlTnuie0dXAfw6lRVIYIZhIeM2jPDrhOvoWlNYLze6p4D8mHL6XZg-6Z!CwnsTcMexAV3utkf02LAXmREmfxc3wB6cNTV2yOB1FUIakYfzeTslpDF5cnk9Q8rB2xdx7uPlwjvgVaPlQ86AIvs5hiTuTHL4JqehDt4l4syORE-8R3LsvnezFkRdTGvZVgwGQxLgs5yCNRwHGPxe2YhhmbaH8ze",key);
//	           System.out.println(ew);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}