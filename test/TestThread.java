package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//如何顺序执行T1,T2,T3三个线程
@SuppressWarnings("unused")
public class TestThread {
	class T1 extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// T1线程中要处理的东西
			System.out.println("T1线程执行");
			new T2().start();
		}
	}

	class T2 extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// T2线程中要处理的东西
			System.out.println("T2线程执行");
			new T3().start();
		}
	}

	class T3 extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// T3线程中要处理的东西
			System.out.println("T3线程执行");
		}
	}

	// 使用SingleThreadExecutor
	private static void sequenceExecUseSingleThreadExecutor() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " run 1");
			}
		}, "T1");
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " run 2");
			}
		}, "T2");
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " run 3");
			}
		}, "T3");

		// 三个线程顺序执行 第一种方案，单个线程池 顺序放入执行队列中
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.submit(t1);
		executor.submit(t2);
		executor.submit(t3);
		executor.shutdown();
	}

	class T1_JOIN extends Thread {
		public T1_JOIN(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// T3线程中要处理的东西
			System.out.println("T1线程执行");
			for (int i = 0; i < 10; i++) {
				System.out.println(this.getName() + ":" + i);
			}
		}
	}

	class T2_JOIN extends Thread {
		public T2_JOIN(String name) {
			super(name);
		}

		@Override
		public void run() {
			// T3线程中要处理的东西
			System.out.println("T2线程执行");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(this.getName() + ":" + i);
			}
		}
	}

	class T3_JOIN extends Thread {
		public T3_JOIN(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// T3线程中要处理的东西
			System.out.println("T3线程执行");
			for (int i = 0; i < 10; i++) {
				System.out.println(this.getName() + ":" + i);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TestThread t = new TestThread();
		// 顺序在线程中创建实例
		// t.new T1().start();
		// 使用SingleThreadExecutor, 等前面一个线程执行完毕后才会执行后一个线程
		// sequenceExecUseSingleThreadExecutor();
		// 使用Thread的join方法
		T1_JOIN t1 = t.new T1_JOIN("T1");
		t1.start();
		t1.join();
		T2_JOIN t2 = t.new T2_JOIN("T2");
		t2.start();
		t2.join();
		T3_JOIN t3 = t.new T3_JOIN("T3");
		t3.start();
		t3.join();
	}
}
