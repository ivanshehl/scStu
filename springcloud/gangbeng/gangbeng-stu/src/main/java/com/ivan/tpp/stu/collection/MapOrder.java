package com.ivan.tpp.stu.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapOrder {
	
	public static void main(String[] args) {
//		Map<String,String> map1 = new HashMap<String,String>();
//		map1.put("4", "Four");
//		map1.put("3", "Three");
//		map1.put("2", "Two");
//		map1.put("1", "One");
//		System.out.println("HashMap       ：" + map1);
 
		Map<String,String> map2 = new LinkedHashMap<String,String>();
		map2.put("4", "Four");
		map2.put("3", "Three");
		map2.put("2", "Two");
		map2.put("1", "One");
		System.out.println("LinkedHashMap ：" + map2);
 
//		Map<String,String> map3 = new TreeMap<String,String>();
//		map3.put("4", "Four");
//		map3.put("3", "Three");
//		map3.put("2", "Two");
//		map3.put("1", "One");
//		// 打印结果说明：TreeMap会根据Map的key值，进行从小到大的排序
//		System.out.println("TreeMap       ：" + map3);
	}
	
}
