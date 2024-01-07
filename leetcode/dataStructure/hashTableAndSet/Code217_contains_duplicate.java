package leetcode.dataStructure.hashTableAndSet;

import java.util.HashSet;
import java.util.Set;

public class Code217_contains_duplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }

    public static void main(String[] args) {
        Code217_contains_duplicate c = new Code217_contains_duplicate();
        int[] nums = {1, 2, 3, 1};
        System.out.println(c.containsDuplicate(nums));
    }
}
