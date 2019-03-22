package com.huntto.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * @ClassName  CyryService 
 * @Description 从业人员体检信息接口
 * @author 梁城市
 * @date  2019年3月22日 上午10:13:46 
 *
 */
@WebService(name = "CyryService", // 暴露服务名称
	targetNamespace="http://service.server.huntto.com"// 命名空间,一般是接口的包名倒序
)
public interface CyryService {
	/**
	 * 登记
	 * @param xml
	 * @return
	 */
	@WebMethod(operationName = "register")
	String register(@WebParam(name = "xml") String xml);
	
	/**
	 * 体检
	 * @param xml
	 * @return
	 */
	@WebMethod(operationName = "physicalExam")
	String physicalExam(@WebParam(name = "xml") String xml);
	
	/**
	 * 获取二维码
	 * @param xml
	 * @return
	 */
	@WebMethod(operationName = "getQRcode")
	String getQRcode(@WebParam(name = "xml") String xml);
	
	/**
	 * 获取人员信息
	 * @param xml
	 * @return
	 */
	@WebMethod(operationName = "getPersonMsg")
	String getPersonMsg(@WebParam(name = "xml") String xml);
	
	/**
	 * 获取人员信息 东软
	 * @param xml
	 * @return
	 */
	@WebMethod(operationName = "resourceMethod")
	String resourceMethod(@WebParam(name = "xmlData") String xmlData);
}
