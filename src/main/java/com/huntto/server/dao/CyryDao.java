package com.huntto.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.huntto.server.model.cyry.CyryQRIMG;
import com.huntto.server.model.cyry.CyryVo1;
import com.huntto.server.model.cyry.OlexamCyryJbxx;

@Mapper
@Repository
public interface CyryDao {
	
	/**
	 * 
	 * @Title  selectQRCODE1 
	 * @Description  通过人员ID 查询该人员二维码
	 * @param ID ID
	 * @return  List<CyryQRIMG>
	 */
	List<CyryQRIMG> selectQRCODE(String ID);
	
	/**
	 * 
	 * @Title  selectCYRYVo1 
	 * @Description  通过身份证号查询从业人员信息
	 * @param idcard 身份证号
	 * @return  List<CyryVo1>
	 */
	List<CyryVo1> selectCYRYVo1(String idcard);
	
	int InsertModels(OlexamCyryJbxx models,String userid)throws Exception;
	
	int updateModels(OlexamCyryJbxx models,String userid,String type)throws Exception;
	
	boolean findCyryByType(OlexamCyryJbxx models);
	
	/**
	 * 
	 * @Title  findCyryById 
	 * @Description  根据表名和id查询id是否重复
	 * @param table 表名
	 * @param ID id
	 * @return  boolean
	 */
	boolean findCyryById(String table,String ID);
	
	
	/**
	 * 
	 * @Title  findLogin 
	 * @Description  判断头中用户名密码是否相符
	 * @param user_id 用户名
	 * @param psw 密码
	 * @return  boolean 
	 */
	boolean findLogin(String user_id,String psw);
	
	/**
	 * 测试数据库切换 Mysql 和 Oracle
	 * @Title  isExist 
	 * @Description  TODO
	 * @return  String
	 */
	String isExist();
	
}
