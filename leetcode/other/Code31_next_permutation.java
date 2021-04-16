package leetcode.other;

import java.util.Arrays;

public class Code31_next_permutation {

    public void nextPermutation(int[] nums) {
        int maxStart = -1;
        int end = 0;
        for (int currEnd = nums.length - 1; currEnd > 0; currEnd--) {
            int currStart = getStart(nums, currEnd);
            if (currStart > maxStart) {
                maxStart = currStart;
                end = currEnd;
            }
        }
        if (maxStart > -1) {
            swap(nums, maxStart, end);
        }
        Arrays.sort(nums, maxStart + 1, nums.length);
    }

    private int getStart(int[] nums, int end) {
        for (int i = end - 1; i >= 0; i--) {
            if (nums[i] < nums[end]) {
                return i;
            }
        }

        return -1;
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        Code31_next_permutation c = new Code31_next_permutation();
        c.nextPermutation2(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
    }
}
