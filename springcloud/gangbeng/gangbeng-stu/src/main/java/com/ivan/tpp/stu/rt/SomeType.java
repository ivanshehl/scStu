package com.ivan.tpp.stu.rt;

import java.util.concurrent.Executors;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

public class SomeType {
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public Observable<String> valueObservable() {
		return Observable.just(value);
	}

	public static void main(String[] args) {
		final SomeType instance = new SomeType();
		Observable<String> value = instance.valueObservable3();
		value.toBlocking().toFuture();
		Executors.newFixedThreadPool(1).execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("sleep =================== start");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("sleep =================== end");
				// TODO Auto-generated method stub
				instance.setValue("Some Value");
			}
		});
		value.subscribe(System.out::println);
	}

	public Observable<String> valueObservable2() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {

				subscriber.onNext(value);
				subscriber.onCompleted();
			}
		});
	}
	
	public Observable<String> valueObservable3() {
		return Observable.defer(new Func0<Observable<String>>() {

			@Override
			public Observable<String> call() {
				System.out.println("chufa=================");
				return Observable.just(value);
			}
		});
	}
}
