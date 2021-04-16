package leetcode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author YJ
 * @create 2021/3/21 11:23
 */
public class Code3_longest_substring_without_repeating_characters {

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int max = 0;
        int start = 0;
        int curLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int prevPos = map.get(c);
                start = Math.max(start, prevPos + 1);
                curLen = i - start + 1;
            } else {
                curLen++;
            }
            max = Math.max(max, curLen);
            map.put(c, i);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        int max = 0;
        int start = 0;
        int curLen = 0;
        int[] charPos = new int[256];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int curPos = i + 1;
            if (charPos[c] != 0) {
                int prevPos = charPos[c];
                start = Math.max(start, prevPos + 1);
                curLen = curPos - start + 1;
            } else {
                curLen++;
            }
            max = Math.max(max, curLen);
            charPos[c] = curPos;
        }
        return max;
    }

    public int lengthOfLongestSubstring3(String s) {
        int len = s.length();
        int max = 0;
        int start = 0;
        int[] charPos = new int[128];

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int curPos = i + 1;
            int prevPos = charPos[c];
            start = Math.max(start, prevPos + 1);
            max = Math.max(max, curPos - start + 1);
            charPos[c] = curPos;
        }
        return max;
    }

    public static void main(String[] args) {
        Code3_longest_substring_without_repeating_characters c = new Code3_longest_substring_without_repeating_characters();
        String s = "abcabcbb";
//        String s = "bbbbb";
//        String s = "pwwkew";
//        String s = "wobgrovw";
//        String s = "dvda";
//        String s = "ab";
        int ans = c.lengthOfLongestSubstring3(s);
        System.out.println(ans);
    }
}
