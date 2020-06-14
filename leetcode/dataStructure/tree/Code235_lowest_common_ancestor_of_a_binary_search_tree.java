package leetcode.dataStructure.tree;

public class Code235_lowest_common_ancestor_of_a_binary_search_tree {

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		Integer rootVal = root.val;
		Integer pVal = p.val;
		Integer qVal = q.val;
		if (pVal < rootVal && qVal < rootVal) {
			return lowestCommonAncestor(root.left, p, q);
		} else if (pVal > rootVal && qVal > rootVal) {
			return lowestCommonAncestor(root.right, p, q);
		} else {
			return root;
		}
	}

	public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode current = root;
		Integer pVal = p.val;
		Integer qVal = q.val;
		Integer min = Math.min(pVal,qVal);
		Integer max = Math.max(pVal,qVal);

		while(current.val < min || current.val > max) {
			if (current.val < min) {
				current = current.right;
			} else if (current.val > max) {
				current = current.left;
			}
		}
		
		return current;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 };
		TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);
		TreeNode t2 = root.left;
		TreeNode t8 = root.right;
		TreeNode t4 = t2.right;

		System.out.println(lowestCommonAncestor2(root,t2,t8).val);
		System.out.println(lowestCommonAncestor2(root,t2,t4).val);
	}
}
