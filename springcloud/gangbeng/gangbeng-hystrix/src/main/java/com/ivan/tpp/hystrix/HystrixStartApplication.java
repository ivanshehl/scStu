package com.ivan.tpp.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HystrixStartApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(HystrixStartApplication.class, args);
	}

}
