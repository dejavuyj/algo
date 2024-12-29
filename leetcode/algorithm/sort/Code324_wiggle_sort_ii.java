package leetcode.algorithm.sort;

import java.util.Arrays;

public class Code324_wiggle_sort_ii {

    public void wiggleSort(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int x = (n + 1) / 2;
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        Code324_wiggle_sort_ii c = new Code324_wiggle_sort_ii();
        c.wiggleSort(nums);
        Arrays.stream(nums).forEach(n -> System.out.print(n + ", "));
    }
}