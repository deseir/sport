package com.moerlong.carloan.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class CommonUtil {
	
	private static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	
	/**
	 * 把json转成map
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2map(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		return mapper.readValue(json, Map.class);
	}
	
	/**
	 * 把json转成object
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T json2Object(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		if(json==null) {
			return null;
		}
		ObjectMapper mapper=new ObjectMapper();
		return mapper.readValue(json, clazz);
	}
	
	/**
	 * 把object转成json
	 * @param obj
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String obj2json(Object obj) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().writeValueAsString(obj);
	}

	/**
	 * 把java中的类，成员变量转成驼峰写法字符串。
	 * <p/>
	 * 如： StudnetNameAge -> studnet_name_age ; studnetNameAge ->studnet_name_age
	 * 
	 * @param src
	 *            源字符串
	 * @return String
	 */
	public static String humpJava2DB(String src) {

		if (isNull(src)) {
			return null;
		}

		StringBuffer tgt = new StringBuffer("");
		boolean isFirstUpper = true;// 是否是第一个大写字符
		boolean isFirstChar = true;// 是否是第一个字符

		int len = src.length();

		for (int i = 0; i < len; i++) {
			char c = src.charAt(i);
			if (Character.isUpperCase(c)) {// 如果是大写
				if (isFirstUpper) {// 如果是第一个大写字符
					if (isFirstChar) {// 如果是第一个字符
						tgt.append(Character.toLowerCase(c));
					} else {
						tgt.append('_');
						tgt.append(Character.toLowerCase(c));
					}
					isFirstUpper = false;
				} else {
					tgt.append('_');
					tgt.append(Character.toLowerCase(c));
				}
			} else {// 如果是小写
				tgt.append(c);
			}
			isFirstChar = false;
		}

		return tgt.toString();
	}

	/**
	 * 把数据库中驼峰写法字符串转成java中的类，成员变量写法字符串。
	 * <p/>
	 * 如： studnet_name_age -> StudnetNameAge ; studnet_name_age -> studnetNameAge
	 * 
	 * @param src
	 *            源字符串
	 * @param firstChar2Upper
	 *            转换后的首字符是否要大写
	 * @return String
	 */
	public static String humpDB2Java(String src, boolean firstChar2Upper) {

		if (isNull(src)) {
			return null;
		}

		StringBuffer tgt = new StringBuffer("");
		boolean isFirstChar = true;// 是否是第一个字符

		int len = src.length();

		for (int i = 0; i < len; i++) {
			char c = src.charAt(i);
			// 如果是首字符，并且需要把首字符转成大写
			if (isFirstChar && firstChar2Upper) {
				tgt.append(Character.toUpperCase(c));
			} else {
				// 如果字符是'_'，并且不是最后一个字符。
				// 则舍弃此字符，并把下个字符转成大写，并把指针指向下下个字符
				if ('_' == c && i != (len - 1)) {
					char cn = src.charAt(i + 1);
					tgt.append(Character.toUpperCase(cn));
					i++;
				} else {
					tgt.append(c);
				}
			}

			isFirstChar = false;
		}

		return tgt.toString();
	}
	
	/**
	 * 判断字符串是否为逻辑空
	 * @param str
	 * @return 为空返回true，否则返回false
	 */
	public static boolean isNull(String str){
		if(str==null|| "".equals(str.trim()) || "null".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 判断字符串是否为不为逻辑空
	 * @param str
	 * @return 为空返回false，否则返回true
	 */
	public static boolean isNotNull(String str){
		return !isNull(str);
	}
	
	public static Number formulaCompute(String formula) throws ScriptException {
		Object res = jse.eval(formula);
		return new BigDecimal(res.toString());
	}

//	public static void main(String[] args) throws ScriptException {
//		System.out.println(formulaCompute("1-2-3"));
//	}
}
