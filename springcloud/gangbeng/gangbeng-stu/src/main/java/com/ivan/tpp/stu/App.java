package com.ivan.tpp.stu;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
    	map.put("phone", "Y");
    	map.put("contact1", "Y");
    	map.put("contact2", "Y");
    	map.put("type", "asset");
    	map.put("score", "60");
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
