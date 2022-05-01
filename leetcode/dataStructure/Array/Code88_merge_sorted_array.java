package leetcode.dataStructure.Array;

public class Code88_merge_sorted_array {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int mIndex = m - 1, nIndex = n - 1, z = m + n - 1;
        while (z >= 0) {
            if (mIndex < 0) {
                nums1[z--] = nums2[nIndex--];
            } else if (nIndex < 0) {
                break;
            } else if (nums1[mIndex] > nums2[nIndex]) {
                nums1[z--] = nums1[mIndex--];
            } else {
                nums1[z--] = nums2[nIndex--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 0, 0};
        int m = 3;
        int[] nums2 = {1, 2};
        int n = 2;

        Code88_merge_sorted_array c = new Code88_merge_sorted_array();
        c.merge(nums1, m, nums2, n);
        for (int i = 0; i < m + n; i++) {
            System.out.print(nums1[i] + ", ");
        }
    }
}
