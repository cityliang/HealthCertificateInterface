package com.huntto.server.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.apache.commons.lang3.StringUtils;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;


/**
 * 格式化工具类包含日期、数值、字符串等
 * @author cao.yazhen
 */
public final class ConvertUtil {
    protected static final Log log = LogFactory.getLog(ConvertUtil.class);
    private static final String TIME_PATTERN = "HH:mm";
    
    /**
     * 东软 获取入参 值
     * @param xml
     * @param name
     * @return
     * @throws DocumentException
     */
    public static String xml2Str(String xml,String name) throws DocumentException {
    	
    	Document doc = DocumentHelper.parseText(xml);
		Element rootElt = doc.getRootElement(); // 获取根节点
		Element reqconditionEle = rootElt.element("business").element("requestset").element("reqcondition");
		Iterator iterssd = reqconditionEle.elementIterator("condition");
		String NAME = null;
    	while (iterssd.hasNext()) {
            Element conditionEle = (Element) iterssd.next();
            NAME = conditionEle.elementTextTrim(name); 
    	}
        return NAME;
    }
    

    /**
     * Return default datePattern (yyyy-MM-dd)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern = "yyyy-MM-dd";
        return defaultDatePattern;
    }

    public static String getDateTimePattern() {
		return ConvertUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException when String doesn't match the expected format
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }
    
    /**
    * This method returns the current date in the format: yyyy
    *
    * @return the current date
    * @throws ParseException when String doesn't match the expected format
    */
   public static String getYearToday() throws ParseException {
       Date today = new Date();
       SimpleDateFormat df = new SimpleDateFormat("yyyy");
       String todayYearAsString = df.format(today);
       return todayYearAsString;
   }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }
    
    /**
     * yyyyMMdd
     * @param aDate
     * @return
     */
    public static String convertDateToD8String(Date aDate) {
        return getDateTime("yyyyMMdd", aDate);
    }
    /**
     * yyyyMMdd
     * @param aDate
     * @return
     */
    public static String convertDateToD14String(Date aDate) {
    	return getDateTime("yyyyMMdd", aDate);
    }

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private ConvertUtil() {
    }

    /**
	 * String 2 Long
	 */
	public static Long toLong(String str){
		Long i = null;
		try {
			if(Nulls.isNotEmpty(str))
				i = new Long(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * String 2 Long,有默认值
	 */
	public static Long toLong(String str, long defaultVal){
		Long i = new Long(defaultVal);
		try {
			if(Nulls.isNotEmpty(str)){
				i = new Long(str);
			}
		} catch (Exception e) {
		}
		return i;
	}
	
	/**
	 * BigDecimal类型转换：转不了，返回null
	 */
	public static BigDecimal toBigDecimal(String str){
		BigDecimal bd = null;
		try {
			if(Nulls.isNotEmpty(str)){
				bd = new BigDecimal(str);
			}
		} catch (Exception e) {
		}
		return bd;
	}
	
	/**
	 * BigDecimal类型转换：转不了，抛异常
	 */
	public static BigDecimal toBigDecimal(String str, String expMsg){
		BigDecimal bd = null;
		try {
			if(Nulls.isNotEmpty(str)){
				bd = new BigDecimal(str);
			} else {
				throw new RuntimeException(expMsg);
			}
		} catch (Exception e) {
			throw new RuntimeException(expMsg);
		}
		return bd;
	}
	
	/**
	 * BigDecimal类型转换：转不了，默认值
	 */
	public static BigDecimal toBigDecimal(String str, double defaultVal){
		BigDecimal bd = new BigDecimal(defaultVal);
		try {
			if(Nulls.isNotEmpty(str)){
				bd = new BigDecimal(str);
			}
		} catch (Exception e) {
		}
		return bd;
	}
	
	/**
	 * Short类型转换
	 */
	public static Short toShort(String str){
		Short st = null;
		if(null != str && !"".equals(str)){
			st = new Short(str);
		}
		return st;
	}
	
	/**
	 * Integer类型转换
	 */
	public static Integer toInteger(String str){
		Integer i = null;
		if(null != str && !"".equals(str)){
			i = new Integer(str);
		}
		return i;
	}
	/**
	 * Float类型转换
	 */
	public static Float toFloat(String str){
		Float i = null;
		if(null != str && !"".equals(str)){
			i = new Float(str);
		}
		return i;
	}
	/**
	 * Float类型转换
	 */
	public static Float toFloat(String str,float defaultVal){
		Float i = defaultVal;
		if(null != str && !"".equals(str)){
			i = new Float(str);
		}
		return i;
	}
		
	/**
	 * 字符串转日期，优先考虑DateConverter
	 */
	public static Date toParseDate(String time)  {
		/** */
		/**
		 * 字符串转换为java.util.Date 支持格式为:
		 * yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD' 
		 * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00' 
		 * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm' 
		 * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' 
		 * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am'
		 * 
		 * @param time
		 *            String 字符串
		 * @return Date 日期
		 */
		try
		{
		SimpleDateFormat formatter;
		
		int tempPos = time.indexOf("AD");
		time = time.trim();
		formatter = new SimpleDateFormat(" yyyy.MM.dd G 'at' hh:mm:ss z ");
		if (tempPos > -1) {
			time = time.substring(0, tempPos) + " 公元 "
			+ time.substring(tempPos + " AD ".length()); // china
			formatter = new SimpleDateFormat(" yyyy.MM.dd G 'at' hh:mm:ss z ");
		}
		
		tempPos = time.indexOf("-");
		
		if (time.indexOf(".") != -1) {
			time = time.substring(0, time.indexOf("."));
		}
		
		if (tempPos > -1) {//包含“-”
			if(time.indexOf(":") == -1){
				formatter = new SimpleDateFormat("yyyy-MM-dd");
			} else if (time.indexOf(":") == time.lastIndexOf(":")) {
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			} else {
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
		} else if (time.indexOf("/") > -1) {//包含“/”
			if(time.indexOf(":") == -1){
				formatter = new SimpleDateFormat("yyyy/MM/dd");
			}else{
				formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			}
		} else if (time.substring(0, 8).indexOf("-") == -1) {
			time = time.substring(0, 8);
			formatter = new SimpleDateFormat("yyyyMMdd");
		} else {
			formatter = new SimpleDateFormat("HH:mm");
		}
		
		ParsePosition pos = new ParsePosition(0);
		java.util.Date ctime = formatter.parse(time, pos);
		return ctime;
		
		}
		catch(Exception ex)
		{ return null;}
		
	}
	
	/**
	 * 格式化日期
	 */
	public static String dataFormat(Date date, String pattern){
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.format(date);
		} catch (Exception e) {
			return "";
		}
		
	}
	
	/**
	 * 获取当前日期第一天
	 */
	public static Date getFirstDayByMonth(Date date) throws Exception{
		Calendar cal = Calendar.getInstance();   
		cal.setTime(date);   
		int stDate = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-");
		String startDate1 = sdf.format(date).concat(String.valueOf(stDate));
		return toParseDate(startDate1);
	}
	
	/**
	 * 获取当前提前最后一天
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getLastDayByMonth(Date date) throws Exception{
		Calendar cal = Calendar.getInstance();   
		cal.setTime(date);   
		int enDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-");
		String endDate1 = sdf.format(date).concat(String.valueOf(enDate));
		return toParseDate(endDate1);
	}
	
	/**
	 * 获取当前日期下一天
	 */
	public static Date getNexDate(Date date)throws Exception{
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(date);   
		int day=calendar.get(Calendar.DATE);   
		calendar.set(Calendar.DATE,day+1);
		Date dat = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		String  date1   = sdf.format(dat) ; 
		Date d = sdf.parse(date1);
		return d;
	}
	/**
	 * 获取当前日期前一天
	 */
	public static Date getForwardOneDate(Date date){
		try {
			Calendar calendar = Calendar.getInstance();   
			calendar.setTime(date);   
			int day=calendar.get(Calendar.DATE);   
			calendar.set(Calendar.DATE,day - 1);
			Date dat = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			String  date1  = sdf.format(dat) ; 
			Date d = sdf.parse(date1);
			return d;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取当前日期前一个月
	 */
	public static String getForwardOneMon(String date){
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(toParseDate(date));
			//calendar.add(Calendar.DATE, -1); // 得到前一天
			calendar.add(Calendar.MONTH, -1); // 得到前一个月
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day= calendar.get(Calendar.DAY_OF_MONTH);
			return String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取当前日期 N年后 的前一天
	 * @param year	几年后
	 * @param date	当前日期
	 * @return
	 */
	public static Date getForwardOneDateAfterNYear(int year,Date date){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			Calendar calendar = Calendar.getInstance();   
			calendar.setTime(date); 
			String thisYear = sdf.format(date).substring(0,4);
			int day=calendar.get(Calendar.DATE);   
			calendar.set(Calendar.DATE,day - 1);
			calendar.set(Calendar.YEAR,Integer.valueOf(thisYear)+year);
			Date dat = calendar.getTime();
			String  date1  = sdf.format(dat) ; 
			Date d = sdf.parse(date1);
			return d;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取当前日期前10天
	 */
	public static Date getForwardTenDate(Date date){
	  try {
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(date);   
	    int day=calendar.get(Calendar.DATE);   
	    calendar.set(Calendar.DATE,day - 10);
	    Date dat = calendar.getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	    String  date1  = sdf.format(dat) ; 
	    Date d = sdf.parse(date1);
	    return d;
    } catch (Exception e) {
      return null;
    }
		
	}
	/**
	 * 获取当前日期前几天
	 */
	public static Date getForwardSumeDate(Date date,int days){
		try {
			Calendar calendar = Calendar.getInstance();   
			calendar.setTime(date);   
			int day=calendar.get(Calendar.DATE);   
			calendar.set(Calendar.DATE,day - days);
			Date dat = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			String  date1  = sdf.format(dat) ; 
			Date d = sdf.parse(date1);
			return d;
		} catch (Exception e) {
			return null;
		}
		
	}
	/**
	 * 获取当前日期后7天
	 */
	public static Date getSevenDate(Date date){
	  try {
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(date);   
	    int day=calendar.get(Calendar.DATE);   
	    calendar.set(Calendar.DATE,day + 6);
	    Date dat = calendar.getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	    String  date1  = sdf.format(dat) ; 
	    Date d = sdf.parse(date1);
	    return d;
    } catch (Exception e) {
      return null;
    }
		
	}
	
	/**
	 * 处理当前日期去掉小时分钟秒
	 */
	public static Date getCurrentDate(Date date)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		String  date1   = sdf.format(date) ; 
		Date d = sdf.parse(date1);
		return d;
	}
	
	/**
	 * 处理行政区划代码
	 * 只取行政编码的有效位
	 */
	public static String handleXZQH(String qhdm){
		StringBuffer sb = new StringBuffer();
		if(qhdm.length() == 9){
			String sheng = qhdm.substring(0,2);//省
			String shi = qhdm.substring(2,4);//市
			String xq = qhdm.substring(4,6);//县区
			String jd = qhdm.substring(6,9);//乡镇街道
			if(sheng.indexOf("00") == -1){
				sb.append(sheng);
			}
			if(shi.indexOf("00") == -1){
				sb.append(shi);
			}
			if(xq.indexOf("00") == -1){
				sb.append(xq);
			}
			if(jd.indexOf("000") == -1){
				sb.append(jd);
			}
			return sb.toString();
		}else{
			return qhdm;
		}
	}
	/**
	 * 处理行政区划代码
	 * 将行政区划代码补齐九位
	 */
	public static String handleXZQH2Nine(String qhdm){
		StringBuffer sb = new StringBuffer();
		int len = qhdm.length();
		if(len != 9){
			sb.append(qhdm);
			for (int i = 0; i < 9 - len; i++) {
				sb.append("0");
			}
			return sb.toString();
		}else{
			return qhdm;
		}
	}
	/**
	 * 处理行政区划代码
	 * 将行政区划代码补齐6位
	 */
	public static String handleXZQH2Six(String qhdm){
		StringBuffer sb = new StringBuffer();
		int len = qhdm.length();
		if(len < 6){
			sb.append(qhdm);
			for (int i = 0; i < 6 - len; i++) {
				sb.append("0");
			}
			return sb.toString();
		}else{
			return qhdm.substring(0,6);
		}
	}
	
	/**
	 * 生成sql select语句中的regioncode
	 * @param regionCode 行政区划代码，9位
	 * @param regionCol 表中的根据行政区划查询的列名
	 * @return
	 */
	public static String generateSelectRegionCode(String regionCode,String regionCol){
		String str = handleXZQH(regionCode);
		switch (str.length()) {
		case 2:
			return "substr("+regionCol+",1,4)||'00000'";
		case 4:
			return "substr("+regionCol+",1,6)||'000'";
		case 6:
			return "substr("+regionCol+",1,9)";
		default:
			return "substr("+regionCol+",1,9)";
		}
	}
	
	/**
	 * 生成sql where like语句中的regioncode
	 * @param regionCode 行政区划代码，9位
	 * @return
	 */
	public static String generateWhereLikeRegionCode(String regionCode){
		String str = handleXZQH(regionCode);
		switch (str.length()) {
		case 2:
			return "'"+str+"%00000'";
		case 4:
			return "'"+str+"%000'";
		case 6:
			return "'"+str+"%'";
		default:
			return str;
		}
	}
	
	/**
	 * 生成sql where <>语句中的regioncode
	 * @param regionCode 行政区划代码，9位
	 * @return
	 */
	public static String generateWhereNQRegionCode(String regionCode){
		String str = handleXZQH(regionCode);
		switch (str.length()) {
		case 2:
			return "'"+str+"0000000'";
		case 4:
			return "'"+str+"00000'";
		case 6:
			return "'"+str+"000'";
		default:
			return str;
		}
	}
	
	/**
	 * 生成sql group by语句中的regioncode
	 * @param regionCode 行政区划代码，9位
	 * @return
	 */
	public static String generateGroupByRegionCode(String regionCode,String regionCol){
		String str = handleXZQH(regionCode);
		switch (str.length()) {
		case 2:
			return "substr("+regionCol+",1,4)";
		case 4:
			return "substr("+regionCol+",1,6)";
		case 6:
			return "substr("+regionCol+",1,9)";
		default:
			return "substr("+regionCol+",1,9)";
		}
	}
	
	/**
	 * 系统生成标识管理相对人的唯一编号
	 * 推荐使用S18，管理相对人所属行政区划（精确至区县，六位）的编码和系统日期（六位）、系统自动生成的流水号（六位）
	 * @param qhdm 地区编码 （精确至区县，六位）
	 */
	public static String generateCOMP_NO(int qhdm){
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(qhdm).substring(0, 6));
		String dateStr = dataFormat(new Date(),"yyMMdd");
		sb.append(dateStr);
		String timeStr = dataFormat(new Date(),"HHmmss");
//		timeStr=timeStr.substring(timeStr.length()-9,timeStr.length()-3);
		sb.append(timeStr);
		return sb.toString();
	}
	
	/**
	 * 系统生32位唯一编号
	 */
	public static String generateID(){
		String uuid=java.util.UUID.randomUUID().toString();
		return uuid.substring(uuid.length()-32);
	}
	/**
	 *生成一个时间戳字符串，用于附件上传 
	 * @return 时间戳字符串，例如“20130101090101”
	 */
	public static String generateTimeStamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");   
		Date now = new Date(); 
		return sdf.format(now);
	}
	
	/**
	 * 生成一个当前时间字符串，格式是"yyyy-MM-dd HH:mm:ss"
	 * @return 当前时间字符串
	 */
	public static String getTimeOfNow(){
		Date now = new Date();
		return formatDate(now, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 返回一个当前日期的字符串，格式是yyyy-MM-dd
	 * @return
	 */
	public static String getDateOfNow(){
		Date today = new Date();
		return formatDate(today, "yyyy-MM-dd");
	}
	
	/**
	 * 返回一个当前日期的字符串，格式是yyyy年MM月dd日
	 * @return
	 */
	public static String getDateOfNow2(){
		Date today = new Date();
		String year = String.valueOf(today.getYear()+1900);
		int mm = today.getMonth()+1;
		String month = String.valueOf(mm);
		if(mm<10){
			month = "0"+month;
		}
		int dd = today.getDate();
		String day = String.valueOf(dd);
		if(dd<10){
			day = "0"+day;
		}
		String time = year+"年  "+month+"月  "+day+"日";
		return time;
	}
	
	public static String formatDate(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);   
		return sdf.format(date);
	}
	
	/**
	 * 生成附件上传的唯一标示，每个pojo类统一字段FJUPLOADID
	 * @return
	 */
	public static String generateFJUploadID(){
		return ConvertUtil.generateID();
	}
	
	/**
	 * 生成报告卡头
	 */
	public static String prepBGKXML(String bizType,String speCode,
			String operate,String regionCode,String sendTime,String sendCode){
		StringBuffer sb = new StringBuffer();
		sb.append("<DSCR>\n");
		sb.append("<BIZTYPE>"+bizType+"</BIZTYPE>\n");
		sb.append("<SPECODE>"+speCode+"</SPECODE>\n");
		sb.append("<OPERATE>"+operate+"</OPERATE>\n");
		sb.append("<REGIONCODE>"+regionCode+"</REGIONCODE>\n");
		sb.append("<SENDTIME>"+sendTime+"</SENDTIME>\n");
		sb.append("<VERSIONS>2012000001</VERSIONS>\n");
		sb.append("<SENDCODE>"+sendCode+"</SENDCODE>\n");
		sb.append("</DSCR>\n");
		return sb.toString();
	}
	
	/**
	 * byte数组转换为二进制字符串
	 **/
	public static String byteArrToBinStr(byte[] b) {
	    StringBuffer result = new StringBuffer();
	    for (int i = 0; i < b.length; i++) {
	        result.append(Long.toString(b[i] & 0xff, 2));
	    }
	    return result.toString().substring(0, result.length() - 1);
	}
	
	/**
	 * 转换性别
	 * 
	 * @param xb xb
	 * @return
	 */
	public static String toXB(String xb) {
		if (StringUtils.isNotBlank(xb)) {
			if ("1".equals(xb)) {
				xb = "男";
			} else {
				xb = "女";
			}
		} else {
			xb = "未录入性别";
		}
		return xb;
	}

	/**
	 * 转换体检结果
	 * 
	 * @param LB LB
	 * @return
	 */
	public static String toLB(String LB) {
		if (StringUtils.isNotBlank(LB)) {
			if ("1".equals(LB)) {
				LB = "食品卫生";
			} else if ("2".equals(LB)) {
				LB = "公共场所卫生";
			} else if ("3".equals(LB)) {
				LB = "食品/公共场所";
			} else if ("4".equals(LB)) {
				LB = "其他";
			}
		} else {
			LB = "未查到类别";
		}
		return LB;
	}

	public static String toTJJG(String TJJG) {
		if (StringUtils.isNotBlank(TJJG)) {
			if ("1".equals(TJJG)) {
				TJJG = "合格";
			} else if ("0".equals(TJJG)) {
				TJJG = "不合格";
			} else if (null == TJJG || "''".equals(TJJG)) {
				TJJG = "办理中";
			}
		} else {
			if(null == TJJG || "''".equals(TJJG)) {
				TJJG = "办理中";
			}else {
				TJJG = "未体检";
			}
		}
		return TJJG;
	}
	
	public static String toFBType(String FBType) {
		// 1姓名错误 2性别错误 3类别错误 4体检结果错误 5身份证号错误 6二维码错误 7头像错误
		StringBuffer sb = new StringBuffer();
		if (Nulls.isNotEmpty(FBType)) {
			String[] str = FBType.split(",");
			if (null != str && "".equals(str.toString())) {
				List<String> list = Arrays.asList(str);
				if (null != list && !list.isEmpty()) {
					if (list.contains("1")) {
						sb.append("姓名错误");
						sb.append(",");
					}
					if (list.contains("2")) {
						sb.append("性别错误");
						sb.append(",");
					}
					if (list.contains("3")) {
						sb.append("类别错误");
						sb.append(",");
					}
					if (list.contains("4")) {
						sb.append("体检结果错误");
						sb.append(",");
					}
					if (list.contains("5")) {
						sb.append("身份证号错误");
						sb.append(",");
					}
					if (list.contains("6")) {
						sb.append("二维码错误");
						sb.append(",");
					}
					if (list.contains("7")) {
						sb.append("头像错误");
					}
				}
			}
			return sb.toString();
		} else {
			return "";
		}
	}

}
