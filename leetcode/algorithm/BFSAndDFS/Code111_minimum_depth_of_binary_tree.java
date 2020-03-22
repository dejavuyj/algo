package leetcode.algorithm.BFSAndDFS;

public class Code111_minimum_depth_of_binary_tree {

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

	public int minDepth(TreeNode root) {
		int depth = 0;
		if (root == null) {
			return depth;
		}

		int leftDepth = minDepth(root.left);
		int rightDepth = minDepth(root.right);

		if (leftDepth != 0 && rightDepth != 0) {
			depth = Math.min(leftDepth, rightDepth) + 1;
		} else {
			depth = Math.max(leftDepth, rightDepth) + 1;
		}

		return depth;
	}

	public int minDepth2(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return 1;
		}

		int min_depth = Integer.MAX_VALUE;

		if (root.left != null) {
			min_depth = Math.min(min_depth, minDepth2(root.left));
		}
		if (root.right != null) {
			min_depth = Math.min(min_depth, minDepth2(root.right));
		}
		return min_depth + 1;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 3, 9, 20, null, null, 15, 7 };
//		 Integer[] arr = new Integer[] { 1, 2 };
		Code111_minimum_depth_of_binary_tree code = new Code111_minimum_depth_of_binary_tree();
		TreeNode root = code.createBinaryTreeByArray(arr, 0);
		System.out.println(code.minDepth2(root));
	}
}
