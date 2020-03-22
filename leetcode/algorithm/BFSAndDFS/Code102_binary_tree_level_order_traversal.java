package leetcode.algorithm.BFSAndDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code102_binary_tree_level_order_traversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private TreeNode createBinaryTreeByArray(Integer[] array, int index) {
		TreeNode tn = null;
		if (index < array.length) {
			Integer value = array[index];
			if (value == null) {
				return null;
			}
			tn = new TreeNode(value);
			tn.left = createBinaryTreeByArray(array, 2 * index + 1);
			tn.right = createBinaryTreeByArray(array, 2 * index + 2);
			return tn;
		}
		return tn;
	}

	private List<List<Integer>> levels = new ArrayList<>();

	private void helper(TreeNode node, int level) {
		if (levels.size() == level) {
			levels.add(new ArrayList<Integer>());
		}

		levels.get(level).add(node.val);

		if (node.left != null) {
			helper(node.left, level + 1);
		}
		if (node.right != null) {
			helper(node.right, level + 1);
		}
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null) {
			return levels;
		}
		helper(root, 0);
		return levels;
	}

	public List<List<Integer>> levelOrder2(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			List<Integer> l = new ArrayList<Integer>();
			int level_length = queue.size();

			for (int i = 0; i < level_length; ++i) {
				TreeNode node = queue.remove();
				l.add(node.val);

				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			ans.add(l);
		}
		return ans;
	}

	private void dfs(TreeNode node, int level) {
		if (node == null) {
			return;
		}
		if(levels.size() < level+1) {
			List<Integer> l = new ArrayList<Integer>();
			levels.add(l);
		}
		
		levels.get(level).add(node.val);

		dfs(node.left, level+1);
		dfs(node.right, level+1);
	}

	public List<List<Integer>> levelOrder3(TreeNode root) {
		if (root == null) {
			return levels;
		}
		dfs(root, 0);
		return levels;
	}

	public static void main(String[] args) {
//		Integer[] arr = new Integer[] { 3, 9, 20, null, null, 15, 7 };
		Integer[] arr = new Integer[] { 10, 5, 15, null, null, 13, 20, null,
				null, null, null, 11, 14 };
		// Integer[] arr = new Integer[] { 0 };
		Code102_binary_tree_level_order_traversal code = new Code102_binary_tree_level_order_traversal();
		TreeNode root = code.createBinaryTreeByArray(arr, 0);
		List<List<Integer>> ans = code.levelOrder(root);
		for (List<Integer> l : ans) {
			for (Integer i : l) {
				System.out.print(i);
				System.out.print(',');
			}
			System.out.println();
		}
	}
}
