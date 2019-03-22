/*
 * 
 */
package com.huntto.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * @ClassName  WjwsjcService 
 * @Description 卫计委数据仓接口<br/>
 *  卫计委数据仓对接，获取监督系统医师处罚、医疗机构处罚、非法行医处罚、许可数据
 * @author 梁城市
 * @date  2019年3月22日 上午10:20:16 
 *
 */
@WebService(name = "WjwsjcService", // 暴露服务名称
			targetNamespace="http://service.server.huntto.com"// 命名空间,一般是接口的包名倒序
)
public interface WjwsjcService {

	@WebMethod(operationName = "getCfxxData")
	String getCfxxData(@WebParam(name = "xml") String xml);
	
	@WebMethod(operationName = "getXkxxData")
	String getXkxxData(@WebParam(name = "xml") String xml);
}
