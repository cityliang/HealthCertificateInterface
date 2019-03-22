package com.huntto.server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.huntto.server.model.cyry.OlexamCyryJbxx;

@Mapper
@Repository
public interface CyryDao {
	
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
