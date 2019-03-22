package com.huntto.server.model.cyry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * 
 * @ClassName  JaxbDateAdapter 
 * @Description jaxb定制Date类型的序列化方式
 * @author 梁城市
 * @date  2019年3月22日 上午9:12:39 
 *
 */
public class JaxbDateAdapter extends XmlAdapter<String, Date>{

	static final String STANDARM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	@Override
	public Date unmarshal(String v) throws Exception {
		if(v == null){
			return null;
		}
		DateFormat format = new SimpleDateFormat(STANDARM_DATE_FORMAT);
		return format.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		DateFormat format = new SimpleDateFormat(STANDARM_DATE_FORMAT);
		return format.format(v);
	}
	

}
