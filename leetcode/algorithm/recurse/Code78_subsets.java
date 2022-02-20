package leetcode.algorithm.recurse;

import java.util.ArrayList;
import java.util.List;

public class Code78_subsets {

    List<Integer> tmpList = new ArrayList<>();
    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTrackAll(nums, 0);
        return resultList;
    }

    private void backTrackAll(int[] nums, int pos) {
        if (pos == nums.length) {
            resultList.add(new ArrayList<>(tmpList));
            return;
        }
        tmpList.add(nums[pos]);
        backTrackAll(nums, pos + 1);
        tmpList.remove(tmpList.size() - 1);
        backTrackAll(nums, pos + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Code78_subsets c = new Code78_subsets();
        List<List<Integer>> r = c.subsets(nums);
        r.forEach(l -> {
            l.forEach(i -> {
                System.out.print(i + ",");
            });
            System.out.println();
        });
    }
}
