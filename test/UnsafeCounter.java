package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UnsafeCounter {

	static UnsafeCounter c = new UnsafeCounter();
	private static int count;

	public  synchronized void counter() {
		// public void counter() {
		count++;
	}

	public  synchronized int calc() {
		return count++;
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
//		pool.execute(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			}
//		});
//		pool.execute(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				for (int i = 0; i < 10000; i++) {
//					calc();
//				}
//			}
//		});
		Thread thread1 = new Thread(UnsafeCounter::count1, "线程1");
        thread1.start();
		Thread thread2 = new Thread(UnsafeCounter::count2, "线程2");
        thread2.start();
        thread1.join();
        thread2.join();
		System.out.println("count is " + count);
	}

	public static void count1() {
		for (int i = 0; i < 10000; i++) {
			c.counter();
		}
	}
	public static void count2() {
		for (int i = 0; i < 10000; i++) {
			c.calc();
		}
	}
}