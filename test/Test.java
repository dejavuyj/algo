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

    private static void testFloat() {
        float a = 1;
        float b = 0.9f;
        float c = a - b;
        System.out.println(c);
    }

    private static void complementation() {
        String s = "76001";
        System.out.println(Math.abs(s.hashCode()));
        System.out.println("hashCode(" + s + ") % 64 = " + Math.abs(s.hashCode()) % 64);
        System.out.println(s + " % 64 = " + Long.parseLong(s) % 64);
    }

    private static void countCharacterNum() {
        String s = "水印内容水x印内容水印 Vus_rzjvs5l4i 2023-08-08 14:21:37";
        int cnt = 0;
        for (char c : s.toCharArray()) {
            cnt += Character.isIdeographic(c) ? 2 : 1;
        }
        System.out.println(cnt / 2);
    }

    public static void main(String[] args) throws Exception {
        // hash();
        // Map m = new ConcurrentHashMap<String, String>(10);
        // m.put("", "");
        // testHoldsLock();
//		testTypeErasure();
//        testFloat();
        countCharacterNum();
    }
}