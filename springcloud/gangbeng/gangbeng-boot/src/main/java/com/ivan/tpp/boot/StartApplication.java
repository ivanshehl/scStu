package com.ivan.tpp.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(basePackages={"com.ivan.tpp.boot"})
@MapperScan("com.ivan.tpp.boot.business.*.dao")
@ImportResource(locations = { "classpath:application-tx.xml" })
@EnableAutoConfiguration
public class StartApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(StartApplication.class, args);
	}

}
