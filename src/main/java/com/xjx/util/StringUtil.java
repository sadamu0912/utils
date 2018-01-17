package com.xjx.util;

/**
 * 字符串工具类
 * @author xjx
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空：
	 * true: null 或 ''
	 * @param str
	 * @return
	 */
	public static Boolean isEmpty(String str){
		if(str == null || "".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否不为空：
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmpty(String str){
		if(str != null && !"".equals(str)){
			return true;
		}
		return false;
	}
	
	


	
	/**
	 * 1.将null对象返回空字符串-“""”<br>
	 * 2.若非null对象返回toString()及trim字符串<br>
	 * 
	 * @param origin
	 *            String
	 * @return String
	 */
	public static String null2Str(Object origin) {
		return (origin == null ? "" : origin.toString().trim()).replace("null", "");
	}
	
	
	/**
	计算字符串的个数
	* ^param strl
	* @param str2
	* ^return
	*/
	public static int countStr(String str1,String str2,int counter){
	if(str1.indexOf(str2)==-1){
		return counter;
	}else if (str1.indexOf(str2)!=-1){
		counter++;
		counter =countStr(str1.substring(str1.indexOf(str2)+str2.length()),str2, counter);
		return counter;
	}
	return counter;
	}
}
