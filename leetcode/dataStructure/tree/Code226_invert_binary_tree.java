package leetcode.dataStructure.tree;

public class Code226_invert_binary_tree {

	private void invertNode(TreeNode node) {
		if (node == null || (node.left == null && node.right == null)) {
			return;
		}

		TreeNode tmp = node.left;
		node.left = node.right;
		node.right = tmp;

		invertNode(node.left);
		invertNode(node.right);
	}

	public TreeNode invertTree(TreeNode root) {
		invertNode(root);
		return root;
	}

	public static void main(String[] args) {
		Code226_invert_binary_tree c = new Code226_invert_binary_tree();
		Integer[] arr = new Integer[] { 4, 2, 7, 1, 3, 6, 9 };
		TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);

		root = c.invertTree(root);
		RecurseTree.levelRecurse(root);
	}
}
