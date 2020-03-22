package leetcode.dataStructure.tree;

public class Code235_lowest_common_ancestor_of_a_binary_search_tree {

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
		TreeNode root = createBinaryTreeByArray(arr, 0);
		TreeNode t2 = root.left;
		TreeNode t8 = root.right;
		TreeNode t4 = t2.right;

		System.out.println(lowestCommonAncestor2(root,t2,t8).val);
		System.out.println(lowestCommonAncestor2(root,t2,t4).val);
	}
}
