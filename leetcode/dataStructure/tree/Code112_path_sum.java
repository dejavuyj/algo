package leetcode.dataStructure.tree;

public class Code112_path_sum {

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		sum -= root.val;
		if (root.left == null && root.right == null) {
			return sum == 0;
		}
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}

	public static void main(String[] args) {
		Code112_path_sum c = new Code112_path_sum();
		Integer[] arr = new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, 1 };
		TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);

		boolean ret = c.hasPathSum(root, 22);
		System.out.println(ret);
	}
}