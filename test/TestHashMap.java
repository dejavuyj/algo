package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ConflictObject {
	
}

class ConflictObject1 extends ConflictObject {
	@Override
	public int hashCode() {
		return 1;
	}
}

class ConflictObject2 extends ConflictObject {
	@Override
	public int hashCode() {
		return 2;
	}
}

@SuppressWarnings("unused")
public class TestHashMap {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Comparator comp = new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
			Entry<String, Integer> e1 = (Entry<String, Integer>)o1;
			Entry<String, Integer> e2 = (Entry<String, Integer>)o2;
			return e1.getValue() - e2.getValue();
//			return 0;
		}
	};

	@SuppressWarnings("unchecked")
	private static void testCompare() {
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("13", 3);
		m.put("12", 2);
		m.put("31", 1);
		m.put("14", 4);
		m.put("17", 7);
		m.put("6", 6);
		m.put("5", 5);
		Set<Entry<String, Integer>> set = m.entrySet();
		Object[] a = set.toArray();
		Arrays.sort(a, comp);
		for(Object o:a) {
			Entry<String, Integer> e = (Entry<String, Integer>)(o);
			System.out.println(e.getKey()+", "+e.getValue());
		}
	}

	private static void testTreeify() {
		// 只有当hash tab的size大于等于64后,才会进行树化
		Map<ConflictObject, Integer> m = new HashMap<ConflictObject, Integer>();
		for (Integer i = 0; i < 12; i++) {
			if (i == 8) {
				System.out.println(i);
			}
			ConflictObject1 c1 = new ConflictObject1();
			m.put(c1, i);
		}
	}

	private static void testResize() {
		Map<ConflictObject, Integer> m = new HashMap<ConflictObject, Integer>();
		for (Integer i = 0; i < 3336; i++) {
			if (i == 5) {
				System.out.println(i);
			}
			ConflictObject1 c1 = new ConflictObject1();
			m.put(c1, i);
		}
//		for (Integer i = 0; i <= 6; i++) {
//			if (i == 6) {
//				System.out.println(i);
//			}
//			ConflictObject2 c2 = new ConflictObject2();
//			m.put(c2, i);
//		}
	}

	@SuppressWarnings("rawtypes")
	private static void testLinkedHashMap() {
		// 10是初始大小，0.75是装载因子，true是表示按照访问时间排序
		HashMap<Integer, Integer> m = new LinkedHashMap<>(10, 0.75f, true);
		m.put(3, 11);
		m.put(1, 12);
		m.put(5, 23);
		m.put(2, 22);

		m.put(3, 26);
		m.get(5);

		for (Map.Entry e : m.entrySet()) {
		  System.out.println(e.getKey());
		}
	}

	public static void main(String[] args) {
		// testCompare();
		// testTreeify();
		// testResize();
		testLinkedHashMap();
	}
}