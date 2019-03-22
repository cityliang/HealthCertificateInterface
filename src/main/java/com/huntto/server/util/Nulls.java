package com.huntto.server.util;

import java.math.BigDecimal;

/**
 * 判断是否为空 工具类
 * @author cao.yazhen
 */

public class Nulls {
	/**
	 * 校验是否为空
	 */
	public static boolean isEmpty(String data){
		boolean flag = false;
		if(data == null || "".equals(data) || data =="null"){
			flag = true;
		}
		return flag;
	}
	/**
	 * 校验是否为空，为空时抛出异常
	 * @param data：校验数
	 * @param errorMsg：异常信
	 */
	public static void isEmpty(String data, String errorMsg){
		if(data == null || "".equals(data)){
			throw new RuntimeException(errorMsg);
		}
	}
	
	public static boolean isNotEmpty(String data){
		return !isEmpty(data);
	}
	
	public static String nullToString(Object obj){
		if(obj instanceof Integer){
			if(Integer.parseInt(obj.toString())==0){
				return "";
			}
		}
		if(obj == null){
			return "";
		}
		return obj.toString();
	}
	
	public static double nullToDouble(Object obj){
		if(obj == null){
			return 0;
		}
		return Double.parseDouble(obj.toString());
	}
	
	//lt2013102503 增加处理整数变量的排空方法
	public static int nullToInt(Object obj){
		if(Nulls.isEmpty(obj.toString())){
			return 0;
		}
		return Integer.parseInt(obj.toString());
	}
	
	public static BigDecimal nullToBigDecimal(Object obj){
		if(obj == null){
			return new BigDecimal("0");
		}
		return (BigDecimal)obj;
	}
	public static Long nullToLong(Object obj){
		if(obj == null){
			return new Long("0");
		}
		return (Long)obj;
	}
	
}
