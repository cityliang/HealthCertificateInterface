package com.huntto.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Test2 {

	public static void main(String[] args) {// 使用自带jetty容器发布webservice
//		JaxWsProxyFactoryBean jwpf = new JaxWsProxyFactoryBean();// 代理工厂Bean
//		jwpf.setServiceClass(WaterService.class);
//		jwpf.setAddress("http://127.0.0.1:8080/wj/waterserver?wsdl");// 这里要和正确访问的wsdl地址一致
//		WaterService hw = (WaterService) jwpf.create();
//		hw.sendValues("");
	}
}
