package com.huntto.server.model.cyry;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 传输xml标准头信息(DSCR)
 */
@Data
@NoArgsConstructor
@XmlRootElement(name="HEAD")
@XmlAccessorType(XmlAccessType.FIELD)
public class Head  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String password;
	private String trans_id;
	private String busi_type;
	private String busi_version; //业务数据版本
	private String opt_type;//操作类型
	private String is_test;//是否测试
	
}
