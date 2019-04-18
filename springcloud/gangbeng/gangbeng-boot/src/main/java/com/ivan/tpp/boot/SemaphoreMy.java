package com.ivan.tpp.boot;

import java.util.concurrent.Semaphore;

public class SemaphoreMy {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
