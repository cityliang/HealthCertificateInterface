package com.huntto.server.service.impl;

import javax.jws.WebService;

import com.huntto.server.service.WjService;

@WebService(serviceName = "WjService", // 与接口中指定的name一致
			targetNamespace = "http://service.server.huntto.com", // 与接口中的命名空间一致,一般是接口的包名倒
			endpointInterface = "com.huntto.server.service.WjService"// 接口地址
)
public class WjServiceImpl implements WjService {

	@Override
	public String getData(String xzqhcode, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
