package leetcode.algorithm.math;

import java.util.Arrays;

public class Code204_count_primes {

    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code204_count_primes c = new Code204_count_primes();
        int n = 10;
        System.out.println(c.countPrimes(n));
    }
}
