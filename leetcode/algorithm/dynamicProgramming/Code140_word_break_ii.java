package leetcode.algorithm.dynamicProgramming;

import java.util.*;

public class Code140_word_break_ii {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int right = 1; right <= len; right++) {
            for (int left = right - 1; left >= 0; left--) {
                if (dp[left] && wordSet.contains(s.substring(left, right))) {
                    dp[right] = true;
                    break;
                }
            }
        }

        List<String> res = new ArrayList<>();
        if (dp[len]) {
            Deque<String> path = new ArrayDeque<>();
            dfs(s, len, wordSet, dp, path, res);
            return res;
        }

        return res;
    }

    private void dfs(String s, int len, Set<String> wordSet, boolean[] dp, Deque<String> path, List<String> res) {
        if (len == 0) {
            res.add(String.join(" ", path));
            return;
        }

        for (int i = len - 1; i >= 0; i--) {
            String suffix = s.substring(i, len);
            if (wordSet.contains(suffix) && dp[i]) {
                path.addFirst((suffix));
                dfs(s, i, wordSet, dp, path, res);
                path.removeFirst();
            }
        }
    }

    public static void main(String[] args) {
        Code140_word_break_ii c = new Code140_word_break_ii();
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
        List<String> ans = c.wordBreak(s, wordDict);
        ans.forEach(si -> System.out.println(si));
    }
}
