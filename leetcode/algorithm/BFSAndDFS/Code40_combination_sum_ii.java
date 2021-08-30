package leetcode.algorithm.BFSAndDFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code40_combination_sum_ii {

    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            if (!duplicate(path, res)) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);
            dfs(candidates, i + 1, len, target - candidates[i], path, res);
            path.removeLast();
        }
    }

    private boolean duplicate(Deque<Integer> path, List<List<Integer>> res) {
        for (List<Integer> l2 : res) {
            Map<Integer, Integer> m = new HashMap<>();
            for (Integer i : l2) {
                m.put(i, m.getOrDefault(i, 0) + 1);
            }
            for (Integer i : path) {
                m.put(i, m.getOrDefault(i, 0) - 1);
                if (m.get(i) == 0) {
                    m.remove(i);
                }
            }
            if (m.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs2(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            dfs2(candidates, i + 1, len, target - candidates[i], path, res);
            path.removeLast();
        }
    }

    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs2(candidates, 0, len, target, path, res);
        return res;
    }

    public static void main(String[] args) {
        Code40_combination_sum_ii c = new Code40_combination_sum_ii();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> l = c.combinationSum2(candidates, target);
        for (List<Integer> l2 : l) {
            for (Integer i : l2) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
    }
}
