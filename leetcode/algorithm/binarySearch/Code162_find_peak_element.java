package leetcode.algorithm.binarySearch;

public class Code162_find_peak_element {

    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public int compare(int[] nums, int idx1, int idx2) {
        int[] num1 = get(nums, idx1);
        int[] num2 = get(nums, idx2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }

    private int[] get(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, Integer.MIN_VALUE};
        }
        return new int[]{1, nums[idx]};
    }

    public static void main(String[] args) {
        Code162_find_peak_element c = new Code162_find_peak_element();
        int[] nums = {1, 2, 3, 1};
        System.out.println(c.findPeakElement(nums));
    }
}
