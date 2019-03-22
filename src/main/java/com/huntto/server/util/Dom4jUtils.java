package com.huntto.server.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLDecoder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 解析xml 工具类 
 * @author cao.yazhen
 */
public class Dom4jUtils {
	/**
	 * @param str
	 * @return
	 * @throws IOException
	 * 字符串转dom4j document
	 */
	public static Document String2Document(String str) throws IOException {
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(new ByteArrayInputStream(str.getBytes("UTF-8")));
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * @param str
	 * @return
	 * @throws IOException
	 * dom4j document转字符串
	 */
	public static String Document2String(Document doc) throws IOException {
		try {
			StringWriter out = new StringWriter(1024);  
			XMLWriter writer = new XMLWriter(out,OutputFormat.createPrettyPrint());
			writer.write(doc);
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * @param str
	 * @return
	 * @throws IOException
	 * dom4j document转格式化字符串
	 */
	public static String FormatXML(Document doc) throws Exception {  
        StringWriter out=null;  
        try{  
            OutputFormat formate=OutputFormat.createPrettyPrint();  
            out=new StringWriter();  
            XMLWriter writer=new XMLWriter(out,formate);  
            writer.write(doc);  
        } catch (IOException e){  
           e.printStackTrace();  
        } finally{  
            out.close();   
        }  
        return out.toString();  
    }
	
	/**
	 * @param str
	 * @return
	 * @throws IOException
	 * 文件转dom4j document
	 */
	public static Document File2Document(String fileName)
	{
		try {
			SAXReader xmlReader = new SAXReader();
			Document xmlDocument = (Document) xmlReader.read(new File(
					fileName));
			return xmlDocument;
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * @param str
	 * @return
	 * @throws IOException
	 * 文件转String(xml模板加载)
	 */
	public static String File2String(String fileName)
	{
		try {
			String path=Dom4jUtils.class.getClassLoader().getResource("/")
					.getPath()+"exchange"+File.separator+"xmlTemplate"+File.separator+fileName;
			path=URLDecoder.decode(path,"utf-8");
			return Document2String(File2Document(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		}
	}
}
