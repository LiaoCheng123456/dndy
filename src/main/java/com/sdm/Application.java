package com.sdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 服务启动类
 * @author rex
 * @version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
public class Application {
	
	/**
	 *	本地调试直接运行main方法即启动（springboot内置tomcat）
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
