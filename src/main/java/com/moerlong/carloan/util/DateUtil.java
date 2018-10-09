package com.moerlong.carloan.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

	public static final String DEFAULT_START_DATE = "1900-1-1";

	public static final String YMD = "yyyy-MM-dd";

	public static final String CN_YMD = "yyyy年MM月dd日";

	public static final String YMDH = "yyyy-MM-dd HH";

	public static final String YMDHM = "yyyy-MM-dd HH:mm";

	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

	public static final String YMDHMSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String YMDHMSS_STR = "yyyyMMddHHmmssSSS";

	public static final String MD = "MM-dd";

	public static final String HMS = "HH:mm:ss";

	public static final String YMDHMS_STR = "yyyyMMddHHmmss";

	public static final String Y = "yyyy";

	public static final String M = "MM";

	public static final String D = "dd";

	public static final String H = "HH";

	public static final String YM = "yyyyMM";
	public static final String YMD_YMD = "yyyyMMdd";
	public static final String DHMS = "ddHHmmssSSS";
	public static final String MMDD = "MMdd";

	public static final Date DEFAULT_DB_TIME = str2Date("1000-01-01 00:00:00", DateUtil.YMDHM);


	public static String format(Date date,String dateFormat) {
		if(date == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(date);
	}


	/**
	 * 计算两个时间的差值,m-s=r
	 * 
	 * @param minuend
	 *            减数
	 * @param subtrahend
	 *            被减数
	 * @return 两个时间的差值
	 */
	public static int timesBetween2Value(Object minuend, Object subtrahend) {
		int times = 0;
		int minuendInt = 0;
		int subtrahendInt = 0;
		if (minuend instanceof String) {
			minuendInt = Integer.parseInt((String) minuend);
		} else if (minuend instanceof Long) {
			minuendInt = ((Long) minuend).intValue();
		}

		if (subtrahend instanceof String) {
			subtrahendInt = Integer.parseInt((String) subtrahend);
		} else if (subtrahend instanceof Long) {
			subtrahendInt = ((Long) subtrahend).intValue();
		}
		if (minuendInt >= subtrahendInt) {
			times = minuendInt - subtrahendInt;
		} else {
			times = minuendInt + 86400 - subtrahendInt;
		}
		return times;
	}

	/**
	 * 计算两个时间的差值,m-s=r
	 * 
	 * @param minuend
	 *            减数
	 * @param subtrahend
	 *            被减数
	 * @return 两个时间的差值
	 */
	public static int timesBetween2Value(int minuend, int subtrahend) {
		int times = 0;
		if (minuend >= subtrahend) {
			times = minuend - subtrahend;
		} else {
			times = minuend + 86400 - subtrahend;
		}
		return times;
	}

	/**
	 * 计算当前日期是一年中的第几天，并返回当前年份的最后一位，如2013年1月1日，则返回3001
	 * 
	 * @return
	 */
	public static String getTotalDays() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String str = String.format("%tj", date); // 得到time日期是在这年的第几天
		return String.valueOf(year).substring(3) + str;
	}

	/**
	 * 计算当前时间是一天中的第几秒
	 * 
	 * @return TotalSeconds
	 */
	public static int getTotalSeconds() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return hour * 60 * 60 + minute * 60 + second;
	}

	/**
	 * 得到两天后的当前时间YMDHMS字符串
	 * 
	 * @return
	 */
	public static String getNextDayYMDHMSStr() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 2);
		return date2str(c.getTime(), YMDHMS);
	}

	/**
	 * 将java.util.Date 按指定格式转化为String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2str(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将string 按指定格式转化为java.util.Date
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date str2Date(String str, String format) {
		if ((str == null) || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将string 按指定格式转化为java.util.Date
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date str2DatewithExp(String str, String format) throws Exception {
		if ((str == null) || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 返回当前java.sql.Timestamp类型时间
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 将string 按指定格式转化为java.sql.Timestamp
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Timestamp str2Timestamp(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return new Timestamp(sdf.parse(str).getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将Date 按照 format 进行格式化
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null || StringUtils.isBlank(format)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 验证字符串是否为合法日期格式 支持YYYY-MM-DD OR YYYY-MM-DD HH:mm:ss
	 * 
	 * @param dateString
	 */
	public static boolean validateDateFormat(String dateString) {
		Boolean validate = Boolean.FALSE;
		String reg1 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|"
				+ "([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		String reg2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";

		Pattern p1 = Pattern.compile(reg1);
		Pattern p2 = Pattern.compile(reg2);
		Matcher m1 = p1.matcher(dateString);
		Matcher m2 = p2.matcher(dateString);
		if (m1.matches() || m2.matches()) {
			validate = Boolean.TRUE;
		}
		return validate;
	}

	/**
	 * 验证字符串是否为合法日期格式 支持YYYY-MM-DD HH:mm:ss 这个方法很重要，请不要再修改
	 * 
	 * @param dateString
	 * @return boolean
	 */
	public static boolean validateDateFormat2(String dateString) {
		Boolean validate = Boolean.FALSE;
		String reg1 = "^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)"
				+ "-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$";

		Pattern p1 = Pattern.compile(reg1);
		Matcher m1 = p1.matcher(dateString);
		if (m1.matches()) {
			validate = Boolean.TRUE;
		}
		return validate;
	}

	/**
	 * 比较传入的日期（格式：yyyy-MM-dd hh:MM:ss）与当前日期的时间差
	 * 
	 * @param dateString
	 * @return int
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	public static int dateMDate(String dateString) throws Exception {
		SimpleDateFormat parseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = parseTime.parse(dateString);
		new Date();
		Calendar cpcalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		cpcalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		cpcalendar.setTime(date);

		Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		long i = c2.getTimeInMillis() - cpcalendar.getTimeInMillis();
		return (int) (i / 1000);
	}

	/**
	 * 计算两个日期之间的相差的天数，不计时分秒
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws Exception
	 */
	public static int daysBetween(Date smdate, Date bdate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * 计算两个日期之间的相差的分钟数
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws Exception
	 */
	public static int minuteBetween(Date smdate, Date bdate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(YMDHMS);
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long betweenMinutes = (time2 - time1) / (1000 * 60);

		return Integer.parseInt(String.valueOf(betweenMinutes));
	}

	public static long secondBetween(Date startDate, Date endDate) throws Exception {
		return (endDate.getTime() - startDate.getTime()) / 1000;
	}

	/**
	 * 判断开始时间是否小于等于结束时间
	 * 
	 * @param smalltime
	 *            开始时间
	 * @param largeTime
	 *            结束时间
	 * @return
	 */
	public static boolean validateDateTDate(String smalltime, String largeTime) {
		Boolean validate = Boolean.FALSE;
		try {
			SimpleDateFormat parseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date smallDate = parseTime.parse(smalltime);
			Date largeDate = parseTime.parse(largeTime);
			if (smallDate.equals(largeDate)) {
				return true;
			}
			boolean isLarge = smallDate.before(largeDate);
			return isLarge;
		} catch (Exception e) {
			return validate;
		}
	}

	/**
	 * 将制定日期向 向后推若干分钟
	 * 
	 * @param startTime
	 *            日期
	 * @param compartTime
	 *            要推迟的分钟数 正数 向后 负数向前
	 * @return
	 */
	public static Date compartDate(Date startTime, int compartTime) {
		if (null == startTime) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startTime);
		c.add(Calendar.MINUTE, compartTime);
		return c.getTime();
	}

	/**
	 * 获得对某个时间单位进行偏移之后时间 如getDate(new Date(), 1, Calendar.DATE)，表示取到当前时间一天之后的时间
	 * 
	 * @param date
	 * @param offset
	 * @param unit
	 * @return
	 */
	public static Date getDate(Date date, int offset, int unit) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(unit, offset);
		return c.getTime();
	}

	public static Integer getTodayLastTime() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long millis = c.getTimeInMillis() - now;
		String string = Long.valueOf(millis / 1000).toString();
		Integer valueOf = Integer.valueOf(string);
		return valueOf;
	}

	public static Date getAfterDays(Date baseDate, int days) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(baseDate);
		ca.add(Calendar.DATE, days);
		return ca.getTime();
	}

	public static void main(String[] args) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println(df.format(DateUtil.getAfterDays(new Date(), 15)));
	}

	public static List<String> getBetweenDates(Date start, Date end) {
		List<String> result = new ArrayList<String>();
		if (start.compareTo(end) == 0) {
			result.add(date2str(start, YMD));
			return result;
		}
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);
		result.add(date2str(start, YMD));
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			result.add(date2str(tempStart.getTime(), YMD));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		result.add(date2str(end, YMD));
		return result;
	}

	/**
	 * 显示时间，如果与当前时间差别小于一天，则自动用**秒(分，小时)前，如果大于一天则用format规定的格式显示
	 *
	 * @author wxy
	 * @param ctime
	 *            时间
	 * @param format
	 *            格式 格式描述:例如:yyyy-MM-dd yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String showTime(Date ctime) {
		String r = "";
		if (ctime == null)
			return r;
		long nowtimelong = System.currentTimeMillis();
		long ctimelong = ctime.getTime();
		long result = nowtimelong - ctimelong;
		if (result <= 1000)// 数据异常
		{
			r = "刚刚";
		} else if (result < 60000)// 一分钟内
		{
			long seconds = result / 1000;
			r = seconds + "秒钟前";
		} else if (result >= 60000 && result < 3600000)// 一小时内
		{
			long seconds = result / 60000;
			r = seconds + "分钟前";
		} else if (result >= 3600000 && result < 86400000)// 一天内
		{
			long seconds = result / 3600000;
			r = seconds + "小时前";
		} else if (result >= 86400000 && result < 604800000) {
			long seconds = result / 86400000;
			r = seconds + "天前";
		} else if (result >= 604800000 && result < 2678400000L) {
			long seconds = result / 604800000;
			r = seconds + "周前";
		} else if (result >= 2678400000L && result < 31536000000L) {
			long seconds = result / 2678400000L;
			r = seconds + "月前";
		} else {
			long seconds = result / 31536000000L;
			r = seconds + "年前";
		}
		return r;
	}

}
