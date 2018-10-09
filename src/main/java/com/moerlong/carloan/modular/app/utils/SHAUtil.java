package com.moerlong.carloan.modular.app.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class SHAUtil {

	public static String digest(String content) throws Exception {
		return Base64.encodeBase64URLSafeString(DigestUtils.sha256(content.getBytes("UTF-8")));
	}

}
