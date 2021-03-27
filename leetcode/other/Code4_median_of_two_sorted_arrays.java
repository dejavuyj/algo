package leetcode.other;

/**
 * @Author YJ
 * @create 2021/3/27 20:56
 */
public class Code4_median_of_two_sorted_arrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double res = 0;
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int[] nums = new int[len];

        if (m == 0) {
            nums = nums2;
        }
        if (n == 0) {
            nums = nums1;
        }

        int i = 0;
        int j = 0;
        int k = 0;
        while (i <= m - 1 && j <= n - 1) {
            if (nums1[i] < nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }

        if (m != 0 && n != 0) {
            while (i <= m - 1) {
                nums[k++] = nums1[i++];
            }
            while (j <= n - 1) {
                nums[k++] = nums2[j++];
            }
        }

        if (len % 2 != 0) {
            res = nums[len / 2];
        } else {
            res = (double) (nums[len / 2 - 1] + nums[len / 2]) / 2;
        }

        return res;
    }

    public static void main(String[] args) {
        Code4_median_of_two_sorted_arrays c = new Code4_median_of_two_sorted_arrays();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double res = c.findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }
}
