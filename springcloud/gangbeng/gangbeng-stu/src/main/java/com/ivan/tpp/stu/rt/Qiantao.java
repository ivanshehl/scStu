package com.ivan.tpp.stu.rt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

public class Qiantao {

	public static String TAG = "tag";
	
	public static void main(String[] args) {
		Future<String> delegate = test().toBlocking().toFuture();
		final Future<String> f = new Future<String>() {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
				return mayInterruptIfRunning;}

            @Override
            public boolean isCancelled() {
                return delegate.isCancelled();
			}

            @Override
            public boolean isDone() {
                return delegate.isDone();
			}

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return delegate.get();
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return delegate.get(timeout, unit);
            }
        	
        };

        /* special handling of error states that throw immediately */
        if (f.isDone()) {
        	try {
				System.out.println(f.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	private static Observable<String> test() {
		Observable<String> observable = Observable
				.defer(new Func0<Observable<String>>() {

					@Override
					public Observable<String> call() {
						// TODO Auto-generated method stub
						return qian().doOnUnsubscribe(new Action0() {
							@Override
							public void call() {
								Logs.e(TAG,
										"观察者取消订阅了它生成的Observable.....................");
								try {
									Thread.currentThread().sleep(10000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println("doOnUnsubscribe =====================");
							}
						}).doOnCompleted(new Action0() {
							@Override
							public void call() {
								Logs.e(TAG,
										"doOnCompleted正常终止了.....................");

							}
						}).doOnTerminate(new Action0() {
							@Override
							public void call() {
								Logs.e(TAG, "doOnTerminate订阅即将被终止.....................");
							}
						});
					}
				});
		return observable;
	}

	private static Observable<String> qian() {
		return Observable.defer(new Func0<Observable<String>>() {

			@Override
			public Observable<String> call() {
				String[] arrays = new String[1];
				for (int i = 0; i < 1; i++) {
					arrays[i] = i + "";
				}
				// TODO Auto-generated method stub
				return Observable.from(arrays).doOnNext(new Action1<String>() {

					@Override
					public void call(String t) {
						// TODO Auto-generated method stub
						System.out.println("next:\t" + t);
					}
				});
			}
		});
	}
	
}

class Logs{
	public static void d(String tag ,String mes){
		System.out.println(tag+"\t"+mes);
	}
	
	public static void e(String tag ,String mes){
		System.out.println(tag+"\t"+mes);
	}
}
