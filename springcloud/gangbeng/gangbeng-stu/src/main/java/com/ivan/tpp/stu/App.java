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
    	Mobile[] mobiles = Mobile.values();
    	for(Mobile mobile : mobiles){
    		System.out.println(mobile.showPrice()+"\t"+mobile.ordinal());
    	}
    }
}
enum Mobile {
	   Samsung(400), Nokia(250),Motorola(325);
	  
	   int price;
	   Mobile(int p) {
	      price = p;
	   }
	   int showPrice() {
	      return price;
	   } 
	}

