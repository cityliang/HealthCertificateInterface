package com.huntto.client;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Test3 {
	public static void main(String[] args) {
		try {
			test1(BB.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test1(Class<?> clazz) throws Exception {
//		// 生成标准实体类
//		NHISDT nhisdt = new NHISDT();
//		
//		//生成头信息
//		DSCR dscr = new DSCR();
//		dscr.setBIZTYPE("02");
//		dscr.setSPECODE("09");
//		dscr.setOPERATE("1");
//		dscr.setORGCODE("3302012");
//		dscr.setSENDTIME(Utils.toDT14(new Date()));
//		dscr.setSENDCODE(ConvertUtil.generateID());
//		dscr.setVERSIONS("0100");
//		nhisdt.setDscr(dscr);
//		
//		//生成数据内容
//		List<Object> b040002s = new ArrayList<Object>();
//		
//		BB bb1 = new BB();
//		b040002s.add(bb1);
//		
//		BB bb2 = new BB();
//		b040002s.add(bb2);
//
//		nhisdt.setData(b040002s);
//		
//		JAXBContext context = JAXBContext.newInstance(NHISDT.class,clazz);
//		Marshaller mar = context.createMarshaller();  
//        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
//        mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
//          
//        StringWriter writer = new StringWriter(); 
//		
//        mar.marshal(nhisdt,writer);
//		
//		String result = writer.toString();
//		System.out.println(result);
	}
}
