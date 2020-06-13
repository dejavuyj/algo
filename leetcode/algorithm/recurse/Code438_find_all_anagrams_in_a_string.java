package leetcode.algorithm.recurse;

import java.util.ArrayList;
import java.util.List;

public class Code438_find_all_anagrams_in_a_string {

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

	public static List<Integer> findAnagrams(String s, String p) {
		List<Integer> ansList = new ArrayList<>();
		int sLen = s.length();
		int pLen = p.length();
		for (int i = 0; i <= sLen - pLen; i++) {
			String subStr = s.substring(i, i + pLen);
			if (isAnagram3(subStr, p)) {
				ansList.add(i);
			}
		}
		return ansList;
	}

    public static List<Integer> findAnagrams2(String s, String p) {
		// 转化为char array
		char[] s_arr = s.toCharArray();
		char[] p_arr = p.toCharArray();
		List<Integer> list = new ArrayList<>();

		// 定义两个hash数组
		int[] s_letter = new int[26]; // 滑动窗口hash
		int[] p_letter = new int[26]; // 目标数组hash
		// hash p数组
		for (int i = 0; i < p_arr.length; i++) {
			p_letter[p_arr[i] - 'a']++;
		}

		// 滑动窗口
		int left = 0;
		int right = 0;

		// 当窗口右边出界 则退出
		while (right < s_arr.length) {
			// 获取当前最右边的字符的hash值
			int current_char_index = s_arr[right++] - 'a';
			// 当前最优字符加入hash
			s_letter[current_char_index]++;

			// 当前字符的hash值 大于 目标数组（溢出） 1.当前字符不在目标数组中 2.当前字符有重复数组 左边界右移缩小窗口
			// 主要技巧点 可以进行手动验证
			while (p_letter[current_char_index] < s_letter[current_char_index]) {
				s_letter[s_arr[left++] - 'a']--;
			}
			// 当出现窗口大小等于目标数组的大小的时候 匹配成功+1
			if (right - left == p_arr.length) {
				list.add(left);
			}
		}
		return list;
    }

	public static void main(String[] args) {
		List<Integer> ansList = findAnagrams2("abbac", "bba");
		for (Integer i : ansList) {
			System.out.print(i + ", ");
		}
	}
}
