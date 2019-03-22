/*
 * 系统名称：卫生监督管理系统系统
 * 版权所有：(c)2014 杭州汉图信息技术有限公司，所有版权保留
 * 版权声明：本软件所有权归杭州汉图信息技术有限公司，
 *          未经杭州汉图信息技术有限公司同意，禁止拷贝、修改和发布本系统代码。
 * 开发日期：2014年2月
 */
package com.huntto.server.model.water;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ModelsId entity. @author MyEclipse Persistence Tools
 */
@Data
@NoArgsConstructor
//@Table(name = "WATER_OLMONITOR")
public class Water {

	/** 记录ID，可传任意值 */
	private String TESTID;
	/** 卫生局会分配一个ID给你们，用来表示这个是来自你们站点的数据 */
	private String SITEID;
	/** 2(PH值)/3(浊度)/5(余氯) */
	private String PARAMETERID;
	/** 上述对应的值 */
	private Double TESTVALUE;
	/** 记录产生的日期时间集合内的三条数据的时间应是一致的 */
	private Date CREATEDATE;
	/** 用户ID，可传任意值 */
	private String CREATEUSER;
	/** 传空值 */
	private Date LASTCHANGEDATE;
	/** 传空值 */
	private String LASTCHANGEUSER;
	/** 始终为1 */
	private String STATE;
	/** 传空值 */
	private String TRIGGERVALUE;
	/** 传空值 */
	private String EXT2;
	/** 传空值 */
	private String EXT3;
	/** 录入时间 */
	private Date INPUTTIME;
}