package leetcode.dataStructure.Array;

import java.util.HashSet;
import java.util.Set;

public class Code128_longest_consecutive_sequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentLen = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentLen++;
                }
                longest = Math.max(longest, currentLen);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        Code128_longest_consecutive_sequence c = new Code128_longest_consecutive_sequence();
        int ret = c.longestConsecutive(nums);
        System.out.print(ret);
    }
}
