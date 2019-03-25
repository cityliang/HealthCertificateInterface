package com.huntto.server.model.cyry;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name="REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
public class CyryQRcode implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 记录ID，可传任意值 */
	private String ID;
	private String QRCODE;
}
