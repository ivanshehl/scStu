package com.ivan.tpp.stu.rt;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

public class HyRxjava {
	
	public static String TAG = "tag";
	
	public static void main(String[] args) {
		doOnTerminate();
//		doOnTerminate4();
	}
	
	private static void testOn(){
		Observable.just(2,3)
        .doOnTerminate(new Action0() {
            @Override
            public void call() {
                System.out.println("JG"+"--doOnTerminate--");
            }
        }).doOnCompleted(new Action0(){
			@Override
			public void call() {
				// TODO Auto-generated method stub
				System.out.println("JG"+"--doOnCompleted--");
			}
        	
        })
        .subscribe(integer -> System.out.println("JG"+integer.toString()));
	}
	
	private static void unding(){
		Observable<Integer> observable = Observable.just(1,2,3,4,5,6);
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
 
            @Override
            public void onNext(Integer v) {
                Log.e(TAG,"onNext................."+v);
            }
 
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted.................");
            }
 
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError.....................");
            }
        };
 
        observable
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.e(TAG, "观察者取消订阅了它生成的Observable.....................");
                    }
                })
                .subscribe(subscriber);
	}
	
	private static void nomol(){
		Observable<Integer> observable = Observable.just(1,2,3,4,5,6);
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
 
            @Override
            public void onNext(Integer v) {
                Log.e(TAG,"onNext................."+v);
            }
 
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted.................");
            }
 
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError.....................");
            }
        };
 
        observable
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        Log.e(TAG, "Observable正常终止了.....................");
 
                    }
                })
                .subscribe(subscriber);
	}
	
	private static void doOnTerminate(){
		Observable<Integer> observable =  Observable.create(new Observable.OnSubscribe<Integer>() {
			 
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if(i == 10){
                        subscriber.onError(new Throwable("EROOR"));
                    }else {
                        subscriber.onNext(i);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
 
                    }
                }
                subscriber.onCompleted();
            }
        });
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
 
            @Override
            public void onNext(Integer v) {
                Log.e(TAG,"onNext................."+v);
            }
 
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted.................");
            }
 
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError.....................");
            }
        };
 
        observable.doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "doOnUnsubscribe观察者取消订阅了它生成的Observable.....................");
            }
        }).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				// TODO Auto-generated method stub
				Log.e(TAG, "doOnNext=================");
			}
		})
        .doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "doOnCompleted正常终止了.....................");

            }
        })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Log.e(TAG, "doOnTerminate.....................");
                    }
                })
                .subscribe(subscriber);
	}
	
	private static void doOnTerminate3(){
		Observable<Integer> observable =  Observable.create(new Observable.OnSubscribe<Integer>() {
			 
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if(i == 2){
                        subscriber.onError(new Throwable("EROOR"));
                    }else {
                        subscriber.onNext(i);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
 
                    }
                }
                subscriber.onCompleted();
            }
        });
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
 
            @Override
            public void onNext(Integer v) {
                Log.e(TAG,"onNext................."+v);
            }
 
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted.................");
            }
 
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError.....................");
            }
        };
 
        observable.doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "doOnUnsubscribe观察者取消订阅了它生成的Observable.....................");
            }
        }).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				// TODO Auto-generated method stub
				Log.e(TAG, "doOnNext=============");
			}
		})
        .doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "doOnCompleted=============");

            }
        }).onErrorReturn(new Func1<Throwable, Integer>() {

			@Override
			public Integer call(Throwable t) {
				// TODO Auto-generated method stub
				return 9;
			}
		})
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                    	Log.e(TAG, "doOnTerminate=============");
                    }
                })
                .doOnError(new Action1<Throwable>() {

					@Override
					public void call(Throwable t) {
						// TODO Auto-generated method stub
						Log.e(TAG, "doOnError=============");
					}
				})
                .subscribe(subscriber);
	}
	
	
	private static void doOnTerminate2(){
		Observable<Integer> observable =  Observable.defer(new Func0<Observable<Integer>>(){

			@Override
			public Observable<Integer> call() {
				// TODO Auto-generated method stub
				return null;
			}
			
		}).create(new Observable.OnSubscribe<Integer>() {
			 
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if(i == 10){
                        subscriber.onError(new Throwable("EROOR"));
                    }else {
                        subscriber.onNext(i);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
 
                    }
                }
                subscriber.onCompleted();
            }
        });
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
 
            @Override
            public void onNext(Integer v) {
                Log.e(TAG,"onNext................."+v);
            }
 
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted.................");
            }
 
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError.....................");
            }
        };
 
        observable.doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "观察者取消订阅了它生成的Observable.....................");
            }
        })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Log.e(TAG, "订阅即将被终止.....................");
                    }
                })
                .subscribe(subscriber);
	}
	
	private static void doOnTerminate4(){
		Observable<Integer> observable =  Observable.create(new Observable.OnSubscribe<Integer>() {
			 
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if(i == 2){
                        subscriber.onError(new Throwable("EROOR"));
                    }else {
                        subscriber.onNext(i);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
 
                    }
                }
                subscriber.onCompleted();
            }
        });
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
 
            @Override
            public void onNext(Integer v) {
                Log.e(TAG,"onNext................."+v);
            }
 
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted.................");
            }
 
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError.....................");
            }
        };
 
        observable.doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "doOnUnsubscribe观察者取消订阅了它生成的Observable.....................");
            }
        }).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				// TODO Auto-generated method stub
				Log.e(TAG, "doOnNext=============");
			}
		})
        .doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "doOnCompleted=============");

            }
        }).onErrorResumeNext(Observable.just(99, 999))
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                    	Log.e(TAG, "doOnTerminate=============");
                    }
                })
                .doOnError(new Action1<Throwable>() {

					@Override
					public void call(Throwable t) {
						// TODO Auto-generated method stub
						Log.e(TAG, "doOnError=============");
					}
				})
                .subscribe(subscriber);
	}
}

class Log{
	public static void d(String tag ,String mes){
		System.out.println(tag+"\t"+mes);
	}
	
	public static void e(String tag ,String mes){
		System.out.println(tag+"\t"+mes);
	}
}
