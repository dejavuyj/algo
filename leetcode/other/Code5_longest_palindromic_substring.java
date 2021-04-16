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
        if (s.length() == 2 && s.charAt(0) == s.charAt(1)) {
            return s;
        }

        String ret = "";
        int maxLen = 0;
        for (int i = 1; i < s.length() - 1; i++) {
            int left = i;
            if (s.charAt(i-1) == s.charAt(i)) {
                int currLen = 2;
                if (currLen > maxLen) {
                    maxLen = currLen;
                    ret = s.substring(i-1, i+1);
                }
                left--;
            }
            int right = i + 1;
            if (s.charAt(right) == s.charAt(i)) {
                int currLen = right - left + 1;
                if (currLen > maxLen) {
                    maxLen = currLen;
                    ret = s.substring(left, right+1);
                }
                right++;
            }
            left--;
            while (left >= 0
                    && right <= s.length() - 1) {
                if (s.charAt(left) == s.charAt(right)) {
                    int currLen = right - left + 1;
                    if (currLen > maxLen) {
                        maxLen = currLen;
                        ret = s.substring(left, right+1);
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

    public static void main(String[] args) {
        String str = "ccc";
        Code5_longest_palindromic_substring c = new Code5_longest_palindromic_substring();
        str = c.longestPalindrome2(str);
        System.out.print(str);
    }
}
