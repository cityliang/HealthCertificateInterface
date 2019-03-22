package com.huntto.server.service.impl;

import javax.jws.WebService;

import com.huntto.server.service.YlwsService;

@WebService(serviceName = "YlwsService", // 与接口中指定的name一致
			targetNamespace = "http://service.server.huntto.com", // 与接口中的命名空间一致,一般是接口的包名倒
			endpointInterface = "com.huntto.server.service.YlwsService"// 接口地址
)
public class YlwsServiceImpl implements YlwsService {

	@Override
	public String getYlwsData(String health_license) {
		// TODO Auto-generated method stub
		return null;
	}

}
