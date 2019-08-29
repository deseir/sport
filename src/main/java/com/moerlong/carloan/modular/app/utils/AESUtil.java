package com.moerlong.carloan.modular.app.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

	private static final Logger LOG = LoggerFactory.getLogger(AESUtil.class);

	private static Cipher cipherEncrypt;

	private static Cipher cipherDecrypy;

	private static final String ALGORITHMS_AES = "AES/CBC/PKCS5Padding";

	private static final String CHARSET_NAME = "UTF-8";

	private static final String IV = "12345678ABCDEFGH";

	private static final String KEY = "dde/AUxVdy2MGTqtap1FXg==";

	static {
		try {
			cipherEncrypt = Cipher.getInstance(ALGORITHMS_AES);
			cipherDecrypy = Cipher.getInstance(ALGORITHMS_AES);
			Key key = new SecretKeySpec(Base64.decodeBase64(KEY), "AES");
			cipherEncrypt.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
			cipherDecrypy.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
		} catch (Exception e) {
			LOG.error("初始化ARSUtil工具类异常", e);
		}
	}

	public static String encrypt(String content, String aesKey) throws Exception {
		byte[] encrypt = cipherEncrypt.doFinal(content.getBytes(CHARSET_NAME));
		return Base64.encodeBase64URLSafeString(encrypt);
	}

	public static String decrypt(String content, String aesKey) throws Exception {
		byte[] encrypt = cipherDecrypy.doFinal(Base64.decodeBase64(content.getBytes(CHARSET_NAME)));
		return new String(encrypt, CHARSET_NAME);
	}

	public static void main(String[] args) {
		KeyGenerator kg;
		try {
			kg = KeyGenerator.getInstance("AES");
			kg.init(128);// 要生成多少位，只需要修改这里即可128, 192或256
			SecretKey sk = kg.generateKey();
			byte[] b = sk.getEncoded();
			System.out.println(Base64.encodeBase64String(b));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
