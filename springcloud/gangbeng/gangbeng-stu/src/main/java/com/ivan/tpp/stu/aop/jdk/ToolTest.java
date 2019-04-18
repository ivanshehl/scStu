package com.ivan.tpp.stu.aop.jdk;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;

public class ToolTest {
	public static void main(String[] args) throws Exception {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		Tool tool = (Tool)ToolProxyFactory.getProxy(new Bike()); 
		tool.doSth();
//		Class.forName("com.ivan.tpp.stu.aop.jdk.Tool").getM
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		for(int i = 0; i < 5; i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					List<Tool> list = Lists.newArrayList();
					while(true){
						try {
							Thread.currentThread().sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						for(int i = 0; i < 10000; i++){
							list.add(new Bike());
						}
					}
				}
			});
		}
		
		while(true){
			Thread.currentThread().sleep(10000);
//			System.gc();
		}
	}
	
}
