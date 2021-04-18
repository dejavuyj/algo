package leetcode.other;

public class Code5_longest_palindromic_substring {

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        String ret = "";
        int maxLen = 0;
        for (int i = s.length() - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (i + 1 - j <= maxLen) {
                    continue;
                }
                String currStr = s.substring(j, i + 1);
                if (isPalindrome(currStr)) {
                    if (currStr.length() > maxLen) {
                        maxLen = currStr.length();
                        ret = currStr;
                        break;
                    }
                }
            }
        }
        if (maxLen == 0) {
            return s.charAt(0) + "";
        }
        return ret;
    }

    private boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public String longestPalindrome2(String s) {
        if (s.length() == 1) {
            return s;
        }

        String ret = "";
        int maxLen = 0;
        int cnt = 1;
        // 两个元素之间也可以作为中心点
        for (float i = 0.5f; i < s.length() - 1; i += 0.5, cnt++) {
            int left;
            int right;
            if ((cnt & 1) == 1) {
                left = (int) (i - 0.5f);
                right = (int) (i + 0.5f);
            } else {
                left = (int) (i - 1);
                right = (int) (i + 1);
            }
            while (left >= 0
                    && right <= s.length() - 1) {
                if (s.charAt(left) == s.charAt(right)) {
                    int currLen = right - left + 1;
                    if (currLen > maxLen) {
                        maxLen = currLen;
                        ret = s.substring(left, right + 1);
                    }
                } else {
                    break;
                }
                left--;
                right++;
            }
        }
        if (maxLen == 0) {
            return s.charAt(0) + "";
        }
        return ret;
    }

    public String longestPalindrome3(String s) {
        // Manacher算法
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int i_mirror = 2 * C - i;
            if (R > i) {
                P[i] = Math.min(R - i, P[i_mirror]);
            } else {
                P[i] = 0;
            }

            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 0; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    private String preProcess(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        String ret = "^";
        for (int i = 0; i < n; i++) {
            ret += "#" + s.charAt(i);
        }
        ret += "#$";
        return ret;
    }

    public static void main(String[] args) {
        String str = "ccc";
        Code5_longest_palindromic_substring c = new Code5_longest_palindromic_substring();
        str = c.longestPalindrome2(str);
        System.out.print(str);
    }
}
