package leetcode.dataStructure.hashTableAndSet;

import java.util.Arrays;
import java.util.HashMap;

public class Code242_valid_anagram {

	public static boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		HashMap<Character, Integer> m1 = new HashMap<Character, Integer>();
		HashMap<Character, Integer> m2 = new HashMap<Character, Integer>();

		for (int i = 0; i < s.length(); i++) {
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);

			Integer count1 = m1.get(c1);
			if (count1 == null) {
				m1.put(c1, 1);
			} else {
				m1.put(c1, ++count1);
			}

			Integer count2 = m2.get(c2);
			if (count2 == null) {
				m2.put(c2, 1);
			} else {
				m2.put(c2, ++count2);
			}
		}

//		for (Character c : m1.keySet()) {
//			Integer i1 = m1.get(c);
//			Integer i2 = m2.get(c);
//			if (m1.get(c) == null || !i1.equals(i2)) {
//				return false;
//			}
//		}
		return m1.equals(m2);
	}

	public static boolean isAnagram2(String s, String t) {
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		char[] sa = s.toCharArray();
		Arrays.sort(sa);
		String sf = new String(sa);

		char[] ta = t.toCharArray();
		Arrays.sort(ta);
		String tf = new String(ta);

		return sf.equals(tf);
	}

	public static boolean isAnagram3(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		int[] table = new int[26];
		for (int i = 0; i < s.length(); i++) {
			table[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			table[t.charAt(i) - 'a']--;
			if (table[t.charAt(i) - 'a'] < 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String s = "anagram";
		String t = "nagaram";
		System.out.println(isAnagram3(s, t));
	}
}
