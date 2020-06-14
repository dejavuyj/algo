package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Code98_validate_binary_search_tree {

	private static boolean isValidBSTCheckBorder(TreeNode node, Integer lower, Integer upper) {
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
		// Integer[] arr = { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 };
		Integer[] arr = { 10, 5, 15, null, null, 13, 20, null, null, null, null, 11, 14 };
		// Integer[] arr = new Integer[] { 0 };
		TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);
		System.out.println(isValidBST(root));
		for (Integer i : l) {
			System.out.print(i);
			System.out.print(',');
		}
	}
}
