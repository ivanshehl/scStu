package com.ivan.tpp.boot.business.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ivan.tpp.boot.business.user.service.IHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service("hystrixService")
public class HystrixServiceImpl implements IHystrixService {
	
	private static final Logger logger = LoggerFactory.getLogger(HystrixServiceImpl.class);

	@HystrixCommand(groupKey = "GroupOne",
	// 线程池的名称
	fallbackMethod = "fallbackTest", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000000"),
			// 是否开启熔断器
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			// 请求的失败数目超过这个之后，就会打开熔断器
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			// 熔断器工作时间，默认5秒，超过这个时间便会放流量进去
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "50000"),
			//超时是否终端线程
//			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "false"),
			
			// 出错率超过75%，启动熔断器，默认50%
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"), })
	@Override
	public String login() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			logger.error("",e);
		}
		return "login success";
	}

	public String fallbackTest() {
		return "chao guo 10 ci error";
	}
}
