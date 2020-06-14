package leetcode.dataStructure.tree;

public class Code236_lowest_common_ancestor_of_a_binary_tree {

	private static TreeNode ans = null;

	private static boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
		if (currentNode == null) {
			return false;
		}

		int left = recurseTree(currentNode.left, p, q) ? 1 : 0;
		int right = recurseTree(currentNode.right, p, q) ? 1 : 0;
		int mid = 0;
		if (currentNode.val == p.val || currentNode.val == q.val) {
			mid = 1;
		}

		if (left + right + mid >= 2) {
			ans = currentNode;
		}

		return (left + right + mid > 0);
	}

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		recurseTree(root, p, q);
		return ans;
	}

	public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}

		TreeNode left = lowestCommonAncestor2(root.left, p, q);
		TreeNode right = lowestCommonAncestor2(root.right, p, q);

		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return root;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
		TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);
		TreeNode t5 = root.left;
		TreeNode t1 = root.right;
		// TreeNode t4 = t5.right.right;

		System.out.println(lowestCommonAncestor2(root, t5, t1).val);
		// System.out.println(lowestCommonAncestor2(root, t5, t4).val);
	}
}
