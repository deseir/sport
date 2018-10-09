package com.moerlong.carloan.util;

import org.springframework.util.StringUtils;

import java.util.Calendar;

public class CalculateNumberUtils {
	
	/**
	 * 根据身份证号获取用户的年龄.
	 */
	public static  int getUserAge(String idNo) {
		if (StringUtils.isEmpty(idNo)) return 0;
		Calendar ca = Calendar.getInstance();
		int nowYear = ca.get(Calendar.YEAR);
		int nowMonth = ca.get(Calendar.MONTH) + 1;
		int len = idNo.length();
		if (len == 18) {
			int IDYear = Integer.parseInt(idNo.substring(6, 10));
			int IDMonth = Integer.parseInt(idNo.substring(10, 12));
			if ((IDMonth - nowMonth) > 0) {
				return nowYear - IDYear - 1;
			} else
				return nowYear - IDYear;
		} else {
			// 错误的身份证号
			return 0;
		}
	}
	
}
