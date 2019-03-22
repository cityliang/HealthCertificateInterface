package com.huntto.server.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Request参数 和 自定义参数保存用
 * @author chen.ruisen
 */
public class ParameterMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Request参数 */
	protected Map<String, String[]> parameterMap = null;
	
	/** 自定义参数 */
	protected Map<String, Object> attributeMap = new HashMap<String, Object>();

	/**
	 * 构造函数
	 */
	public ParameterMap(){
	}
	

	/**
	 * 取得Request参数值
	 * @param paramName 参数名称
	 * @return 值
	 */
	public String getParameter(String paramName){
		String[] values = getParameters(paramName);
		if(values != null && values.length>0){
			return values[0];
		}
		return null;
	}
	
	/**
	 * 取得Request参数值（多个）
	 * @param paramName 参数名称
	 * @return 值
	 */
	public String[] getParameters(String paramName){
		if(parameterMap == null) return new String[0];
		return parameterMap.get(paramName);
	}
	
	/**
	 * 取得自定义参数值
	 * @param paramName 参数名称
	 * @return 值
	 */
	public Object getAttribute(String paramName){
		return attributeMap.get(paramName);
	}
	
	/**
	 * 设定自定义参数值
	 * @param paramName 参数名称
	 * @param paramValue 值
	 */
	public void setAttribute(String paramName, Object paramValue){
		attributeMap.put(paramName, paramValue);
	}
}
