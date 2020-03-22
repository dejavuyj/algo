package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Code98_validate_binary_search_tree {

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

	private static boolean isValidBSTCheckBorder(TreeNode node, Integer lower,
			Integer upper) {
		if (node == null) {
			return true;
		}

		if (lower != null && node.val <= lower) {
			return false;
		}
		if (upper != null && node.val >= upper) {
			return false;
		}

		if (!isValidBSTCheckBorder(node.left, lower, node.val)) {
			return false;
		}
		if (!isValidBSTCheckBorder(node.right, node.val, upper)) {
			return false;
		}

		return true;
	}

	public static boolean isValidBST(TreeNode root) {
		return isValidBSTCheckBorder(root, null, null);
	}

	private static TreeNode prev = null;
	private static List<Integer> l = new ArrayList<Integer>();

	private static boolean inOrderCheck(TreeNode node) {
		if (node == null) {
			return true;
		}
		if (!inOrderCheck(node.left)) {
			return false;
		}
		if (prev != null && prev.val >= node.val) {
			return false;
		}
		prev = node;
		l.add(node.val);
		return inOrderCheck(node.right);
	}

	public static boolean isValidBST2(TreeNode root) {
		return inOrderCheck(root);
	}

	public static void main(String[] args) {
		// Integer[] arr = new Integer[] { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5
		// };
		Integer[] arr = new Integer[] { 10, 5, 15, null, null, 13, 20, null,
				null, null, null, 11, 14 };
		// Integer[] arr = new Integer[] { 0 };
		TreeNode root = createBinaryTreeByArray(arr, 0);
		System.out.println(isValidBST(root));
		for (Integer i : l) {
			System.out.print(i);
			System.out.print(',');
		}
	}
}
