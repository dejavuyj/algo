package leetcode.algorithm.binarySearch;

import java.util.Arrays;

public class Code167_two_sum_ii_input_array_is_sorted {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right + 1};
    }

    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[left] + numbers[mid] > target) {
                right = mid - 1;
            } else if (numbers[mid] + numbers[right] < target) {
                left = mid + 1;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        Code167_two_sum_ii_input_array_is_sorted c = new Code167_two_sum_ii_input_array_is_sorted();
        int[] nums = {-10, -8, -2, 1, 2, 5, 6};
        int target = 0;
        System.out.println(Arrays.toString(c.twoSum(nums, target)));
    }
}
