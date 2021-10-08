package leetcode.algorithm.recurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Code46_permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num:nums) {
            output.add(num);
        }

        int n = nums.length;
        recurse(n, output, ret, 0);

        return ret;
    }

    private void recurse(int n, List<Integer> output, List<List<Integer>> ret, int first) {
        if (first == n) {
            ret.add(new ArrayList<>(output));
        }

        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            recurse(n, output, ret, first + 1);
            Collections.swap(output, first, i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Code46_permutations c = new Code46_permutations();
        List<List<Integer>> r = c.permute(nums);
        r.forEach(l -> {
            System.out.print("[");
            l.forEach(i -> {
                System.out.print(i + ",");
            });
            System.out.println("]");
        });
    }
}
