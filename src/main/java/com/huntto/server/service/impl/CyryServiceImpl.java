package com.huntto.server.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.huntto.server.dao.CyryDao;
import com.huntto.server.model.cyry.Head;
import com.huntto.server.model.cyry.OlexamCyryJbxx;
import com.huntto.server.service.CyryService;
import com.huntto.server.util.Nulls;
import com.huntto.server.util.XmlCheck;
import com.huntto.server.util.XmlUtil;

@WebService(serviceName = "CyryService", // 与接口中指定的name一致
			targetNamespace = "http://service.server.huntto.com", // 与接口中的命名空间一致,一般是接口的包名倒
			endpointInterface = "com.huntto.server.service.CyryService"// 接口地址
)
public class CyryServiceImpl implements CyryService {
	
	@Autowired
	private CyryDao cyryDao;

	@Override
	public String register(String xml) {
		boolean flag = false;
		String error = "";
		try{
			//解析xml
			Document doc = null;
			Head head = null;
			Object data = null;
			try {
				doc = XmlUtil.parse(xml);
				//校验XML结构（NHISDT/DSCR部分）
				head = getHead(doc);
				System.out.println(head.getUser_id());
			} catch (Exception e) {
				error += "HEAD解析失败";
				return error;
			}
			try {
				doc = XmlUtil.parse(xml);
				//XML转POJO（REQUEST部分）
				data = getData(doc, OlexamCyryJbxx.class);
				System.out.println(data.toString());
			} catch (Exception e) {
				e.printStackTrace();
				error += "REQUEST解析失败";
				return error;
			}
			
			//判断用户名和密码是不是正确
			boolean loginTrue = cyryDao.findLogin(head.getUser_id(),head.getPassword());
			System.out.println(loginTrue);
			if(loginTrue){
				//处理数据
				OlexamCyryJbxx olexamCyryJbxx = (OlexamCyryJbxx)data;
				boolean isequal = false;
				//同一天不能录同一个工种
				boolean isSameType = false;
				if("1".equals(head.getOpt_type())){
					isequal = cyryDao.findCyryById("OLEXAM_CYRY_JBXX",olexamCyryJbxx.getID());
					isSameType = cyryDao.findCyryByType(olexamCyryJbxx);
				}
				if(isequal){
					error +="ID重复;  ";
				}else if(isSameType){
					error +="同一个人不能在同一天登记相同的工种;  ";
				}else if(!XmlCheck.IsNullJob_type(olexamCyryJbxx)){
					error +="类别为公共场所时，工种必填;  ";
				}else if(olexamCyryJbxx.getREGIST_TIME() == null){
					error +="登记时间必填;  ";
				}else {
					if(Nulls.isEmpty(olexamCyryJbxx.getCARDTYPE())){
						olexamCyryJbxx.setCARDTYPE("01");
					}
					if("1".equals(head.getOpt_type())){
						this.cyryDao.InsertModels(olexamCyryJbxx,head.getUser_id());
					}else if("2".equals(head.getOpt_type()) || "6".equals(head.getOpt_type())){
						this.cyryDao.updateModels(olexamCyryJbxx,head.getUser_id(),head.getOpt_type());
					}
					flag = true;
					error = "成功！";
				}
			}else{
				error += "用户名或密码错误";
				return error;
			}
		}catch(Exception e){
			error += "接收失败";
			return error;
		}

		return error;
	}

	@Override
	public String physicalExam(String xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQRcode(String xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPersonMsg(String xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resourceMethod(String xmlData) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected Head getHead(Document doc) throws Exception {
		NodeList nodes = doc.getElementsByTagName("HEAD");
		Node node = null;
		
		if(nodes.getLength()>0){
			node = nodes.item(0);
			Head head = (Head)XmlUtil.deserialize(node, Head.class);
			return head;
		}
		throw new Exception("HEAD not found");
	}
	
	protected Object getData(Document doc, Class<?> dataEntityClass) throws Exception {
		NodeList nodes = doc.getElementsByTagName("REQUEST");
		Node node = null;
		
		if(nodes.getLength()>0){
			node = nodes.item(0);
			Object data = XmlUtil.deserialize(node, dataEntityClass);
			return data;
		}
		throw new Exception("REQUEST not found");
	}

}
