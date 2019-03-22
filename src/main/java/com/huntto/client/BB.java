package com.huntto.client;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DATA")
@XmlAccessorType(XmlAccessType.FIELD)
public class BB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String XXX = "aaa";
	private String YYY = "bb";
	
	public String getXXX() {
		return XXX;
	}
	public void setXXX(String xXX) {
		XXX = xXX;
	}
	public String getYYY() {
		return YYY;
	}
	public void setYYY(String yYY) {
		YYY = yYY;
	}
	
}
