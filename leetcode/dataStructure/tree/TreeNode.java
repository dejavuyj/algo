package leetcode.dataStructure.tree;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	public static TreeNode createBinaryTreeByArray(Integer[] array, int index) {
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
}
