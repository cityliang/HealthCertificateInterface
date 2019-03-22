/*
 * 系统名称：卫生监督管理系统系统
 * 版权所有：(c)2012 杭州汉图信息技术有限公司，所有版权保留
 * 版权声明：本软件所有权归杭州汉图信息技术有限公司，
 *          未经杭州汉图信息技术有限公司同意，禁止拷贝、修改和发布本系统代码。
 * 开发日期：2014年2月
 */
package com.huntto.server.util;

import java.util.HashMap;

import org.apache.log4j.Logger;


/**
 * 配置信息管理<br />
 * @author cao.yazhen
 * @since 2014/9/11
 */
final public class BgkConfig {
	private static Logger logger = Logger.getLogger(BgkConfig.class);
	
	/**
	 * 根目录<br />
	 * 配置ID: bgkgj.home,bgkgj.base
	 */
	public static final String baseDir;
	
	/**
	 * 配置文件目录<br />
	 * 配置ID: bgkgj.config_dir
	 */
	public static final String configDir;

	/** 默认报卡者ID<br/> 当记录的R_ID为空，或不存在R_ID字段时候，使用该用户ID */
	public static Long defaultUserID = 9526L; //9526 孙剑寒,11590 吴家道

	/** 默认报卡者是否启用 */
	public static boolean defaultUserEnabled = false;

	/** 每日一次运行触发器时间  HHmm*/
	public static String dailyTriggerTime = "2200";

	/**
	 * 是否启用某类报告卡<br/>
	 * 配置ID: cards<br/>
	 * 配置例子: cards=01,02,03,04,05,06,11
	 */
	public static HashMap<String, Boolean> cardsEnabled = new HashMap<String, Boolean>();

	/**
	 * 监督卡存在更新检查标志<br/>
	 * 监督新增报告时, 如果该标志为true,则重复错误也认为正确, 不需要继续recoveryExecute<br />
	 * 监督卡时候用
	 */
	public static boolean supervisionDuplicateNoUpdate = true;

	/** 是否进行本地检验: 确保本地校验包和国家校验包一致时才可启用 */
	public static boolean localValidationEnabled = false;

	static{
		String homeDir2 = Utils.getJvmParam("bgkgj.home", "");
		baseDir = Utils.getJvmParam("bgkgj.base", homeDir2);
		
		String configDir2 = Utils.getJvmParam("bgkgj.config_dir");
		if(Utils.isNullOrEmpty(configDir2)){
			configDir2 = baseDir + "/conf";
		}
		configDir = configDir2;
		
		if(Utils.isNullOrEmpty(baseDir)){
			logger.warn("未找到JVM参数bgkgj.base。");
		}
		
		//报卡启用标志读取
		String[] cardKeys = {"01","02","03","04","05","06","11"};
		for (String key : cardKeys) {
			cardsEnabled.put(key, false);
		}
	
		String cardsSetting = "01,02,03,04,05,06,11";
		String[] cardKeysFromSetting = cardsSetting.split(",");
		
		for (String key : cardKeysFromSetting) {
			if(cardsEnabled.containsKey(key)) cardsEnabled.put(key, true);
		}
	}

	/**
	 * 读取配置文件
	 */
	public static void init() {
	}
}
