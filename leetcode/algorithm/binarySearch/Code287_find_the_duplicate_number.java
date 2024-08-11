package leetcode.algorithm.binarySearch;

public class Code287_find_the_duplicate_number {

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code287_find_the_duplicate_number c = new Code287_find_the_duplicate_number();
        int[] nums = {1, 3, 4, 2, 2};
        int ret = c.findDuplicate(nums);
        System.out.println(ret);
    }
}
