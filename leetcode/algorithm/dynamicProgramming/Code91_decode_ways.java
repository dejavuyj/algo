package leetcode.algorithm.dynamicProgramming;

public class Code91_decode_ways {

    public int numDecodings(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            if (1 <= a && a <= 9) {
                f[i] = f[i - 1];
            }
            if (10 <=b && b <= 26) {
                f[i] += f[i-2];
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        String s = "226";
        Code91_decode_ways c = new Code91_decode_ways();
        int ans = c.numDecodings(s);
        System.out.println(ans);
    }
}
