package leetcode.algorithm.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Code435_non_overlapping_intervals {

	static int[][] mIntervals = null;

	@SuppressWarnings({ "rawtypes" })
	static Comparator comp = new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
			return mIntervals[(Integer) o1][0] - mIntervals[(Integer) o2][0];
		}
	};

	@SuppressWarnings("unchecked")
	public int eraseOverlapIntervals(int[][] intervals) {
		mIntervals = intervals;
		int count = 0;
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		List<Integer> l = new ArrayList<>(intervals.length);
		for (int i = 0; i < intervals.length; i++) {
			l.add(i);
		}

		Collections.sort(l, comp);
		int preIndex = l.get(0);
		for (int i = 1; i <= l.size() - 1; i++) {
			int preEnd = intervals[preIndex][1];
			int curBegin = intervals[l.get(i)][0];
			int curEnd = intervals[l.get(i)][1];

			if (curBegin < preEnd) {
				if (curEnd < preEnd) {
					preIndex = l.get(i);
				}
				count++;
			} else {
				preIndex = l.get(i);
			}
		}

		return count;
	}

	public static void main(String[] args) {
		Code435_non_overlapping_intervals c = new Code435_non_overlapping_intervals();
		// int[][] intervals = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
		// int[][] intervals = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
		// int[][] intervals = {};
		// int[][] intervals = { { 1, 100 }, { 11, 22 }, { 1, 11 }, { 2, 12 } };
		int[][] intervals = { { 0, 1 }, { 3, 4 }, { 1, 2 } };
		// tbd
		// [[0,2],[1,3],[2,4],[3,5],[4,6]]
		System.out.println(c.eraseOverlapIntervals(intervals));
	}
}
