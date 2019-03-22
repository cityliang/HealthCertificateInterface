package com.huntto.server.model.cyry;

import java.sql.Blob;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @ClassName  OlexamCyryJbxx 
 * @Description 考试从业人员基本信息表
 * @author 梁城市
 * @date  2019年3月22日 上午9:08:29 
 *
 */
@Data
@NoArgsConstructor
//@Table(name = "OLEXAM_CYRY_JBXX")
@XmlRootElement(name="REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
public class OlexamCyryJbxx {
	/** 主键id */
	private String ID;
	/** 姓名 */
	private String NAME;
	/** 性别 */
	private Integer SEX;
	/** 身份证号 */
	private String IDCARD;
	/** 身份证件类型(GB2012_TS_4身份证件类型编码表) */
	private String CARDTYPE;
	/** 年龄 */
	private Integer AGE;
	/** 类别  1:食品卫生，2：公共场所卫生*/
	private Integer REQUEST_TYPE;
	/** 工种 */
	private String JOB_TYPE;
	/** 单位名称 */
	private String COMP_NAME;
	/** 单位id */
	private String COMP_ID;
	/** 单位编码 */
	private String COMP_NO;
	/** 删除标示位 */
	private Integer IS_DELETE;
	/** 删除备注 */
	private String DEL_MEMO;
	/** 记录创建时间 */
	private Date CREATE_TIME;
	/** 监督创建人 */
	private String CREATE_PERSON;
	/** 最后更新时间 */
	private Date UPDATE_TIME;
	/** 最后更新人 */
	private String UPDATE_PERSON;
	/** 监督单位id */
	private String R_ORGCODE;
	/** 体检机构id */
	private String MEDI_INTID;
	/**
	 * 所在地地址
	 */
	private String LOC_ADDR;
	/**
	 * 所在地地址CODE
	 */
	private String LOC_ADDR_CODE;
	// 需求新增字段 START
	/**
	 * 文化程度
	 */
	private String EDUCATION;
	/**
	 * 工龄
	 */
	private Integer WORK_AGE;
	/**
	 * 照片
	 */
	private String PHOTO;
	
	@XmlTransient
	private Blob CYRYPHOTO;

	// 需求新增字段 END
	/**
	 * 登记时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date REGIST_TIME;
	/**
	 * 体检登记号 
	 */
	private String EXAM_REGIST_NUMBER;
	//单位地址
	private String COMP_ADDR;
	//单位地址code
	private String COMP_ADDR_CODE;
}
