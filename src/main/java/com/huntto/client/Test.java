package com.huntto.client;

public class Test {

	public static void main(String[] args) {// 使用自带jetty容器发布webservice
//		System.out.println("Hello World Client ");
//		JaxWsProxyFactoryBean jwpf = new JaxWsProxyFactoryBean();// 代理工厂Bean
//		jwpf.setServiceClass(WjService.class);
//		jwpf.setAddress("http://127.0.0.1:8081/wj/server?wsdl");// 这里要和正确访问的wsdl地址一致
//		WjService hw = (WjService) jwpf.create();
//		System.out.println(hw.getData("330204","20140625"));

//		try {
//			String endpoint = "http://192.168.22.134:8081/jkzjk/cyryserver?wsdl";
//			// 直接引用远程的wsdl文件
//			// 以下都是套路
//			Service service = new Service();
//			Call call = (Call) service.createCall();
//			call.setTargetEndpointAddress(endpoint);
//			call.setOperationName("getPersonMsg");// WSDL里面描述的接口名称
//			call.addParameter("IDCARD", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 接口的参数
//			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
//			String temp = "测试人员";
//			String result = (String) call.invoke(new Object[] { temp });
//			// 给方法传递参数，并且调用方法
//			System.out.println("result is " + result);
//		} catch (Exception e) {
//			System.err.println(e.toString());
//		}
		
	}
}
