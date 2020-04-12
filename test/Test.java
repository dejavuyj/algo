package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
			if (this.getName().equals("张三")) {
				this.yield();
			}
			System.out.println("" + this.getName() + "-----" + i);
			// 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
//			if (i == 30) {
//				this.yield();
//			}
		}
	}

	public static void main(String[] args) {
		//hash();
		YieldTest yt1 = new YieldTest("张三");
		YieldTest yt2 = new YieldTest("李四");
		yt1.start();
		yt2.start();
	}
}

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
		int anual = 30; //每年存
		double rate = 1.1; //每年收益率
		int startYear = 2020; //起始
		
		System.out.println("每年存"+anual+", 年收益率"+rate);
		int i=10;
		for(int j=0;j<10;j++,i--) {
			int currYear = startYear + j;
			double currYearPow = anual*Math.pow(rate, i);
			total += currYearPow;
			System.out.println(currYear+"年的"+anual+",累积"+i+"年后,金额是"+currYearPow+",  总金额是"+total);
		}
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		//hash();
//		Map m = new ConcurrentHashMap<String, String>(10);
//		m.put("", "");
//		simulation();
		Random random = new Random();
		for(int i=0; i<100; i++) {
			int r = random.nextInt(0);
			if(r < 0) {
				System.out.println("--------negative");
			}
			System.out.println("r is "+r);
		}
		
	}
}