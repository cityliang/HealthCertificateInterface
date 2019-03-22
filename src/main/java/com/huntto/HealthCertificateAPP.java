package com.huntto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * 
 * @ClassName  HealthCertificateAPP 
 * @Description 健康证接口启动类
 * @author 梁城市
 * @date  2019年3月22日 上午8:35:49 
 *
 */
@SpringBootApplication
public class HealthCertificateAPP extends SpringBootServletInitializer {
	public static void main(String[] args) {
        SpringApplication.run(HealthCertificateAPP.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HealthCertificateAPP.class);
	}

}
