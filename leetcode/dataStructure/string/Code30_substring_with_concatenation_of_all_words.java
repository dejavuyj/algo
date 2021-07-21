package leetcode.dataStructure.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code30_substring_with_concatenation_of_all_words {

    public List<Integer> findSubstring(String s, String[] words) {
        int wordNum = words.length;
        int wordLen = words[0].length();
        int wordsLen = wordNum * wordLen;
        List<Integer> ansList = new ArrayList<>();
        int end = s.length() - wordsLen;

        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        for (int i = 0; i <= end; i++) {
            String curStr = s.substring(i, i + wordsLen);
            Map<String, Integer> tmpMap = new HashMap<>();
            for (int j = 0; j < wordsLen; j += wordLen) {
                String curWord = curStr.substring(j, j + wordLen);
                tmpMap.put(curWord, tmpMap.getOrDefault(curWord, 0) + 1);
            }
            if (map.equals(tmpMap)) {
                ansList.add(i);
            }
        }

        return ansList;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        int wordNum = words.length;
        int wordLen = words[0].length();
        List<Integer> ansList = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> tmpMap = new HashMap<>();
            while(right + wordLen <= s.length()) {
                String w = s.substring(right, right + wordLen);
                tmpMap.put(w, tmpMap.getOrDefault(w, 0) + 1);
                right += wordLen;
                count++;
                while (tmpMap.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                    String l_w = s.substring(left, left + wordLen);
                    count--;
                    tmpMap.put(l_w, tmpMap.get(l_w) - 1);
                    left += wordLen;
                }
                if (count == wordNum) {
                    ansList.add(left);
                }
            }
        }

        return ansList;
    }

    public List<Integer> findSubstring3(String s, String[] words) {
        int wordNum = words.length;
        int wordLen = words[0].length();
        List<Integer> ansList = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> tmpMap = new HashMap<>();
            while(right + wordLen <= s.length()) {
                String w = s.substring(right, right + wordLen);
                right += wordLen;
                if (!map.containsKey(w)) {
                    count = 0;
                    left = right;
                    tmpMap.clear();
                } else {
                    tmpMap.put(w, tmpMap.getOrDefault(w, 0) + 1);
                    count++;
                    while (tmpMap.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                        String l_w = s.substring(left, left + wordLen);
                        count--;
                        tmpMap.put(l_w, tmpMap.get(l_w) - 1);
                        left += wordLen;
                    }
                }
                if (count == wordNum) {
                    ansList.add(left);
                }
            }
        }

        return ansList;
    }


    public static void main(String[] args) {
        String s = "ababaab";
        String[] words = {"ab", "ba", "ba"};
        Code30_substring_with_concatenation_of_all_words c = new Code30_substring_with_concatenation_of_all_words();
        List<Integer> ansList = c.findSubstring2(s, words);
        ansList.forEach(i -> System.out.print(i + ", "));
    }
}
