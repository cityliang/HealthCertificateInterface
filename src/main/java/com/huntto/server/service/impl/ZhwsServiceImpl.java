package com.huntto.server.service.impl;

import javax.jws.WebService;

import com.huntto.server.service.ZhwsService;

@WebService(serviceName = "ZhwsService", // 与接口中指定的name一致
			targetNamespace = "http://service.server.huntto.com", // 与接口中的命名空间一致,一般是接口的包名倒
			endpointInterface = "com.huntto.server.service.ZhwsService"// 接口地址
)
public class ZhwsServiceImpl implements ZhwsService {

	@Override
	public String executeQuery(String userId, String password, String compType, String busType, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkResult(String userId, String password, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
