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
            if (this.getName().equals("张三")) {
                this.yield();
            }
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            // if (i == 30) {
            // this.yield();
            // }
        }
    }

    public static void main(String[] args) {
        // hash();
        YieldTest yt1 = new YieldTest("张三");
        YieldTest yt2 = new YieldTest("李四");
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
        // testHoldsLock();
//		testTypeErasure();
    }
}