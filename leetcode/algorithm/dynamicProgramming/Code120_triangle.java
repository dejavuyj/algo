package leetcode.algorithm.dynamicProgramming;

import java.util.Arrays;
import java.util.List;

public class Code120_triangle {

	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null) {
			return 0;
		}
		int[] record = new int[1];
		return recurse(triangle, record, 1);
	}

	private int recurse(List<List<Integer>> triangle, int[] record, int level) {
		int min = Integer.MAX_VALUE;
		int[] newRecord = new int[level];
		
		for (int i = 0; i < level; i++) {
			if (i == 0) {
				newRecord[i] = triangle.get(level - 1).get(i) + record[i];
			} else if (i < level - 1) {
				newRecord[i] = triangle.get(level - 1).get(i) + Math.min(record[i - 1], record[i]);
			} else {
				newRecord[i] = triangle.get(level - 1).get(i) + record[i - 1];
			}
			min = Math.min(min, newRecord[i]);
		}

		if (level >= triangle.size()) {
			return min;
		}

		return recurse(triangle, newRecord, level + 1);
	}

	public int minimumTotal2(List<List<Integer>> triangle) {
		if (triangle == null) {
			return 0;
		}
		int size = triangle.size();
		int[] record = new int[size];
		for (int c = size - 1; c >= 0; c--) {
			List<Integer> currentList = triangle.get(c);
			for (int i = 0; i < currentList.size(); i++) {
				if (c == size - 1) {
					record[i] = currentList.get(i);
				} else {
					record[i] = currentList.get(i) + Math.min(record[i], record[i + 1]);
				}
			}
		}
		return record[0];
	}

	public static void main(String[] args) {
		Code120_triangle c = new Code120_triangle();
		List<List<Integer>> triangle = Arrays.asList(
				Arrays.asList(    2),
				Arrays.asList(  3, 4),
				Arrays.asList( 6, 5, 7),
				Arrays.asList(4, 1, 8, 3)
		);
		int ans = c.minimumTotal2(triangle);
		System.out.println(ans);
	}
}
