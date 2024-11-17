package leetcode.algorithm.dynamicProgramming;

import java.util.Arrays;

public class Code313_super_ugly_number {

    public int nthSuperUglyNumber(int n, int[] primes) {
        int p = primes.length;
        int[] pointer = new int[p];
        Arrays.fill(pointer, 0);
        int[] dp = new int[n];
        dp[0] = 1;
        int[] curentValus = new int[p];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < p; j++) {
                curentValus[j] = dp[pointer[j]] * primes[j];
                if (curentValus[j] < 0) {
                    continue;
                } else {
                    // 超出int范围的情况
                    min = Math.min(min, curentValus[j]);
                }
            }
            dp[i] = min;
            for (int j = 0; j < p; j++) {
                if (curentValus[j] == min) {
                    pointer[j]++;
                }
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        Code313_super_ugly_number c = new Code313_super_ugly_number();
        int n = 12;
        int[] primes = {2, 7, 13, 19};
        System.out.println(c.nthSuperUglyNumber(n, primes));
    }
}