package leetcode.algorithm.math;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code264_ugly_number_ii {

    public int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    public static void main(String[] args) {
        Code264_ugly_number_ii c = new Code264_ugly_number_ii();
        int n = 10;
        System.out.println(c.nthUglyNumber(n));
    }
}
