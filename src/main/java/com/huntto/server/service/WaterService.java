package com.huntto.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
/**
 * 
 * @ClassName  WaterService 
 * @Description 水质监测接口
 * @author 梁城市
 * @date  2019年3月22日 下午4:58:39
 */
@WebService(name = "WaterService", // 暴露服务名称
			targetNamespace="http://service.server.huntto.com"// 命名空间,一般是接口的包名倒序
)
public interface WaterService {

	@WebMethod(operationName = "sendValues")
	String sendValues(@WebParam(name = "xml") String xml);
}