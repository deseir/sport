package com.moerlong.carloan.modular.app.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TokenGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(TokenGenerator.class);

	private static final int SOURCE_LENGTH = 80;

	private static final char PADDING = '=';

	public static final String REDIS_TOKEN_KEY = "TOKEN:";
	public static final String REDIS_EXPIREDTOKEN_KEY = "EXPIRED_TOKEN:";

	//短信验证码相关
	public static final String REDIS_SMS_KEY = "SMS_KEY:";
	public static final int SMS_CODE_TIMEOUT = 60*10;	//10分钟

	public static final int TOKEN_EXPIRED_TIMEOUT = 60 * 60 * 24 * 30;			//30天

	public static final int EXPIREDTOKEN_EXPIRED_TIMEOUT = 60 * 60 * 24 * 60; // 六十天

	public static String getTokenId(UserTokenInfoModel tokenModel) {
		if (tokenModel == null) {
			LOG.error("tokenmodel参数为空！");
			return "";
		}
		String model = JSON.toJSONString(tokenModel);
		char[] paddingList = new char[0];
		if (model.length() <= SOURCE_LENGTH) {
			paddingList = new char[SOURCE_LENGTH - model.length()];
			Arrays.fill(paddingList, PADDING);
		} else {
			LOG.error("tokenmodel参数字符串长度大于规定长度:{}", model.length());
			return "";
		}
		try {
			return AESUtil.encrypt(model + new String(paddingList), null) + SHAUtil.digest(model);
		} catch (Exception e) {
			LOG.error("加密签名异常", e);
			return "";
		}
	}

	public static String getTokenId(long id, long expiredTime) {
		if (id == 0 || expiredTime == 0) {
			LOG.error("getTokenId id null");
			return "";
		}
		UserTokenInfoModel userToken = new UserTokenInfoModel();
		userToken.setId(id);
		userToken.setExpiredTime(expiredTime);
		return getTokenId(userToken);
	}

	public static long getTokenExpiredTime() {
		return System.currentTimeMillis() + TOKEN_EXPIRED_TIMEOUT * 1000L;
	}

	public static long getExpiredTokenExpiredTime() {
		return System.currentTimeMillis() + EXPIREDTOKEN_EXPIRED_TIMEOUT * 1000L;
	}
}
