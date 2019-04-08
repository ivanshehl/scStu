package com.ivan.tpp.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//@ComponentScan(basePackages={"com.ivan.tpp.boot"})
@Configuration
@MapperScan("com.ivan.tpp.boot.business.*.dao")
@ComponentScan
@ImportResource(locations = { "classpath:application-tx.xml" })
@EnableAutoConfiguration
@EnableCircuitBreaker
public class StartApplication {
	
	public static void main(String[] args) {
//		DefaultListableBeanFactory
//		$DependencyObjectProvider@61911947
//		spring.rabbitmq
//		RabbitConnectionFactoryBean
//		AsyncRabbitTemplate
		SpringApplication.run(StartApplication.class, args);
	}
	
	

}
