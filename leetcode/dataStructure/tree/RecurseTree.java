package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RecurseTree {

	private static List<Integer> prevList = new ArrayList<Integer>();
	private static List<Integer> inList = new ArrayList<Integer>();
	private static List<Integer> postList = new ArrayList<Integer>();
	private static List<Integer> levelList = new ArrayList<Integer>();

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private static TreeNode createBinaryTreeByArray(Integer[] array, int index) {
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

	private static void prevRecurse(TreeNode root) {
		if (root != null) {
			prevList.add(root.val);
			prevRecurse(root.left);
			prevRecurse(root.right);
		}
	}

	private static void inRecurse(TreeNode root) {
		if (root != null) {
			inRecurse(root.left);
			inList.add(root.val);
			inRecurse(root.right);
		}
	}

	private static void postRecurse(TreeNode root) {
		if (root != null) {
			postRecurse(root.left);
			postRecurse(root.right);
			postList.add(root.val);
		}
	}

	// 按层遍历
	private static void levelRecurse(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.remove();
			levelList.add(node.val);

			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}

	public static void main(String[] args) {
		// Integer[] arr = new Integer[] { 6, 2, 8, 0, 4, 7, 9, null, null, 3,
		// 5};
		Integer[] arr = new Integer[] { 10, 5, 15, null, null, 13, 20, null,
				null, null, null, 11, 14 };
		TreeNode root = createBinaryTreeByArray(arr, 0);
		prevRecurse(root);
		inRecurse(root);
		postRecurse(root);
		levelRecurse(root);
		for (Integer i : prevList) {
			System.out.print(i);
			System.out.print(',');
		}
		System.out.println();
		for (Integer i : inList) {
			System.out.print(i);
			System.out.print(',');
		}
		System.out.println();
		for (Integer i : postList) {
			System.out.print(i);
			System.out.print(',');
		}
		System.out.println();
		for (Integer i : levelList) {
			System.out.print(i);
			System.out.print(',');
		}
		System.out.println();
	}
}
