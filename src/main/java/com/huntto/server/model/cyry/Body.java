package com.huntto.server.model.cyry;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @ClassName  Body 
 * @Description 封装返回信息的BODY
 * @author 梁城市
 * @date  2019年3月25日 上午11:04:45
 */
@Data
@NoArgsConstructor
@XmlRootElement(name="BODY")
@XmlAccessorType(XmlAccessType.FIELD)
public class Body implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** 头信息 */
	@XmlElement(name="HEAD")
	private Head head;
	
	/** 内容 */
	@XmlAnyElement(lax=true)
	private Object data;

}
