package leetcode.dataStructure.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Code94_binary_tree_inorder_traversal {

	List<Integer> ans = new LinkedList<Integer>();

	public List<Integer> inorderTraversal(TreeNode root) {
		if (root != null) {
			inorderTraversal(root.left);
			ans.add(root.val);
			inorderTraversal(root.right);
		}
		return ans;
	}

	public List<Integer> inorderTraversal2(TreeNode root) {
		List<Integer> ans = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;
		while (curr != null || !stack.empty()) {
			while(curr != null) {
				stack.push(curr);
				curr = curr.left;
			}
			curr = stack.pop();
			ans.add(curr.val);
			curr = curr.right;
		}
		return ans;
	}

	public static void main(String[] args) {
		Code94_binary_tree_inorder_traversal c = new Code94_binary_tree_inorder_traversal();
		Integer[] arr = new Integer[] { 1, null, 2, null, null, 3 };
		TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);

		List<Integer> l = c.inorderTraversal2(root);
		for (Integer i : l) {
			System.out.print(i + ", ");
		}
	}
}
