package com.huntto.server.util;

import java.util.Date;
import java.util.List;

import com.huntto.server.model.cyry.OlexamCyryTjxx;
import com.huntto.server.model.cyry.OlexamCyryJbxx;
import com.huntto.server.model.water.Water;


public class XmlCheck {

	public static boolean IsDateEqual(List<Water> list){
		try{
			if(list.size() ==1){
				return true;
			}
			String date = ConvertUtil.convertDateToString(list.get(0).getCREATEDATE());
			for(int i =1;i<list.size();i++){
				if(!date.equals(ConvertUtil.convertDateToString(list.get(i).getCREATEDATE()))){
					return false;
				}
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static boolean IsIdEqual(List<Water> list){
		try{
			if(list.size() ==1){
				return true;
			}
			String id = list.get(0).getSITEID();
			for(int i =1;i<list.size();i++){
				if(!id.equals(list.get(i).getSITEID())){
					return false;
				}
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static boolean checkPid(List<Water> list){
		try{
			if(list.size() <3){
				return true;
			}
			int sum = 0;
			for(int i =0;i<list.size();i++){
				String id = list.get(i).getPARAMETERID();
				if((!"2".equals(id))&&(!"3".equals(id))&&(!"5".equals(id))){
					return false;
				}
				else{
					sum += Integer.parseInt(id);
				}
			}
			if(sum != 10){
				return false;
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static boolean IsNullJob_type(OlexamCyryJbxx list){
		try{
				int requestType = list.getREQUEST_TYPE();
				String jobType = list.getJOB_TYPE();
				if(requestType == 2 && Nulls.isEmpty(jobType)){
					return false;
				}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean IsBHG(OlexamCyryTjxx list){
		try{
			//体检结果
			int IS_ELIGIBLE = list.getIS_ELIGIBLE();
			Date EXAM_TIME = list.getEXAM_TIME();
			Date HEP_DATE = list.getHEP_DATE();
			Date DYS_DATE = list.getDYS_DATE();
			Date TYP_DATE = list.getTYP_DATE();
			Date TUB_DATE = list.getTUB_DATE();
			Date DER_DATE = list.getDER_DATE();
			Date OTH_DATE = list.getOTH_DATE();
			Integer HEART_ISGOOD = list.getHEART_ISGOOD();
			Integer LUNG_ISGOOD = list.getLUNG_ISGOOD();
			Integer SPLEEN_ISGOOD = list.getSPLEEN_ISGOOD();
			Integer LIVER_ISGOOD = list.getLIVER_ISGOOD();
			Integer SKIN_ISGOOD = list.getSKIN_ISGOOD();
			String OTHER_ISGOOD = list.getOTHER_ISGOOD();
			Integer X_RAY_ISGOOD = list.getX_RAY_ISGOOD();
			Integer SHIGELLA_ISGOOD = list.getSHIGELLA_ISGOOD();
			Integer TYPHOID_ISGOOD = list.getTYPHOID_ISGOOD();
			Integer GPT_ISGOOD = list.getGPT_ISGOOD();
			Integer HAVIGM_ISGOOD = list.getHAVIGM_ISGOOD();
			Integer ANTI_HAVIGM_ISGOOD = list.getANTI_HAVIGM_ISGOOD();
			Integer RPR_ISGOOD = list.getRPR_ISGOOD();
			Integer HIV_ISGOOD = list.getHIV_ISGOOD();
			
			if(IS_ELIGIBLE == 1 && "".equals(EXAM_TIME) && "".equals(HEP_DATE) && "".equals(DYS_DATE) && "".equals(TYP_DATE) && "".equals(TUB_DATE)
					 && "".equals(DER_DATE) && "".equals(OTH_DATE) && "".equals(OTHER_ISGOOD) && HEART_ISGOOD == null && LUNG_ISGOOD == null
					 && SPLEEN_ISGOOD == null && LIVER_ISGOOD == null && SKIN_ISGOOD == null && X_RAY_ISGOOD == null && SHIGELLA_ISGOOD == null
					 && TYPHOID_ISGOOD == null && GPT_ISGOOD == null && HAVIGM_ISGOOD == null && ANTI_HAVIGM_ISGOOD == null
					 && RPR_ISGOOD == null && HIV_ISGOOD == null){
				return false;
			}
			
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
}
