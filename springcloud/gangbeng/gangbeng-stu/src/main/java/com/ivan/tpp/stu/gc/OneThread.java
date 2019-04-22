package com.ivan.tpp.stu.gc;


public class OneThread {
	
	public static void main(String[] args) {
		while(true){
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addData(10, 100);
		}
	}
	
	private static void addData(int size,int times){
		byte [] bytes = null;
		for(int k = 0; k < times; k++){
			for(int i = 0; i < size; i++){
				bytes = new byte [512];
			}
		}
		
	}
}
