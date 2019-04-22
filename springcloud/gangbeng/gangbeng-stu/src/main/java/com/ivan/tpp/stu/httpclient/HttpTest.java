package com.ivan.tpp.stu.httpclient;

import java.util.Map;

import com.google.common.collect.Maps;

public class HttpTest {
	
	public static void main(String[] args) {
		aopTest();
	}
	
	public static void aopTest(){
		String url = "http://localhost:8080/userExtend/info";
		Map<String,String> params = Maps.newHashMap();
		String rlt = HttpUtils.post(url, params);
		System.out.println(rlt);
	}
}
