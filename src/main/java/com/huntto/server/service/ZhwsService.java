package com.huntto.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * @ClassName  ZhwsService 
 * @Description 综卫平台接口
 * @author 梁城市
 * @date  2019年3月22日 上午10:17:29 
 *
 */
@WebService(name = "ZhwsService", // 暴露服务名称
		targetNamespace="http://service.server.huntto.com"// 命名空间,一般是接口的包名倒序
)
public interface ZhwsService {

	/**
	 * 数据传输
	 * 
	 * @param 用户id
	 * @param 密码
	 * @param 专业类别
	 * @param 业务类型
	 * @param 开始时间
	 * @param 结束时间
	 * @return
	 */
	@WebMethod(operationName = "executeQuery")
	String executeQuery(@WebParam(name = "userId") String userId,
			@WebParam(name = "password") String password,
			@WebParam(name = "compType") String compType,
			@WebParam(name = "busType") String busType,
			@WebParam(name = "startDate") String startDate,
			@WebParam(name = "endDate") String endDate);

	/**
	 * 数据对账
	 * 
	 * @param 用户id
	 * @param 密码
	 * @param 开始时间
	 * @param 结束时间
	 */
	@WebMethod(operationName = "checkResult")
	String checkResult(@WebParam(name = "userId") String userId,
			@WebParam(name = "password") String password,
			@WebParam(name = "startDate") String startDate,
			@WebParam(name = "endDate") String endDate);

}