package leetcode.algorithm.dynamicProgramming;

public class Code87_scramble_string {

    public boolean isScramble(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int n = s1.length();
        int m = s2.length();
        if (n != m) {
            return false;
        }

        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = chs1[i] == chs2[j];
            }
        }

        // 区间长度 2~n
        for (int len = 2; len <= n; len++) {
            // s1中的起点位置
            for (int i = 0; i <= n - len; i++) {
                // s2中的起点位置
                for (int j = 0; j <= n - len; j++) {
                    // 划分位置
                    for (int k = 1; k <= len - 1; k++) {
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }

    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";
        Code87_scramble_string c = new Code87_scramble_string();
        boolean ret = c.isScramble(s1, s2);
        System.out.print(ret);
    }
}
