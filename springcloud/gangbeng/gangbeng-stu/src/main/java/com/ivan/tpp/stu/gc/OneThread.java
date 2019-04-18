package com.ivan.tpp.stu.gc;

import java.util.List;

import com.google.common.collect.Lists;

public class OneThread {
	
	public static void main(String[] args) {
		while(true){
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addData(1, 10);
		}
	}
	
	private static void addData(int size,int times){
		String str = null;
		for(int k = 0; k < times; k++){
			for(int i = 0; i < size; i++){
				str = "1";
			}
		}
		
	}
}
