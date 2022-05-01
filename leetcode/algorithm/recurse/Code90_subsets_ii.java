package leetcode.algorithm.recurse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Code90_subsets_ii {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            result.add(path);
            return result;
        }

        Arrays.sort(nums);
        used = new boolean[nums.length];

        subsetsWithDupHelper(nums, 0);

        return result;
    }

    private void subsetsWithDupHelper(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            subsetsWithDupHelper(nums, i + 1);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        Code90_subsets_ii c = new Code90_subsets_ii();
        List<List<Integer>> r = c.subsetsWithDup(nums);
        r.forEach(l -> {
            System.out.print("[");
            l.forEach(i -> {
                System.out.print(i + ",");
            });
            System.out.println("]");
        });
    }
}
