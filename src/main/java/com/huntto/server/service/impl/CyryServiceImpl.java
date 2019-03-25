package com.huntto.server.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.huntto.server.dao.CyryDao;
import com.huntto.server.model.cyry.Body;
import com.huntto.server.model.cyry.CyryQRIMG;
import com.huntto.server.model.cyry.CyryQRcode;
import com.huntto.server.model.cyry.CyryVo1;
import com.huntto.server.model.cyry.Head;
import com.huntto.server.model.cyry.OlexamCyryJbxx;
import com.huntto.server.service.CyryService;
import com.huntto.server.util.ConvertUtil;
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
		boolean flag = false;
		String qrCodeImg = "";
		String error = "";
		StringWriter writer = new StringWriter();
		try{
			//解析xml
			Document doc = null;
			Head head = null;
			Object data = null;
			try {
				doc = XmlUtil.parse(xml);
				//校验XML结构（NHISDT/DSCR部分）
				head = getHead(doc);
			} catch (Exception e) {
				error += "HEAD解析失败";
				e.printStackTrace();
				return error;
			}
			try {
				//XML转POJO（REQUEST部分）
				data = getData(doc, CyryQRcode.class);
			} catch (Exception e) {
				error += "REQUEST解析失败";
				return error;
			}
			CyryQRcode list = (CyryQRcode)data;
			//获取二维码
			//不管有没有数据，先从数据库里读取二维码图片，生成到项目里，
			List<CyryQRIMG> list1 = cyryDao.selectQRCODE(list.getID());
			if(list1 != null && !list1.isEmpty() && "[]".equals(list1.toString())) {
				CyryQRIMG cyryQRIMG = list1.get(0);
				if(cyryQRIMG.getQRIMG() != null) {
					list.setQRCODE(Base64.encodeBase64String(cyryQRIMG.getQRIMG()));
				}
			}
			
			
			//封装xml
			Body body = new Body();
			body.setHead(head);
			list.setQRCODE(qrCodeImg);
			body.setData(list);
			JAXBContext context = JAXBContext.newInstance(Body.class,CyryQRcode.class);
			Marshaller mar = context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			mar.marshal(body, writer);
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		return writer.toString();
	}

	@Override
	public String getPersonMsg(String xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resourceMethod(String xmlData) {
		StringBuffer sb = new StringBuffer();
		String ID = "";// ID
		String BH = "";// 编号
		String XM = "";// 姓名
		String XB = "";// 性别
		Integer NL = 0;// 年龄
		String LB = "";// 类别
		String TJJG = "";// 体检结果
		String FZSJ = "";// 发证时间
		String IDCARD = "";// 身份证号
		String PHOTO = "";// 图片
		String QRIMG = "";// 二维码
		String FZJG = "";// 发证机构
		String YXQ = "1年";// 有效期
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><messages xmlns=\"http://www.neusoft.com/hit/rhin\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.neusoft.com/hit/rhin file:///e:/request_message.xsd\"><switchset><visitor><sourceorgan>请求服务的机构编码</sourceorgan><sourcedomain>请求服务的接入系统编码</sourcedomain></visitor><serviceinf><servicecode></servicecode></serviceinf><provider><targetorgan>470116876911K100330105</targetorgan><targetdomain>3301000053</targetdomain></provider><route/><process/><switchmessage><messagecode/><messagetext/></switchmessage></switchset><business><standardcode></standardcode><returnmessage><retcode></retcode><rettext/></returnmessage><returnset><rettotal>1</rettotal><retpaging>0</retpaging><retpageindex>-</retpageindex><retpageset></retpageset></returnset><datacompress>0</datacompress><businessdata><datasets><setcode/><settype/><setdetails>");
		if(Nulls.isNotEmpty(xmlData)) {
			try{
				//解析xml
				String idcard = ConvertUtil.xml2Str(xmlData.trim(), "IDCARD");
				//获取二维码
				//不管有没有数据，先从数据库里读取二维码图片，生成到项目里，
				CyryVo1 cyryVo1 = new CyryVo1();
				List<CyryVo1> list = cyryDao.selectCYRYVo1(idcard);
				if(list != null && !list.isEmpty() && !"[]".equals(list.toString())) {
					cyryVo1 = list.get(0);
				}
				
				//封装xml
				sb.append("<YXQ>"+YXQ+"</YXQ>");
				if(null != cyryVo1.getBH()) {
					BH = cyryVo1.getBH();
					sb.append("<BH>"+BH+"</BH>");
				}else {
					sb.append("<BH></BH>");
				}
				if(null != cyryVo1.getID()) {
					ID = cyryVo1.getID();
				}
				if(null != cyryVo1.getXM()) {
					XM = cyryVo1.getXM();
					sb.append("<XM>"+XM+"</XM>");
				}else {
					sb.append("<XM></XM>");
				}
				if(null != cyryVo1.getNL()) {
					NL = cyryVo1.getNL();
					sb.append("<NL>"+NL+"</NL>");
				}else {
					sb.append("<NL></NL>");
				}
				if(null != cyryVo1.getTJJG()) {
					TJJG = cyryVo1.getTJJG();
					sb.append("<TJJG>"+TJJG+"</TJJG>");
				}else {
					sb.append("<TJJG></TJJG>");
				}
				if(null != cyryVo1.getFZSJ()) {
					FZSJ = cyryVo1.getFZSJ();
					sb.append("<FZSJ>"+FZSJ+"</FZSJ>");
				}else {
					sb.append("<FZSJ></FZSJ>");
				}
				if(null != cyryVo1.getIDCARD()) {
					IDCARD = cyryVo1.getIDCARD();
					sb.append("<IDCARD>"+IDCARD+"</IDCARD>");
				}else {
					sb.append("<IDCARD></IDCARD>");
				}
				if(null != cyryVo1.getFZJG()) {
					FZJG = cyryVo1.getFZJG();
					sb.append("<FZJG>"+FZJG+"</FZJG>");
				}else {
					sb.append("<FZJG></FZJG>");
				}
				
				if(null != cyryVo1.getXB()) {
					XB = ConvertUtil.toXB(cyryVo1.getXB());
					sb.append("<XB>"+XB+"</XB>");
				}else {
					sb.append("<XB></XB>");
				}
				if(null != cyryVo1.getLB()) {
					LB = ConvertUtil.toLB(cyryVo1.getLB());
					sb.append("<LB>"+LB+"</LB>");
				}else {
					sb.append("<LB></LB>");
				}
				if(null != cyryVo1.getPHOTO()) {
					PHOTO = ConvertUtil.byteArrToBinStr(cyryVo1.getPHOTO());
					sb.append("<PHOTO>"+PHOTO+"</PHOTO>");
				}else{
					sb.append("<PHOTO></PHOTO>");
				}
				if(cyryVo1.getQRIMG() != null) {
					QRIMG = ConvertUtil.byteArrToBinStr(cyryVo1.getQRIMG());
					sb.append("<QRIMG>"+QRIMG+"</QRIMG>");
				}else {
					sb.append("<QRIMG></QRIMG>");
				}
//				if(Nulls.isNotEmpty(cyryVo1.getID())) {
//					String qrcode = cyryManager.findQrCodeByMid(cyryVo1.getID());
//					if(Nulls.isNotEmpty(qrcode)) {
//						QRIMG = ConvertUtil.byteArrToBinStr(qrcode.getBytes());
//						sb.append("<QRIMG>"+QRIMG+"</QRIMG>");
//					}else {
//						sb.append("<QRIMG></QRIMG>");
//					}
//				}else {
//					sb.append("<QRIMG></QRIMG>");
//				}
				
				
			}catch(Exception e){
				System.out.println(e);
				e.printStackTrace();
			}
		}
		sb.append("</setdetails></datasets></businessdata></business><extendset/></messages>");
		return sb.toString();
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
