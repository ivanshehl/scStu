package com.ivan.tpp.stu.rt;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import com.google.common.collect.Lists;


public class RxUse {
	
	private static volatile Integer rlt;
	
	public static void main(String[] args) {
		rlt = 0;
		demo2();
		printLog("start loop");
		while(true){
			try {
				Thread.currentThread().sleep(2000);
				printLog("loop===================="+rlt);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void demo2(){
		Observable.create(new Observable.OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> t) {
				// TODO Auto-generated method stub
				t.onNext("1");
				try {
					Thread.currentThread().sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("sleep end");
			}

			
		}).toBlocking();
		System.out.println("get block");
	}
	private static void d(String val){
		System.out.println(val);
	}
	
	private static void printLog(String str){
		System.out.println(Thread.currentThread().getName()+"\t"+str);
	}
	
}
