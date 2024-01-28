package leetcode.algorithm.slidingWindow;

import java.util.TreeSet;

public class Code220_contains_duplicate_iii {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int indexDiff = 3;
        int valueDiff = 0;
        Code220_contains_duplicate_iii c = new Code220_contains_duplicate_iii();
        boolean r = c.containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
        System.out.println(r);
    }
}
