package com.ivan.tpp.boot;

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
	/**
	 * 主线程执行
	 */
	private static void demo1(){
		Observable.just(1,2,3).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				printLog("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				printLog("onError");
			}

			@Override
			public void onNext(Integer t) {
				// TODO Auto-generated method stub
				printLog(t+"");
			}
		});
	}
	
	private static void demo2(){
		Observable.just(1,2,3).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				printLog("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				printLog("onError");
			}

			@Override
			public void onNext(Integer t) {
				// TODO Auto-generated method stub
				printLog(t+"111");
				rlt += t;
			}
			
			@Override
			public void onStart() {
				printLog("start");
		    }
		});
	}
	
	private static void demo3(){
		Observable.just(1,2,3).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				printLog("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				printLog("onError");
			}

			@Override
			public void onNext(Integer t) {
				// TODO Auto-generated method stub
				printLog(t+"");
			}
		});
	}
	
	private static void printLog(String str){
		System.out.println(Thread.currentThread().getName()+"\t"+str);
	}
	
	private static List<Integer> getList(int size){
		List<Integer> list = Lists.newArrayList();
		for(int i = 0; i < size; i++){
			list.add(i);
		}
		return list;
	}
}
