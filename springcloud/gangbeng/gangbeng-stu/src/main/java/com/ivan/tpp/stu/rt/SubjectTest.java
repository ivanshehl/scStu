package com.ivan.tpp.stu.rt;

import rx.Observer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class SubjectTest {

	public static void main(String[] args) {
		llist();
	}
	
	private static void publish(){
//		BucketedCounterStream
//		AbstractCommand
		PublishSubject<String> publishSubject = PublishSubject.create();
		publishSubject.onNext("publishSubject1");
		publishSubject.onNext("publishSubject2");
		publishSubject.subscribe(new Observer<String>() {
		       @Override
		       public void onCompleted() {
		           
		       }

		       @Override
		       public void onError(Throwable e) {

		       }

		       @Override
		       public void onNext(String s) {
		           System.out.println("publishSubject observer1:"+s);
		       }
		   });
		publishSubject.onNext("publishSubject3");
		publishSubject.onNext("publishSubject4");
	}
	
	private static void llist(){
		ReplaySubject<String>replaySubject = ReplaySubject.create(); //创建默认初始缓存容量大小为16的ReplaySubject，当数据条目超过16会重新分配内存空间，使用这种方式，不论ReplaySubject何时被订阅，Observer都能接收到数据
		//replaySubject = ReplaySubject.create(100);//创建指定初始缓存容量大小为100的ReplaySubject
		//replaySubject = ReplaySubject.createWithSize(2);//只缓存订阅前最后发送的2条数据 
		 //replaySubject=ReplaySubject.createWithTime(1,TimeUnit.SECONDS,Schedulers.computation());  //replaySubject被订阅前的前1秒内发送的数据才能被接收     
		replaySubject.onNext("replaySubject:pre1");
		replaySubject.onNext("replaySubject:pre2");
		replaySubject.onNext("replaySubject:pre3");
		replaySubject.subscribe(new Action1<String>() {
		        @Override
		        public void call(String s) {
		                System.out.println("replaySubject:" + s);
		        }
		    });
		replaySubject.onNext("replaySubject:after1");
		replaySubject.onNext("replaySubject:after2");
	}
}
