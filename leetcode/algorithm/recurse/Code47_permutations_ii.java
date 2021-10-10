package leetcode.algorithm.recurse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Code47_permutations_ii {
    boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);

        recurse(nums, ret, 0, perm);

        return ret;
    }

    private void recurse(int[] nums, List<List<Integer>> ret, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ret.add(new ArrayList<>(perm));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i>0 && nums[i] == nums[i-1] && !vis[i-1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            recurse(nums, ret, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Code47_permutations_ii c = new Code47_permutations_ii();
        List<List<Integer>> r = c.permuteUnique(nums);
        r.forEach(l -> {
            System.out.print("[");
            l.forEach(i -> {
                System.out.print(i + ",");
            });
            System.out.println("]");
        });
    }
}
