package test;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class TestThreadLocal {

	private static void testThreadLocal() {
		ThreadLocal<Object> t = new ThreadLocal<Object>();
		int i = 99;
		t.set(i);
		System.out.println(t.get());
		t.remove();
	}

	private static final int PRINT_INTERVAL = 10000;

	private static int printCnt(int cnt) {
		cnt++;
		if (cnt % PRINT_INTERVAL == 0) {
			System.out.println(cnt);
		}
		return cnt;
	}

	private static void testNormalOOM() {
		//List<Object> l = new ArrayList<Object>();
		int cnt = 0;
		while (true) {
			Object o = new Object();
			//l.add(o);
			//l.remove(o);
			cnt = printCnt(cnt);
		}
	}

	private static void testThreadLocalOOM_NoRemove() {
		List<Object> l = new ArrayList<Object>();
		int cnt = 0;
		while (true) {
			ThreadLocal<Object> t = new ThreadLocal<Object>();
			Object o = new Object();
			t.set(o);
			//l.add(t);
			//l.remove(t);
			t = null;
			cnt = printCnt(cnt);
		}
	}

	private static void testThreadLocalOOM_Remove() {
		int cnt = 0;
		while (true) {
			ThreadLocal<Object[]> t = new ThreadLocal<Object[]>();
			Object[] l = new Object[10000];
			t.set(l);
			cnt = printCnt(cnt);
			t.remove();
		}
	}

	public static void main(String[] args) {
		// testThreadLocal();
//		testNormalOOM();
		 testThreadLocalOOM_NoRemove();
	}

}
