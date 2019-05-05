package com.ivan.tpp.boot.business.user.service.impl;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldCommand extends HystrixCommand<String> {
    
    public HelloWorldCommand() {
        //至少要指定一个 command group name 的值(CommandGroup)
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"),100000);
    }

    @Override
    protected String run() {
        // 在run () 方法中包括实际的业务逻辑
    	try {
//    		System.out.println("123");
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Hello " + "" +" thread:" + Thread.currentThread().getName();
    }
    
    @Override
    protected String getFallback() {
        return "Hello Failure " + "" + "!";
    }
}
