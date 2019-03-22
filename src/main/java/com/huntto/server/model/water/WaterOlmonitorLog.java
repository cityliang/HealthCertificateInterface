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
//@Table(name = "WATER_OLMONITOR_LOG")
public class WaterOlmonitorLog {

	/** 卫生局会分配一个ID给你们，用来表示这个是来自你们站点的数据 */
	private String SITEID;
	/** 记录产生的日期时间集合内的三条数据的时间应是一致的 */
	private Date CREATEDATE;
	/** 录入时间 */
	private Date INPUTTIME;
	/** 传入xml文件*/
	private String requestxml;
	/** 记录状态 1成功 0 失败*/
	private String state;
	private String responsexml;
	private String id;
}