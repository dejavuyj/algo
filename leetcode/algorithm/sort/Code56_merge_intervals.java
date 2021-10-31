package leetcode.algorithm.sort;

import java.util.*;

public class Code56_merge_intervals {

    public int[][] merge1(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, comparator);

        int prevStart = intervals[0][0];
        int prevEnd = intervals[0][1];
        for (int i=1; i< intervals.length; i++) {
            int[] curPair = intervals[i];
            if (curPair[0] > prevEnd) {
                list.add(new int[]{prevStart, prevEnd});
                prevStart = curPair[0];
            }
            prevEnd = Math.max(curPair[1], prevEnd);
        }
        list.add(new int[]{prevStart, prevEnd});

        int[][] res = new int[list.size()][];
        Object[] arr = list.toArray();
        for (int i = 0; i < list.size(); i++) {
            res[i] = (int[]) arr[i];
        }
        return res;
    }

    private static final Comparator<int []> comparator = (e1, e2) -> {
        int s1 = e1[0];
        int s2 = e2[0];
        if (s1 < s2) {
            return -1;
        } else if (s1 == s2) {
            return 0;
        }
        return 1;
    };

    public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

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

    public static void main(String[] args) {
        int[][] nums = {{1, 3}, {8, 10}, {2, 6}, {10, 18}};
        Code56_merge_intervals c = new Code56_merge_intervals();
        nums = c.merge2(nums);
        for (int[] pair : nums) {
            System.out.println(pair[0] + ", " + pair[1]);
        }
    }
}
