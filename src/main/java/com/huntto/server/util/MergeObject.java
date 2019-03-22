/*
 * 系统名称：卫生监督管理系统
 * 版权所有：(c)2014 杭州汉图信息技术有限公司，所有版权保留
 * 版权声明：本软件所有权归杭州汉图信息技术有限公司，
 *          未经杭州汉图信息技术有限公司同意，禁止拷贝、修改和发布本系统代码。
 * 开发日期：2014年2月
 */
package com.huntto.server.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 返回两个对象的合并,相同属性的值如果convertedObject中包含,且不为null的话取它的值,否则取returnedObject的值
 * @author cao.yazhen
 */
public class MergeObject {
	 /**
     * 将相同类型的对象的内容向右合并
     * @param beanType 返回对象的类型
     * @param initObject 包含原始数据的对象
     * @param updateObject  包含修改后数据的对象
     * @return  返回两个对象的合并,相同属性的值如果convertedObject中包含,且不为null的话取它的值,否则取returnedObject的值
     */
    @SuppressWarnings("unchecked")
    public static Object extendObject(Object beanType, Object initObject, Object updateObject){
        Map map1 = BeanToMap(initObject);
        Map map2 = BeanToMap(updateObject);
        List list = getMapKeySet(map1);
        for(int i=0; i<list.size(); i++){
            Object map2Value = map2.get(list.get(i));
            if(null!=map2Value){
                map1.put(list.get(i), map2Value);
            }
        }
        return MapToBean(beanType, map1);
    }

    /**
     * 将map转化为bean
     * @param bean 将要转化成为的对象
     * @param map  被转化的map对象
     */
    @SuppressWarnings("unchecked")
    public static Object MapToBean(Object bean,Map map){
        Object type = null;
        try {
            type = bean.getClass().newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            for(PropertyDescriptor p: beanInfo.getPropertyDescriptors()){
                String propertyName = p.getName();
                Class<?> propertyType = p.getPropertyType();
                Object mapValue = map.get(propertyName);
                if(null!=mapValue&&!"class".equals(propertyName)){//日期格式
	                if(propertyType.toString().equals("class java.util.Date")){
	                        p.getWriteMethod().invoke(type, ConvertUtil.toParseDate((String)mapValue));
	                }else if(propertyType.toString().equals("class java.lang.Integer")){//整型
	                    p.getWriteMethod().invoke(type, Integer.parseInt((String)mapValue));
	                }else if (propertyType.toString().equals("class java.lang.Float")) {//Float
	                	 p.getWriteMethod().invoke(type, Float.parseFloat((String)mapValue));
					}else if (propertyType.toString().equals("class java.lang.String")) {//字符型
	                    p.getWriteMethod().invoke(type, mapValue);
	                }else if(propertyType.toString().equals("class java.lang.Double")){//Double
	                	p.getWriteMethod().invoke(type, Double.parseDouble((String)mapValue));
	                }
	            }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
        return type;
    }

    /**
     * 将bean转化为map
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map BeanToMap(Object object){
        Map map = null ;
        try {
            map = BeanUtils.describe(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获得对应Map的键值
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List getMapKeySet(Map map){
        List list = new ArrayList();
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

//  /**
//   * @param args
//   */
//  public static void main(String[] args) throws Exception{
//      Test a = new Test();
//      a.setAa1("1");
//      a.setAa2(1);
//      a.setAa3(new Date());
//      a.setAa4(Float.valueOf("1.0"));
//      a.setAa5("2");
//      
//      Test b = new Test();
//      b.setAa1("5");
//      b.setAa3(new Date());
//      a.setAa4(Float.valueOf("5.0"));
//      
//      Test c = (Test)extendObject(new Test(),a,b);
//      
//      System.out.println(c.getAa1());
//      System.out.println(c.getAa2());
//      System.out.println(c.getAa3());
//      System.out.println(c.getAa4());
//      System.out.println(c.getAa5());
//      
//  }

}
