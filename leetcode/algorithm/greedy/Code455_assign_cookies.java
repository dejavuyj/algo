package leetcode.algorithm.greedy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Code455_assign_cookies {

	public static int findContentChildren(int[] g, int[] s) {
		int ans = 0;
		if (g == null || s == null) {
			return ans;
		}

		Arrays.sort(g);
		Arrays.sort(s);
		LinkedList<Integer> listS = new LinkedList<>();
		for (int i : s) {
			listS.add(i);
		}
		Iterator<Integer> iter = listS.iterator();
		for (int gi=0; gi<g.length; ) {
			if(iter.hasNext()) {				
				int s1 = iter.next();
				if (s1 >= g[gi]) {
					ans++;
					gi++;
				}
				iter.remove();
			} else {
				break;
			}
		}
		return ans;
	}

	public static int findContentChildren2(int[] g, int[] s) {
		if (g == null || s == null) {
			return 0;
		}

		Arrays.sort(g);
		Arrays.sort(s);

		int gi = 0;
		int si = 0;
		while (gi < g.length && si < s.length) {
			if (s[si] >= g[gi]) {
				gi++;
			}
			si++;
		}

		return gi;
	}

	public static void main(String[] args) {
//		 int[] g = { 1, 2, 3 };
//		 int[] s = { 1, 1 };
//		int[] g = { 1, 2 };
//		int[] s = { 1, 2, 3 };
		int[] g = { 10, 9, 8, 7 };
		int[] s = { 5, 6, 7, 8 };
		System.out.println(findContentChildren(g, s));
	}
}
