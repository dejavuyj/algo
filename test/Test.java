package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// final
class FinalTestClass {

	final Integer fi = new Integer(2);

	// final
	protected void test1() {
	}

	void test2() {
	}
}

abstract class AbstractClass {

}

class YieldTest extends Thread {
	public YieldTest(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			if (this.getName().equals("����")) {
				this.yield();
			}
			System.out.println("" + this.getName() + "-----" + i);
			// ��iΪ30ʱ�����߳̾ͻ��CPUʱ���õ��������������Լ����߳�ִ�У�Ҳ����˭������˭ִ�У�
			// if (i == 30) {
			// this.yield();
			// }
		}
	}

	public static void main(String[] args) {
		// hash();
		YieldTest yt1 = new YieldTest("����");
		YieldTest yt2 = new YieldTest("����");
		yt1.start();
		yt2.start();
	}
}

@SuppressWarnings("unused")
public class Test {

	private static void hash() {
		// return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		// int kh1 = 1 << 17;;
		// int h1 = kh1 >>> 16;
		// h1 = kh1 ^ h1;
		for (Integer i = 0; i < 128; i++) {
			Integer ii = 5 + i * (1 << 4);
			Integer h = ii >>> 16;
			h = ii ^ h;
			h = h & 15;
			Integer h2 = ii & 31;
			m.put(ii, ii);
		}
		m.put(12, 12);
	}

	private static void simulation() {
		double total = 0;
		int anual = 30; // ÿ���
		double rate = 1.1; // ÿ��������
		int startYear = 2020; // ��ʼ

		System.out.println("ÿ���" + anual + ", ��������" + rate);
		int i = 10;
		for (int j = 0; j < 10; j++, i--) {
			int currYear = startYear + j;
			double currYearPow = anual * Math.pow(rate, i);
			total += currYearPow;
			System.out.println(currYear + "���" + anual + ",�ۻ�" + i + "���,�����" + currYearPow + ",  �ܽ����" + total);
		}
	}

	public static void testHoldsLock() throws Exception {
		Object o = new Object();
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o) {
					System.out.println("child thread: holdLock: " + Thread.holdsLock(o));
				}
			}
		}).start();
		System.out.println("main thread: holdLock: " + Thread.holdsLock(o));
		// o.wait();
		Thread.sleep(2000);
	}

	private static void testTypeErasure() {
		List<String> ls = new ArrayList<String>();
		List<Integer> li = new ArrayList<Integer>();
		System.out.println(ls.equals(li));
	}

	public static void main(String[] args) throws Exception {
		// hash();
		// Map m = new ConcurrentHashMap<String, String>(10);
		// m.put("", "");
		// simulation();
		// testHoldsLock();
		testTypeErasure();
	}
}