package com.ivan.tpp.boot;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.google.common.collect.Lists;


public class RxUse {
	
	private static volatile Integer rlt;
	
	public static void main(String[] args) {
		list();
//		mainLoop();
	}
	
	private static void threadqie(){
		Observable.create(new Observable.OnSubscribe<Integer>() {

			@Override
			public void call(Subscriber<? super Integer> t) {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread()+"11111111\t");
				t.onNext(1);
				t.onNext(2);
				t.onNext(6);
			}})
	    .subscribeOn(Schedulers.newThread()) // 指定 subscribe() 发生在 IO 线程
	    .observeOn(Schedulers.io()) // 指定 Subscriber 的回调发生在主线程
	    .subscribe(new Action1<Integer>() {
	        @Override
	        public void call(Integer number) {
	            System.out.println(Thread.currentThread()+"\t"+number);
	        }
	    });
	}
	
	private static void list(){
		Observable.just(1, 2).lift(new Observable.Operator<String,Integer>() {

			@Override
			public Subscriber<? super Integer> call(Subscriber<? super String> t) {
				
				return new Subscriber<Integer>(){

					@Override
					public void onCompleted() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onError(Throwable e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onNext(Integer t) {
						// TODO Auto-generated method stub
						System.out.println("String22222\t"+t);
					}
					
				};
			}
		});
	}
	
	private static void create(){
		Observable.create(new Observable.OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> t) {
				
				t.onNext("1");
				t.onNext("2");
				t.onNext("3");
				
			}
		}).subscribe(new Action1<String>() {

			@Override
			public void call(String t) {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName()+"\t"+t);
			}
			
		});
	}
	
	public static void mainLoop(){
		rlt = 0;
		threadqie();
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
	
	private static void demoAction(){
		Observable.just(1,2,3).subscribeOn(Schedulers.io()).subscribe(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName()+"\tnum:\t"+t.intValue());
			}

			
		}, new Action1<Throwable>(){

			@Override
			public void call(Throwable t) {
				// TODO Auto-generated method stub
				System.out.println(t.toString());
			}
			
		}, new Action0(){

			@Override
			public void call() {
				// TODO Auto-generated method stub
				System.out.println("complete");
			}
			
		});
	}
	
	public static void demoObservable(){
		Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
	        @Override
	        public void call(Subscriber<? super String> subscriber) {
	            subscriber.onNext("message 1");
	            subscriber.onNext("message 2");
	            subscriber.onCompleted();
	        }
	    });
	}
	
	private static void threadChange(){
		
		Observable.create(new Observable.OnSubscribe<String>() {
		    @Override
		    public void call(Subscriber<? super String> subscriber) {
		        d("OnSubscribe.call Thread -> " + Thread.currentThread().getName());
		        subscriber.onNext("message");
//		        subscriber.onCompleted();
		    }
		}).subscribeOn(Schedulers.immediate())
		  .subscribe(new Subscriber<String>() {
		      @Override
		      public void onCompleted() {
		    	  System.out.println("==============onCompleted================");
		      }

		      @Override
		      public void onError(Throwable e) {

		      }

		      @Override
		      public void onNext(String s) {
		          d("Subscriber.onNext Thread -> " + Thread.currentThread().getName());
		      }
		  });
	}
	
	private static void d(String val){
		System.out.println(val);
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
