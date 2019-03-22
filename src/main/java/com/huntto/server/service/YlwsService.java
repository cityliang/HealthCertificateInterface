package com.huntto.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
/**
 * 
 * @ClassName  YlwsService 
 * @Description 江东区医疗机构监督信息接口
 * @author 梁城市
 * @date  2019年3月22日 上午10:18:55 
 *
 */
@WebService(name = "YlwsService", // 暴露服务名称
		targetNamespace="http://service.server.huntto.com"// 命名空间,一般是接口的包名倒序
)
public interface YlwsService {

	@WebMethod(operationName = "getYlwsData")
	String getYlwsData(@WebParam(name = "HEALTH_LICENSE") String health_license);
}
