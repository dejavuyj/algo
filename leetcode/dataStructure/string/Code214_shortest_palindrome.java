package leetcode.dataStructure.string;

import java.util.Arrays;

public class Code214_shortest_palindrome {

    public String shortestPalindrome(String s) {
        int n = s.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; i++) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int best = -1;
        for (int i = n - 1; i >= 0; i--) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                best++;
            }
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuilder ans = new StringBuilder(add).reverse();
        ans.append(s);
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "aacecaaa";
        Code214_shortest_palindrome c = new Code214_shortest_palindrome();
        System.out.println(c.shortestPalindrome(s));
    }
}
