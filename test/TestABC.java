package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
	public static String threadName = "A";
	public static Lock lock = new ReentrantLock();
	public static Condition conA = lock.newCondition();
	public static Condition conB = lock.newCondition();
	public static Condition conC = lock.newCondition();
}

class ThreadA implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			Resource.lock.lock();
			try {
				while (!Resource.threadName.equals("A")) {
					try {
						Resource.conA.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("A");
				Resource.threadName = "B";
				Resource.conB.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Resource.lock.unlock();
			}
		}
	}
}

class ThreadB implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			Resource.lock.lock();
			try {
				while (!Resource.threadName.equals("B")) {
					try {
						Resource.conB.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("B");
				Resource.threadName = "C";
				Resource.conC.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Resource.lock.unlock();
			}
		}
	}
}

class ThreadC implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			Resource.lock.lock();
			try {
				while (!Resource.threadName.equals("C")) {
					try {
						Resource.conC.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("C");
				Resource.threadName = "A";
				Resource.conA.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Resource.lock.unlock();
			}
		}
	}
}

public class TestABC {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(3);
		es.execute(new ThreadA());
		es.execute(new ThreadB());
		es.execute(new ThreadC());
		es.shutdown();
	}
}
