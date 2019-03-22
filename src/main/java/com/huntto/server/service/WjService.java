package com.huntto.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * @ClassName  WjService 
 * @Description 许可信息公示接口
 * @author 梁城市
 * @date  2019年3月22日 上午10:16:09 
 *
 */
@WebService(name = "WjService", // 暴露服务名称
			targetNamespace="http://service.server.huntto.com"// 命名空间,一般是接口的包名倒序
)
public interface WjService {

	@WebMethod(operationName = "getData")
	String getData(@WebParam(name = "XZQHCODE") String xzqhcode,
			@WebParam(name = "DATE") String date);

}