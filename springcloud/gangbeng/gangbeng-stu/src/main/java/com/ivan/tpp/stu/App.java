package com.ivan.tpp.stu;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	JSONArray array = new JSONArray(5);
    	JSONObject jsonObject = null;
    	for(int i = 0; i < 5; i++){
    		jsonObject = new JSONObject();
    		jsonObject.put("phone", "1231346546");
    		jsonObject.put("ivr", "Y");
    		array.add(jsonObject);
    	}
//        BigDecimal fenzi = BigDecimal.valueOf(0);
//        BigDecimal mu = BigDecimal.valueOf(0);
//        System.out.println(fenzi.divide(mu).doubleValue());
//    	double fenzi = 10;
//    	double mu = 0;
//    	System.out.println(fenzi/mu);
    	Map<String,String> map = Maps.newHashMap();
    	map.put("code", "000000");
    	map.put("message", "成功");
    	map.put("contractId", "U234235435345456");
    	map.put("phone", "456412313645411");
    	map.put("phoneIvr", "Y");
    	map.put("type", "asset");
    	map.put("score", "60");
    	map.put("contactIvrList", array.toJSONString());
    	System.out.println(JSON.toJSON(map));
    	map = Maps.newHashMap();
    	map.put("code", "000000");
    	map.put("message", "成功");
    	map.put("type", "action");
    	map.put("score", "60");
    	map.put("contractId", "U234235435345456");
    	System.out.println(JSON.toJSON(map));
    }
}
