package com.huntto.server.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

import com.huntto.server.model.cyry.OlexamCyryTjxx;
import com.huntto.server.model.cyry.OlexamCyryJbxx;
import com.huntto.server.model.water.Water;

/**
 * @author
 * 
 */
public class XmlToBean {
	
	public static Object xmlToObject(String inFileName) {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					inFileName), "UTF-8"));
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				// sb.append(strLine+"\n");
				sb.append(strLine);
			}
			// Object obj = inToBean(sb.toString());
			// return obj;
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("未找到XML对象");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	private static String cutBody(String xmlStr, String startStr, String endStr) {
		int start = xmlStr.indexOf(startStr) + startStr.length();
		int end = xmlStr.indexOf(endStr);
		if(end==-1){
			 start = xmlStr.indexOf(startStr.toUpperCase()) + startStr.length();
			 end = xmlStr.indexOf(endStr.toUpperCase());
		}
		String str = null;
		try {
			str = xmlStr.substring(start, end);
		} catch (Exception e) {
			return "";
		}
		return str;
	}
	
	private static Map<String, String> keyValue(String strBody) {
		Map<String, String> map = new HashMap<String, String>();
		String[] fields = strBody.split("<");
		for (String str : fields) {
			if (str.indexOf("/") == 0) {
				continue;
			}
			String[] keyValue = str.trim().split(">");
			try {
				if((keyValue[0] != null)&&(!"".equals(keyValue[0]))){
					map.put(keyValue[0].toUpperCase(), keyValue[1]);
				}	
			} catch (Exception e) {
				map.put(keyValue[0].toUpperCase(), "");
			}
		}
		return map;
	}
	
	private static Date strToDate(String str) {
		Date date = null;
		if (str == null) {
			return null;
		}
		if (str.length() == 19) {
			// str = str.substring(0, str.indexOf("."));
			str = str.replace("T", " ");
			SimpleDateFormat formate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			try {
				date = formate.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("日期格式不正确");
			}
		}
		if (str.length() == 10) {
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = formate.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("日期格式不正确");
			}
		}
		return date;
	}
	
	public static List<Water> inToBean(String xmlStr) {
		List<Water> list = new ArrayList<Water>();
		try{
			String xml = cutBody(xmlStr, "<models>", "</models>");
			if (xml.isEmpty()) {
				System.out.println("无节点，停止解析");
				return null;
			}
			String[] xmls = xml.trim().split("<TestDataModel>");
			for(int i =1;i<=xmls.length-1;i++){
				Map<String, String> xmlKV = keyValue(xmls[i].trim());
				Water models = new Water();
				models.setCREATEDATE(ConvertUtil.toParseDate(xmlKV.get("CreateDate".toUpperCase())));
				models.setCREATEUSER(xmlKV.get("CreateUser".toUpperCase()));
				models.setEXT2(xmlKV.get("Ext2".toUpperCase()));
				models.setEXT3(xmlKV.get("Ext3".toUpperCase()));
				models.setLASTCHANGEDATE(ConvertUtil.toParseDate(xmlKV.get("LastChangeDate".toUpperCase())));
				models.setLASTCHANGEUSER(xmlKV.get("LastChangeUser".toUpperCase()));
				models.setPARAMETERID(xmlKV.get("ParameterID".toUpperCase()));
				models.setSITEID(xmlKV.get("SiteID".toUpperCase()));
				models.setSTATE(xmlKV.get("State".toUpperCase()));
				models.setTESTID(xmlKV.get("TestID".toUpperCase()));
				models.setTESTVALUE(Double.valueOf(xmlKV.get("TestValue".toUpperCase())));
				models.setTRIGGERVALUE(xmlKV.get("TriggerValue".toUpperCase()));
				list.add(models);
			}
		}catch(Exception e){
			return null;
		}
		return list;	
	}

	public static OlexamCyryJbxx inToCyryRegisterBean(String xmlStr) {
		OlexamCyryJbxx models = null;
		try{
			String xml = cutBody(xmlStr, "<models>", "</models>");
			if (xml.isEmpty()) {
				System.out.println("无节点，停止解析");
				return null;
			}
			models = new OlexamCyryJbxx();
			String[] xmls = xml.trim().split("<DATA>");
			Map<String, String> xmlKV = keyValue(xmls[0].trim());
			models.setID(xmlKV.get("ID".toUpperCase()));
			models.setNAME(xmlKV.get("NAME".toUpperCase()));
			models.setSEX(Integer.parseInt(xmlKV.get("SEX".toUpperCase())));
			models.setIDCARD(xmlKV.get("IDCARD".toUpperCase()));
			models.setAGE(Integer.parseInt(xmlKV.get("AGE".toUpperCase())));
			models.setREQUEST_TYPE(Integer.parseInt(xmlKV.get("REQUEST_TYPE".toUpperCase())));
			models.setJOB_TYPE(xmlKV.get("JOB_TYPE".toUpperCase()));
			models.setCOMP_NAME(xmlKV.get("COMP_NAME".toUpperCase()));
			models.setLOC_ADDR(xmlKV.get("LOC_ADDR".toUpperCase()));
			models.setLOC_ADDR_CODE(xmlKV.get("LOC_ADDR_CODE".toUpperCase()));
			models.setEDUCATION(xmlKV.get("EDUCATION".toUpperCase()));
			models.setWORK_AGE(Integer.parseInt(xmlKV.get("WORK_AGE".toUpperCase())));
			models.setCYRYPHOTO(new SerialBlob(xmlKV.get("PHOTO".toUpperCase()).getBytes()));
			models.setREGIST_TIME(ConvertUtil.toParseDate(xmlKV.get("REGIST_TIME".toUpperCase())));
		}catch(Exception e){
			return null;
		}
		return models;	
	}

	public static OlexamCyryTjxx inToOlexamCyryTjxxBean(String xmlStr) {
		OlexamCyryTjxx models = null;
		try{
			String xml = cutBody(xmlStr, "<body>", "</body>");
			if (xml.isEmpty()) {
				System.out.println("无节点，停止解析");
				return null;
			}
			String[] xmls = xml.trim().split("<DATA>");
			for(int i =1;i<=xmls.length-1;i++){
				Map<String, String> xmlKV = keyValue(xmls[i].trim());
				models = new OlexamCyryTjxx();
				models.setID(xmlKV.get("ID".toUpperCase()));
				models.setCYRY_ID(xmlKV.get("CYRY_ID".toUpperCase()));
				models.setIS_ELIGIBLE(Integer.parseInt(xmlKV.get("IS_ELIGIBLE".toUpperCase())));
				models.setEXAM_TIME(ConvertUtil.toParseDate(xmlKV.get("EXAM_TIME".toUpperCase())));
				models.setHEP_DATE(ConvertUtil.toParseDate(xmlKV.get("HEP_DATE".toUpperCase())));
				models.setDYS_DATE(ConvertUtil.toParseDate(xmlKV.get("DYS_DATE".toUpperCase())));
				models.setTYP_DATE(ConvertUtil.toParseDate(xmlKV.get("TYP_DATE".toUpperCase())));
				models.setTUB_DATE(ConvertUtil.toParseDate(xmlKV.get("TUB_DATE".toUpperCase())));
				models.setDER_DATE(ConvertUtil.toParseDate(xmlKV.get("DER_DATE".toUpperCase())));
				models.setOTH_DATE(ConvertUtil.toParseDate(xmlKV.get("OTH_DATE".toUpperCase())));
				models.setHEART_ISGOOD(Integer.parseInt(xmlKV.get("HEART_ISGOOD".toUpperCase())));
				models.setLUNG_ISGOOD(Integer.parseInt(xmlKV.get("LUNG_ISGOOD".toUpperCase())));
				models.setSPLEEN_ISGOOD(Integer.parseInt(xmlKV.get("SPLEEN_ISGOOD".toUpperCase())));
				models.setLIVER_ISGOOD(Integer.parseInt(xmlKV.get("LIVER_ISGOOD".toUpperCase())));
				models.setSKIN_ISGOOD(Integer.parseInt(xmlKV.get("SKIN_ISGOOD".toUpperCase())));
				models.setOTHER_ISGOOD(xmlKV.get("OTHER_ISGOOD".toUpperCase()));
				models.setX_RAY_ISGOOD(Integer.parseInt(xmlKV.get("X_RAY_ISGOOD".toUpperCase())));
				models.setSHIGELLA_ISGOOD(Integer.parseInt(xmlKV.get("SHIGELLA_ISGOOD".toUpperCase())));
				models.setTYPHOID_ISGOOD(Integer.parseInt(xmlKV.get("TYPHOID_ISGOOD".toUpperCase())));
				models.setGPT_ISGOOD(Integer.parseInt(xmlKV.get("GPT_ISGOOD".toUpperCase())));
				models.setHAVIGM_ISGOOD(Integer.parseInt(xmlKV.get("HAVIGM_ISGOOD".toUpperCase())));
				models.setANTI_HAVIGM_ISGOOD(Integer.parseInt(xmlKV.get("ANTI_HAVIGM_ISGOOD".toUpperCase())));
				models.setRPR_ISGOOD(Integer.parseInt(xmlKV.get("RPR_ISGOOD".toUpperCase())));
				models.setHIV_ISGOOD(Integer.parseInt(xmlKV.get("HIV_ISGOOD".toUpperCase())));
			}
		}catch(Exception e){
			return null;
		}
		return models;	
	}


}
