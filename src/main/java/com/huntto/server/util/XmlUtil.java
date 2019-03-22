package com.huntto.server.util;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;

/**
 * xml转化工具类
 * 
 * @author cao.yazhen
 */
public class XmlUtil {

	/**
	 * org.w3c.dom.Document -> org.dom4j.Document
	 * 
	 * @param doc Document(org.w3c.dom.Document)
	 * @return Document
	 */
	public static Document parseW3cXML(org.w3c.dom.Document doc) throws Exception {
		if (doc == null) {
			return (null);
		}
		org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
		return (Document) (xmlReader.read(doc));
	}

	/**
	 * org.dom4j.Document -> org.w3c.dom.Document
	 * 
	 * @param doc Document(org.dom4j.Document)
	 * @throws Exception
	 * @return Document
	 */
	public static org.w3c.dom.Document parseDom4jXML(Document doc) throws Exception {
		if (doc == null) {
			return (null);
		}
		java.io.StringReader reader = new java.io.StringReader(((org.dom4j.Node) doc).asXML());
		org.xml.sax.InputSource source = new org.xml.sax.InputSource(reader);
		javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory = javax.xml.parsers.DocumentBuilderFactory
				.newInstance();
		javax.xml.parsers.DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		return (documentBuilder.parse(source));
	}

	/**
	 * 格式化XML
	 * 
	 * @param xmlData
	 * @param xslFileName
	 */
	public static String formatXmlFromXsl(String xmlData, String xslFileName) {
		String xml = "";
		try {
			StringWriter writer = new StringWriter();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			byte[] bytes = xmlData.getBytes("utf-8");
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			Document document = builder.parse(new InputSource(bais));
			DOMSource source = new DOMSource(document);

			TransformerFactory tFac = TransformerFactory.newInstance();
			Source xslSource = new StreamSource(xslFileName);
			Transformer t = tFac.newTransformer(xslSource);
			Result result = new StreamResult(writer);
			t.transform(source, result);
			xml = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	/**
	 * MAP->XML
	 */
	public static String map2Xml(Map hashMap) {

		if (hashMap == null || hashMap.isEmpty()) {
			return "";
		}

		StringBuffer strBuffer = new StringBuffer();
		Iterator it = hashMap.keySet().iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = hashMap.get(key);
			strBuffer.append("<" + key + "><![CDATA[" + (value == null ? "" : value) + "]]></" + key + ">\n");
		}

		return strBuffer.toString();
	}

	/**
	 * XML->MAP
	 */
	public static Map<String, String> xml2Map(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		String[] fields = xml.split("<");
		for (String str : fields) {
			if (str.indexOf("/") == 0) {
				continue;
			}
			String[] keyValue = str.trim().split(">");
			try {
				map.put(keyValue[0].toUpperCase(), keyValue[1]);
			} catch (Exception e) {
				map.put(keyValue[0].toUpperCase(), "");
			}
		}
		return map;
	}

	/**
	 * ENTITY->XML
	 */
	public static String entity2Xml(Object obj) {
		XStream xstream = new XStream();
		xstream.alias(obj.getClass().getSimpleName(), obj.getClass());
		return xstream.toXML(obj);
	}

	/**
	 * list转化为xml
	 * 
	 * @param java.util.List<Map>
	 */
	public static String getXmlMap(List list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append("<item>\n");
			Map hashMap = (HashMap) list.get(i);
			sb.append(getStrByMap(hashMap));
			sb.append("</item>\n");
		}
		return sb.toString();

	}

	/**
	 * 把list转化为xml
	 */
	public static String list2Xml(List list) {

		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb.append("<DATA>\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append("<ROW>\n");
			Map hashMap = (HashMap) list.get(i);
			sb.append(getStrByMap(hashMap));
			sb.append("</ROW>\n");
		}

		sb.append("</DATA>");

		return sb.toString();
	}

	/**
	 * 把HASHMAP里面数据封装成XML
	 */
	public static String getStrByMap(Map hashMap) {

		if (hashMap.isEmpty()) {
			return "";
		}

		StringBuffer strBuffer = new StringBuffer();
		Iterator it = hashMap.keySet().iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = hashMap.get(key);
			strBuffer.append("<" + key + ">" + (value == null ? "" : value) + "</" + key + ">\n");
		}

		return strBuffer.toString();
	}

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("asdf", 123);

		System.out.println(entity2Xml(map));
	}

	public static String generateID() {
		String uuid = java.util.UUID.randomUUID().toString();
		return uuid.substring(uuid.length() - 32);
	}

	/**
	 * 解析xml字符串
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Document parse(String xml) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader reader = new StringReader(xml);
		InputSource is = new InputSource(reader);
		Document doc = db.parse(is);
		return doc;
	}

	/**
	 * 反序列化XML文档到Java对象
	 * 
	 * @param xml
	 * @return
	 */
	public static Object deserialize(Node xmlNode, Class<?>... classesToBeBound) throws Exception {
		Unmarshaller unmarshaller = createUnmarshaller(classesToBeBound);
		Object obj = unmarshaller.unmarshal(xmlNode);
		return obj;
	}

	private static Unmarshaller createUnmarshaller(Class<?>... classesToBeBound) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(classesToBeBound);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return unmarshaller;
	}
}
