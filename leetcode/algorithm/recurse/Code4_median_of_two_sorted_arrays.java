package leetcode.algorithm.recurse;

/**
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

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = 0, right = 0;
        int aStart = 0, bStart = 0;

        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart <= m - 1 && (bStart > n - 1 || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }

        if ((len & 1) == 1) {
            return right;
        } else {
            return (left + right) * 0.5;
        }
    }

    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1)/2;
        int right = (m + n + 2)/2;
        return (getKth(nums1, 0, m-1, nums2, 0, n-1, left) + getKth(nums1, 0, m-1, nums2, 0, n-1, right))*0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        // 保证len1的长度小于len2
        if (len1 > len2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }

        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] < nums2[j]) {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        } else {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
    }

    public static void main(String[] args) {
        Code4_median_of_two_sorted_arrays c = new Code4_median_of_two_sorted_arrays();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double res = c.findMedianSortedArrays3(nums1, nums2);
        System.out.println(res);
    }
}
