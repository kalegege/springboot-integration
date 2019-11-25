package com.wasu.springboot.integration.signature;


import com.wasu.springboot.integration.signature.coder.Base64Coder;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 吴国庆
 * @date 2019/5/9-10:15
 * @description: DSA签名校验
 */
public class SignatureDsaWorker {


	enum SignatureAlgorithm {
		/**  */
		SHA1withDSA, SHA224withDSA, SHA256withDSA
	}

//	public static final Logger log = LoggerFactory.getLogger(SignatureDsaWorker.class);

	public static final String ALGORITHM = "DSA";

	/** 密码强度 */
	public static final int DEFAULT_SIZE = 512;

	/** 密码对种子 */
	private static final String DEFAULT_SEED = "Q2VudHJhbGl6ZWQgT3BlcmF0aW5nIENlbnRlcg==";

	public static String sign (String data, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		return sign(data.getBytes(),privateKey);
	}

	public static boolean verify (String data, String publicKey, String sign) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		return verify(data.getBytes(), publicKey, sign);
	}

	/**
	 * 生成签名
	 * @param data 需要被签名的数据
	 * @param privateKey 经base64编码的私钥
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static String sign(byte[] data, String privateKey) throws InvalidKeySpecException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {

		/** 解码为byte */
		byte[] keyBytes = Base64Coder.decode(privateKey);
		/** 私钥配置	 */
		PKCS8EncodedKeySpec specification = new PKCS8EncodedKeySpec(keyBytes);
		/** 配置秘钥工厂及加密算法 */
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		/** 生成私钥对象 */
		PrivateKey key = keyFactory.generatePrivate(specification);
		/** 配置签名算法 */
		Signature signature = Signature.getInstance(SignatureAlgorithm.SHA256withDSA.name());
		signature.initSign(key);
		signature.update(data);
		/** 生成签名并编码 */
		byte[] sign = signature.sign();
		return Base64Coder.encode(sign);
	}

	/**
	 * 签名验证
	 * @param data 需要被验证的数据
	 * @param publicKey 经base64编码的公钥
	 * @param sign 需要被验证的签名
	 * @return
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws InvalidKeySpecException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {

		/** 解码为byte */
		byte[] keyBytes = Base64Coder.decode(publicKey);
		/** 公钥配置 */
		X509EncodedKeySpec specification = new X509EncodedKeySpec(keyBytes);
		/** 配置秘钥工厂及加密算法 */
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		/** 生成公钥对象 */
		PublicKey key = keyFactory.generatePublic(specification);
		/** 配置签名算法 */
		Signature signature = Signature.getInstance(SignatureAlgorithm.SHA256withDSA.name());
		signature.initVerify(key);
		signature.update(data);
		/** 签名解码 */
		byte[] signBytes = Base64Coder.decode(sign);
		/** 验证签名 */
		return signature.verify(signBytes);
	}

	public static KeyPair keyGenerator(int keySize, String seed) throws NoSuchAlgorithmException {

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		/** 初始化随机产生器 */
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(seed.getBytes());
		keyPairGenerator.initialize(keySize, secureRandom);

		return keyPairGenerator.generateKeyPair();
	}

//	public static void main(String [] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
//		String inputStr = "Centralized Operating Center";
//
//		int size = 512;
//		String seed = "Centralized Operating Center Channel LLT INPUT";
//
//		/** 构建密钥对 */
//
//		KeyPair keyPair = SignatureDsaWorker.keyGenerator(size, seed);
//
//		/** 分离公私钥 */
//		String publicKey = Base64Coder.encode(keyPair.getPublic().getEncoded());
//		String privateKey = Base64Coder.encode(keyPair.getPrivate().getEncoded());
//
//		JSONObject body = new JSONObject();
//		body.put("activityId",1186606926884544512L);
//		body.put("channelOrderId","6404280527023874112");
//		body.put("goodsId",1186606233745805312L);
//		body.put("mobile","10000000000");
//		body.put("noticeAddress","www.baidu.com");
//
//		String data = body.toJSONString() + "1573200244884";
//
//		System.out.println("原串:" + data);
//		System.out.println("公钥:" + publicKey);
//		System.out.println("私钥:" + privateKey);
//
//		/** 生成签名 */
//		String sign = SignatureDsaWorker.sign(data, privateKey);
//		System.out.println("签名:" + sign);
//
//		/** 验证签名 */
//		boolean status = SignatureDsaWorker.verify(data, publicKey, sign);
//		System.out.println("状态:" + status);
//	}

	public static void main(String [] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		String inputStr = "Centralized Operating Center";
		byte[] data = inputStr.getBytes();

		/** 构建密钥对 */
		KeyPair keyPair = SignatureDsaWorker.keyGenerator(SignatureDsaWorker.DEFAULT_SIZE, SignatureDsaWorker.DEFAULT_SEED);

		/** 分离公私钥 */
		String publicKey = Base64Coder.encode(keyPair.getPublic().getEncoded());
		String privateKey = Base64Coder.encode(keyPair.getPrivate().getEncoded());
		System.out.println("公钥:" + publicKey);
		System.out.println("私钥:" + privateKey);

		/** 生成签名 */
		String sign = SignatureDsaWorker.sign(data, privateKey);
		System.out.println("签名:" + sign);

		/** 验证签名 */
		boolean status = SignatureDsaWorker.verify(data, publicKey, sign);
		System.out.println("状态:" + status);
	}
}
