package com.huntto.server.service.impl;

import javax.jws.WebService;

import com.huntto.server.service.WaterService;

@WebService(serviceName = "WaterService", // 与接口中指定的name一致
			targetNamespace = "http://service.server.huntto.com", // 与接口中的命名空间一致,一般是接口的包名倒
			endpointInterface = "com.huntto.server.service.WaterService"// 接口地址
)
public class WaterServiceImpl implements WaterService {

	@Override
	public String sendValues(String xml) {
		// TODO Auto-generated method stub
		return null;
	}

}
