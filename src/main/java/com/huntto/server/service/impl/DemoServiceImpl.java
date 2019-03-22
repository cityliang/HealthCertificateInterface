package com.huntto.server.service.impl;

import java.util.Date;
import javax.jws.WebService;
import com.huntto.server.service.DemoService;

@WebService(serviceName = "DemoService", // 与接口中指定的name一致
			targetNamespace = "http://service.server.huntto.com", // 与接口中的命名空间一致,一般是接口的包名倒
			endpointInterface = "com.huntto.server.service.DemoService"// 接口地址
)
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String user) {
		return user+":hello"+"("+new Date()+")";
	}

}
