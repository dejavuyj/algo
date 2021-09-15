package leetcode.dataStructure.string;

public class Code44_wildcard_matching {

    private boolean matched = false;
    private char[] pattern;

    private void rmatch(int ti, int pj, char[] text) {
        if (matched) {
            return;
        }
        int tlen = text.length;
        if (pj == pattern.length) { // 正则表达式到结尾
            if (ti == tlen) { // 字符串也到结尾
                matched = true;
            }
            return;
        }
        if (pattern[pj] == '*') { // *匹配任意个字符
            for (int k = 0; k <= tlen - ti; ++k) {
                rmatch(ti + k, pj + 1, text);
            }
        } else if (pattern[pj] == '?') { // ?匹配1个字符
            rmatch(ti + 1, pj + 1, text);
        } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配
            rmatch(ti + 1, pj + 1, text);
        }
    }

    // 回溯
    public boolean isMatch(String s, String p) {
        matched = false;
        pattern = p.toCharArray();
        rmatch(0, 0, s.toCharArray());
        return matched;
    }

    // 动态规划
    public boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    // 贪心算法
    public boolean isMatch3(String s, String p) {
        int sRight = s.length(), pRight = p.length();
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                --sRight;
                --pRight;
            } else {
                return false;
            }
        }
        if (pRight == 0) {
            return sRight == 0;
        }

        int sIndex = 0, pIndex = 0;
        int sRecord = -1, pRecord = -1;

        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                ++pIndex;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                ++sIndex;
                ++pIndex;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }
        return allStars(p, pIndex, pRight);
    }

    private boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; i++) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    private boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "*";
        Code44_wildcard_matching c = new Code44_wildcard_matching();
        System.out.println(c.isMatch(s, p));
    }
}
