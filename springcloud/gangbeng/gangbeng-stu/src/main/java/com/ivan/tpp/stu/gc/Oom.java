package com.ivan.tpp.stu.gc;

import java.util.List;

import com.google.common.collect.Lists;

public class Oom {
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList();
		while(true){
			list.add("abcd");
		}
	}
}
