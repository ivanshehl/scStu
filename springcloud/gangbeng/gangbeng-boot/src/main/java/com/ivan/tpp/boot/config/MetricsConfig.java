package com.ivan.tpp.boot.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;

@Configuration
public class MetricsConfig implements ApplicationContextAware,InitializingBean{
	
	@Bean
	public MetricRegistry metricbuild(){
//		MybatisAutoConfiguration
		return new MetricRegistry();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterPropertiesSet===================");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		ApplicationUtils.setApplicationContext(applicationContext);
		System.out.println("setApplicationContext===================");
	}
}
