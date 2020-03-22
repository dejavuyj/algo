package leetcode.dataStructure.hashTableAndSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Code49_group_anagrams {

	public static boolean isAnagram(String s, String t) {
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}

		int[] dict = new int[26];
		for (int i = 0; i < s.length(); i++) {
			dict[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			dict[t.charAt(i) - 'a']--;
			if (dict[t.charAt(i) - 'a'] < 0) {
				return false;
			}
		}
		return true;
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> ans = new ArrayList<List<String>>();
		if (strs == null || strs.length == 0) {
			return ans;
		}

		for (String si : strs) {
			boolean hasFound = false;
			Iterator<List<String>> i1 = ans.iterator();
			while(i1.hasNext()) {
				List<String> l = i1.next();;
				Iterator<String> i2 = l.iterator();
				while(i2.hasNext()) {
					if (isAnagram(si, i2.next())) {
						l.add(si);
						hasFound = true;
						break;
					}
				}
				if(hasFound) {
					break;
				}
			}
			if(!hasFound) {
				List<String> l = new ArrayList<String>();
				l.add(si);
				ans.add(l);
			}
		}

		return ans;
	}

	private static String getFeature(String str) {
		char[] charArray = str.toCharArray();
		Arrays.sort(charArray);
		String feature = new String(charArray);
		return feature;
	}
	
	public static List<List<String>> groupAnagrams2(String[] strs) {
		List<List<String>> ans = new ArrayList<List<String>>();
		if (strs == null || strs.length == 0) {
			return ans;
		}
		Map<String, List<String>> m = new HashMap<>();

		for (String s : strs) {
			String sf = getFeature(s);
			List<String> l = m.get(sf);
			if(l == null) {
				l = new ArrayList<String>();
				l.add(s);
				m.put(sf, l);
				ans.add(l);
			} else {
				l.add(s);
			}
		}		

		return ans;
	}

	public static void main(String[] args) {
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> ans = groupAnagrams2(strs);
		for (List<String> l : ans) {
			for (String s : l) {
				System.out.print(s + ',');
			}
			System.out.println();
		}
	}
}
