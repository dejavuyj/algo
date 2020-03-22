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
		// 杞寲涓篶har array
		char[] s_arr = s.toCharArray();
		char[] p_arr = p.toCharArray();
		List<Integer> list = new ArrayList<>();

		// 瀹氫箟涓や釜hash鏁扮粍
		int[] s_letter = new int[26]; // 婊戝姩绐楀彛hash
		int[] p_letter = new int[26]; // 鐩爣鏁扮粍hash
		// hash p鏁扮粍
		for (int i = 0; i < p_arr.length; i++) {
			p_letter[p_arr[i] - 'a']++;
		}

		// 婊戝姩绐楀彛
		int left = 0;
		int right = 0;

		// 褰撶獥鍙ｅ彸杈瑰嚭鐣� 鍒欓��鍑�
		while (right < s_arr.length) {
			// 鑾峰彇褰撳墠鏈�鍙宠竟鐨勫瓧绗︾殑hash鍊�
			int current_char_index = s_arr[right++] - 'a';
			// 褰撳墠鏈�浼樺瓧绗﹀姞鍏ash
			s_letter[current_char_index]++;

			// 褰撳墠瀛楃鐨刪ash鍊� 澶т簬 鐩爣鏁扮粍锛堟孩鍑猴級 1.褰撳墠瀛楃涓嶅湪鐩爣鏁扮粍涓� 2.褰撳墠瀛楃鏈夐噸澶嶆暟缁� 宸﹁竟鐣屽彸绉荤缉灏忕獥鍙�
			// 涓昏鎶�宸х偣 鍙互杩涜鎵嬪姩楠岃瘉
			while (p_letter[current_char_index] < s_letter[current_char_index]) {
				s_letter[s_arr[left++] - 'a']--;
			}
			// 褰撳嚭鐜扮獥鍙ｅぇ灏忕瓑浜庣洰鏍囨暟缁勭殑澶у皬鐨勬椂鍊� 鍖归厤鎴愬姛+1
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
