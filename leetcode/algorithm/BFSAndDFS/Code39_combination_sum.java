package leetcode.algorithm.BFSAndDFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Code39_combination_sum {

	private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
		if (target < 0) {
			return;
		}
		if (target == 0) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i=begin; i<len; i++) {
			path.addLast(candidates[i]);
			dfs(candidates, i, len, target-candidates[i], path, res);
			path.removeLast();
		}
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
		if (target == 0) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i=begin; i<len; i++) {
			// 剪枝
			if (target - candidates[i] < 0) {
				break;
			}
			path.addLast(candidates[i]);
			dfs2(candidates, i, len, target-candidates[i], path, res);
			path.removeLast();
		}
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
		Code39_combination_sum c = new Code39_combination_sum();
		int[] candidates = {2,3,6,7};
		int target = 7;
		List<List<Integer>> l = c.combinationSum2(candidates, target);
		for (List<Integer> l2 : l) {
			for (Integer i : l2) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}
}
