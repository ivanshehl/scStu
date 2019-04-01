package com.ivan.tpp.boot.config;

import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartLifecycleConfig {

	@Bean
	public SmartLifecycle smartLifecycleBuild(){
		return new TestSmartLifecycle();
	}
}
