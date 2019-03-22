package com.huntto.server.config.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.huntto.server.service.CyryService;
import com.huntto.server.service.DemoService;
import com.huntto.server.service.WaterService;
import com.huntto.server.service.WjService;
import com.huntto.server.service.WjwsjcService;
import com.huntto.server.service.YlwsService;
import com.huntto.server.service.ZhwsService;
import com.huntto.server.service.impl.CyryServiceImpl;
import com.huntto.server.service.impl.DemoServiceImpl;
import com.huntto.server.service.impl.WaterServiceImpl;
import com.huntto.server.service.impl.WjServiceImpl;
import com.huntto.server.service.impl.WjwsjcServiceImpl;
import com.huntto.server.service.impl.YlwsServiceImpl;
import com.huntto.server.service.impl.ZhwsServiceImpl;

@Configuration
public class CxfConfig {
	/**
	 * @Description  注册 WebService 服务
	 */
	@Bean
    public ServletRegistrationBean dispatcherWebServiceServlet() {
        return new ServletRegistrationBean(new CXFServlet(),"/jkz/*");
    }
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
    
    /**
     * @Description  注册 Restful 服务
     */
    @Bean
    public ServletRegistrationBean dispatcherRestServlet() {
//    	return new ServletRegistrationBean(new DispatcherServlet(), "/rest/*");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //替换成自己想买的controller包路径
        context.scan("com.huntto.server.controller");
        DispatcherServlet disp = new DispatcherServlet(context);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(disp);
        registrationBean.setLoadOnStartup(1);
        //映射路径自定义,必须设置一个不重复的名称
        registrationBean.addUrlMappings("/rest/*");
        registrationBean.setName("rest");
        return registrationBean;
    }
    /**
     * 
     * @Title  demoService 
     * @Description  Demo
     * @param  @return 
     * @return  DemoService 
     *
     */
    @Bean
    public DemoService demoService() {
        return new DemoServiceImpl();
    }
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), demoService());
        endpoint.publish("/demo");
        return endpoint;
    }
    
    /**
     * 
     * @Title  cyryService 
     * @Description  从业人员体检信息接口
     * @return  CyryService
     */
    @Bean
    public CyryService cyryService() {
        return new CyryServiceImpl();
    }
    @Bean
    public Endpoint cyryEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), cyryService());
        endpoint.publish("/cyryserver");
        return endpoint;
    }
    
    /**
     * @Description  水质监测接口
     */
    @Bean
    public WaterService waterService() {
        return new WaterServiceImpl();
    }
    @Bean
    public Endpoint waterEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), waterService());
        endpoint.publish("/waterserver");
        return endpoint;
    }
    
    /**
     * @Description  许可信息公示接口
     */
    @Bean
    public WjService wjService() {
        return new WjServiceImpl();
    }
    @Bean
    public Endpoint wjEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), wjService());
        endpoint.publish("/server");
        return endpoint;
    }
    
    /**
     * @Description  卫计委数据仓接口
     */
    @Bean
    public WjwsjcService wjwsjcService() {
        return new WjwsjcServiceImpl();
    }
    @Bean
    public Endpoint wjwsjcEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), wjwsjcService());
        endpoint.publish("/wjwsjcserver");
        return endpoint;
    }
    
    /**
     * @Description  江东区医疗机构监督信息接口
     */
    @Bean
    public YlwsService ylwsService() {
        return new YlwsServiceImpl();
    }
    @Bean
    public Endpoint ylwsEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), ylwsService());
        endpoint.publish("/ylwsserver");
        return endpoint;
    }
    
    
    /**
     * 综卫平台接口
     */
    @Bean
    public ZhwsService zhwsService() {
        return new ZhwsServiceImpl();
    }
    @Bean
    public Endpoint zhwsEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), zhwsService());
        endpoint.publish("/zhwsserver");
        return endpoint;
    }
    
}
