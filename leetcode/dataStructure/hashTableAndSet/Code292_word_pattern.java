package leetcode.dataStructure.hashTableAndSet;

import java.util.HashMap;
import java.util.Map;

public class Code292_word_pattern {

	public boolean wordPattern(String pattern, String s) {
		Map<Character, String> p2s = new HashMap<>();
		Map<String, Character> s2p = new HashMap<>();
		char[] patternArray = pattern.toCharArray();
		String[] sArray = s.split(" ");
		if (patternArray.length != sArray.length) {
			return false;
		}
		for (int i=0; i< patternArray.length; i++) {
			String targetStr = p2s.get(patternArray[i]);
			if (targetStr == null) {
				p2s.put(patternArray[i], sArray[i]);
			} else if (!targetStr.equals(sArray[i])) {
				return false;
			}

			Character targetPattern = s2p.get(sArray[i]);
			if (targetPattern == null) {
				s2p.put(sArray[i], patternArray[i]);
			} else if (!targetPattern.equals(patternArray[i])) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Code292_word_pattern c = new Code292_word_pattern();
		String pattern = "abba";
		String s = "dog dog dog dog";
		System.out.println(c.wordPattern(pattern, s));
	}
}
