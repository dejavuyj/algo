package leetcode.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

public class Code57_insert_intervals {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] ans = new int[1][];
            ans[0] = newInterval;
            return ans;
        }
        List<int[]> inserted = new ArrayList<>(intervals.length + 1);
        for (int i = 0; i < intervals.length; i++) {
            int[] pair = intervals[i];
            if (newInterval[0] <= pair[0]) {
                inserted.add(newInterval);
            }
            inserted.add(pair);
            if (newInterval[0] <= pair[1]) {
                inserted.add(newInterval);
            }
        }
        if (newInterval[0] > intervals[intervals.length - 1][1]) {
            inserted.add(newInterval);
        }

        return merge(inserted.toArray(new int[inserted.size()][]));
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];

        List<int[]> ansList = new ArrayList<>();
        boolean placed = false;
        for (int[] interval:intervals) {
            if (left > interval[1]) {
                ansList.add(interval);
            } else if (right < interval[0]) {
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        return ansList.toArray(new int[ansList.size()][]);
    }

    public static void main(String[] args) {
        //int[][] nums = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] nums = {{1,5}};
        int[] nums2 = {0, 3};
        Code57_insert_intervals c = new Code57_insert_intervals();
        nums = c.insert(nums, nums2);
        for (int[] pair : nums) {
            System.out.println(pair[0] + ", " + pair[1]);
        }
    }
}
