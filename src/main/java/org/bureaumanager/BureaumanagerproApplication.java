package org.bureaumanager;

import org.mybatis.spring.annotation.MapperScan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan(basePackages = {"org.bureaumanager.bureaumanagerpro"})
@EnableScheduling
public class BureaumanagerproApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(BureaumanagerproApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BureaumanagerproApplication.class, args);
		logger.info("---->>Spring Boot is started!<<----");
	}

	@Override//为了打包springboot项目
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(BureaumanagerproApplication.class);

	}


}
