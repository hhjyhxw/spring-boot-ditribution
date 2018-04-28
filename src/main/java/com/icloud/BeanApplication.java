package com.icloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
//@EnableAutoConfiguration  
//@ComponentScan   //这两个注解可以使用SpringBootApplication替代  
@MapperScan("com.icloud.dao.*")/** 扫描mybatis mapper接口 */			
@PropertySource({"classpath:config.properties","classpath:jdbc.properties"})
@EnableTransactionManagement/**启用注解事务管理**/
public class BeanApplication extends SpringBootServletInitializer{	
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		  
	        return application.sources(BeanApplication.class);
	    }
	public static void main(String[] args) {
		SpringApplication.run(BeanApplication.class, args);
	}
	
}
