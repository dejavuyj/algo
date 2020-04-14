package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

	static int[][] mIntervals = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Comparator comp = new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
			Integer i1 = (Integer) o1;
			Integer i2 = (Integer) o2;
			if (mIntervals[i1][0] <= mIntervals[i2][0]
					&& mIntervals[i1][1] <= mIntervals[i2][1]) {
				return -1;
			} else {
				return 1;
			}
		}
	};

	int lis = 0;

	public void getLis_bk(int[] nums, int ci, int clis, int cLast) {
		if (ci == nums.length) {
			if (clis > lis) {
				lis = clis;
			}
			return;
		}
		getLis_bk(nums, ci + 1, clis, cLast);
		if (nums[ci] > cLast) {
			getLis_bk(nums, ci + 1, clis + 1, nums[ci]);
		}
	}

	public int getLis_bk(int[] nums) {
		getLis_bk(nums, 0, 0, 0);
		return lis;
	}

	public static void main(String[] args) {
		Solution c = new Solution();
		int[] a = { 2, 9, 3, 6, 5, 1, 7 };
		System.out.println(c.getLis_bk(a));
	}
}
