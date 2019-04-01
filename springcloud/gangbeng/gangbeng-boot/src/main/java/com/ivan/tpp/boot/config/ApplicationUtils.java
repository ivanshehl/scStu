package com.ivan.tpp.boot.config;

import org.springframework.context.ApplicationContext;


public class ApplicationUtils {
	
	private static ApplicationContext context;
	
	public static void setApplicationContext(ApplicationContext context){
		context = context;
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	
}
