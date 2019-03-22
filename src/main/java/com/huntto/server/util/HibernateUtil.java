///*
// * 系统名称：卫生监督管理系统系统
// * 版权所有：(c)2012 杭州汉图信息技术有限公司，所有版权保留
// * 版权声明：本软件所有权归杭州汉图信息技术有限公司，
// *          未经杭州汉图信息技术有限公司同意，禁止拷贝、修改和发布本系统代码。
// * 开发日期：2014年2月
// */
//package com.huntto.server.util;
//
//import java.util.Properties;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.mapping.PersistentClass;
//
//
///**
// * Hibernate工具类<br/>
// * 初始化SessionFactory, 管理Session的创建和关闭.
// * @author risen
// *
// */
//final public class HibernateUtil {
//	private static Configuration cfg = null;
//	private static SessionFactory sessionFactory = null;
//	private static final ThreadLocal<Session> sessionData = new ThreadLocal<Session>();
//
//	/** 是否显示show_sql */
//	public static boolean show_sql = false;
//
//	private HibernateUtil(){}
//
//	/**
//	 * 初始化,通常只进行一次
//	 */
//	public static void init() throws HibernateException {
//		Properties properties = null;
//		System.setProperty("bgkgj.config_dir", "E:\\work\\tempWorkSpaces\\wjWebService\\webapp\\WEB-INF\\classes");
//		System.out.println(System.getProperties().getProperty("bgkgj.config_dir"));
//		try {
//			properties = Utils.loadProperties(BgkConfig.configDir, "jdbc.properties");
//		} catch (Exception e) {
//			throw new HibernateException(e.getMessage(), e);
//		}
//		
//		//其他参数
//		if(!properties.contains("hibernate.hbm2ddl.auto")){
//			properties.put("hibernate.hbm2ddl.auto", "none");
//		}
//		if(!properties.contains("hibernate.show_sql")){
//			properties.put("hibernate.show_sql", show_sql); //test only
//		}
//		
//		//sesstion context, 调用原来的dao时需要
//		//properties.put("hibernate.current_session_context_class", "thread");
//		
//		cfg = new Configuration();
//		cfg.addProperties(properties);
//		cfg.configure();
//		
//		sessionFactory = cfg.buildSessionFactory();  
//	}
//	
//	/**
//	 * 取得SessionFactory
//	 * @return
//	 */
//	public static SessionFactory getSessionFactory(){  
//		return sessionFactory; 
//	}
//
//	/**
//	 * 取得Session
//	 * @return
//	 * @throws HibernateException
//	 */
//	public static Session currentSession() throws HibernateException
//	{
//		Session session = (Session) sessionData.get();
//		
//		//如果该线程还没有Session,则创建一个新的Session
//		if (session == null)
//		{
//			session = sessionFactory.openSession();
//			sessionData.set(session);
//		}
//		
//		return session;
//	}
//	
//	/**
//	 * 创建新Session
//	 * @return
//	 * @throws HibernateException
//	 */
//	public static Session newSession() throws HibernateException
//	{
//		Session session = sessionFactory.openSession();
//		return session;
//	}
//	
//	/**
//	 * 关闭Session
//	 * @throws HibernateException
//	 */
//	public static void closeSession() throws HibernateException {
//		Session session = (Session) sessionData.get();
//		closeSession(session);
//		sessionData.set(null);
//	}
//	
//	/**
//	 * 关闭Session
//	 * @throws HibernateException
//	 */
//	public static void closeSession(Session session) {
//		if (session != null){
//			try {
//				session.close();
//			} catch (Exception e) {
//				//忽略
//			}
//		}
//	}
//
//	/**
//	 * 获取实体对应的表名
//	 * @param clazz
//	 * @return
//	 */
//	public static String getTableName(Class<?> clazz) {
//		String clazzName = clazz.getName();
//		PersistentClass pc = cfg.getClassMapping(clazzName);
//		if(pc == null){
//			throw new RuntimeException("无法取得该实体的物理表名。 实体：" + clazzName);
//		}
//		return pc.getTable().getName();  
//	}  
//}
