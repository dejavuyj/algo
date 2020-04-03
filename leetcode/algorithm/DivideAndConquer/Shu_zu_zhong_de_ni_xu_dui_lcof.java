package leetcode.algorithm.DivideAndConquer;

public class Shu_zu_zhong_de_ni_xu_dui_lcof {

	int count = 0;

	public int reversePairs(int[] nums) {
		reversePairs(nums, 0, nums.length - 1);
		return count;
	}

	private void reversePairs(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}

		int mid = start + ((end - start) >> 1);
		reversePairs(nums, start, mid);
		reversePairs(nums, mid + 1, end);

		merage(nums, start, mid, end);
	}

	private void merage(int[] nums, int start, int mid, int end) {
		int[] tmp = new int[end - start + 1];
		int p = start;
		int q = mid + 1;
		int r = 0;
		while (p <= mid && q <= end) {
			if (nums[p] > nums[q]) {
				tmp[r++] = nums[q++];
				count += mid - p + 1;
			} else {
				tmp[r++] = nums[p++];
			}
		}
		while (p <= mid) {
			tmp[r++] = nums[p++];
		}
		while (q <= end) {
			tmp[r++] = nums[q++];
		}
		// for (int i = 0; i <= end - start; i++) {
		// nums[start + i] = tmp[i];
		// }
		System.arraycopy(tmp, 0, nums, start, end - start + 1);
	}

	public static void main(String[] args) {
		Shu_zu_zhong_de_ni_xu_dui_lcof c = new Shu_zu_zhong_de_ni_xu_dui_lcof();
		// int[] a = { 7, 5, 6, 4 };
		int[] a = { 4, 5, 6, 7 };
		int ans = c.reversePairs(a);
		System.out.println(ans);
	}
}