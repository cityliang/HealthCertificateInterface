package com.huntto.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "DemoService", // 暴露服务名称
			targetNamespace="http://service.server.huntto.com"// 命名空间,一般是接口的包名倒序
)
public interface DemoService {
	
	@WebMethod(operationName="sayHello")
	public String sayHello(@WebParam(name = "user") String user);
}
