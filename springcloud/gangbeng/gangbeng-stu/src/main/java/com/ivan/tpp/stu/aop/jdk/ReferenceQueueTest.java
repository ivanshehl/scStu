package com.ivan.tpp.stu.aop.jdk;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

 

public class ReferenceQueueTest {
	public static boolean isRun = true;

	 

    @SuppressWarnings("static-access")

    public static void main(String[] args) throws Exception {

        String abc = new String("abc");

        System.out.println(abc.getClass() + "@" + abc.hashCode());

        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();

        new Thread() {

            public void run() {

                while (isRun) {
                	try {
						Thread.currentThread().sleep(10000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    Object obj = referenceQueue.poll();

                    if (obj != null) {

                        try {

                            Field rereferent = Reference.class

                                    .getDeclaredField("referent");

                            rereferent.setAccessible(true);

                            Object result = rereferent.get(obj);

                            System.out.println("gc will collect："

                                    + result.getClass() + "@"

                                    + result.hashCode() + "\t"

                                    + (String) result);

                        } catch (Exception e) {

                            e.printStackTrace();

                        }

                    }

                }

            }

        }.start();

        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc,

                referenceQueue);

        abc = null;

        Thread.currentThread().sleep(3000);

        System.gc();
        
        System.out.println("end gc");

        Thread.currentThread().sleep(3000);

        isRun = false;

    }
}