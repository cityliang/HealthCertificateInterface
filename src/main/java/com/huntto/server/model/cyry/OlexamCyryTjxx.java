package com.huntto.server.model.cyry;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @ClassName  OlexamCyryTjxx 
 * @Description 考试从业人员体检信息表
 * @author 梁城市
 * @date  2019年3月22日 上午9:09:14 
 *
 */
@Data
@NoArgsConstructor
//@Table(name = "OLEXAM_CYRY_TJXX")
@XmlRootElement(name="REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
public class OlexamCyryTjxx {
	/** 主键id */
	private String ID;
	/** 人员id */
	private String CYRY_ID;
	/** 是否合格 */
	private Integer IS_ELIGIBLE;
	/** 不合格内容 */
	private String OPTION_B;
	/** 删除标示位 */
	private Integer IS_DELETE;
	/** 删除备注 */
	private String DEL_MEMO;
	/** 记录创建时间 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date CREATE_TIME;
	/** 监督创建人 */
	private String CREATE_PERSON;
	/** 最后更新时间 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date UPDATE_TIME;
	/** 最后更新人 */
	private String UPDATE_PERSON;
	/** 监督单位id */
	private String R_ORGCODE;
	/**
	 * 体检时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date EXAM_TIME;
	// 需求新增字段 START
	/**
	 * 肝炎患病时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date HEP_DATE;
	/**
	 * 痢疾患病时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date DYS_DATE;
	/**
	 * 伤寒患病时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date TYP_DATE;
	/**
	 * 肺结核患病时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date TUB_DATE;
	/**
	 * 皮肤病患病时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date DER_DATE;
	/**
	 * 其他患病时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date OTH_DATE;
	/**
	 * 心是否合格 (0:合格;1:不合格)
	 */
	private Integer HEART_ISGOOD;
	/**
	 * 肺是否合格 (0:合格;1:不合格)
	 */
	private Integer LUNG_ISGOOD;
	/**
	 * 脾是否合格 (0:合格;1:不合格)
	 */
	private Integer SPLEEN_ISGOOD;
	/**
	 * 肝是否合格 (0:合格;1:不合格)
	 */
	private Integer LIVER_ISGOOD;
	/**
	 * 皮肤是否合格 (0:合格;1:不合格;2:手癣;3:指甲癣;4:手部湿疹;5:银屑（或鳞屑）病;6:渗出性皮肤病;7:化脓性皮肤病)
	 */
	private Integer SKIN_ISGOOD;
	/**
	 * 其他是否合格 (可手输)
	 */
	private String OTHER_ISGOOD;
	/**
	 * X线胸透是否合格(0:合格;1:不合格)
	 */
	private Integer X_RAY_ISGOOD;
	/**
	 * 痢疾杆菌是否合格(0:合格;1:不合格)
	 */
	private Integer SHIGELLA_ISGOOD;
	/**
	 * 伤寒或副伤寒是否合格(0:合格;1:不合格)
	 */
	private Integer TYPHOID_ISGOOD;
	/**
	 * 谷丙转氨酶是否合格(0:合格;1:不合格)
	 */
	private Integer GPT_ISGOOD;
	/**
	 * HEVIgM是否合格(0:合格;1:不合格)
	 */
	private Integer HAVIGM_ISGOOD;
	/**
	 * 抗HAVIgM是否合格(0:合格;1:不合格)
	 */
	private Integer ANTI_HAVIGM_ISGOOD;
	/**
	 * RPR试验是否合格(0:合格;1:不合格)
	 */
	private Integer RPR_ISGOOD;
	/**
	 * HIV试验是否合格(0:合格;1:不合格)
	 */
	private Integer HIV_ISGOOD;

	/**
	 * 体检机构名称
	 */
	private String TJDPT_NAME;
	/**
	 * 体检机构ID 
	 */
	private String TJDPT_ID;
	/**
	 * 健康证编号 
	 */
	private String HEALTH_CARD_CODE;
	/**
	 * 是否打印健康证
	 */
	private Integer IS_PRINT;
	/**
	 * 打印时间
	 */
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private Date PRINT_TIME;
	
	// 新增字段END

}
