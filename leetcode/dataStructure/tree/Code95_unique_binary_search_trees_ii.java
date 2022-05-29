package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Code95_unique_binary_search_trees_ii {

	public List<TreeNode> generateTrees(int n) {
		List<TreeNode> ans = new ArrayList<TreeNode>();
		if (n == 0) {
			return ans;
		}
		return getAns(1, n);
	}

	private List<TreeNode> getAns(int start, int end) {
		List<TreeNode> ans = new ArrayList<TreeNode>();
		if (start > end) {
			ans.add(null);
			return ans;
		}

		if (start == end) {
			TreeNode tree = new TreeNode(start);
			ans.add(tree);
			return ans;
		}

		for (int i = start; i <= end; i++) {
			List<TreeNode> leftTrees = getAns(start , i - 1);
			List<TreeNode> rightTrees = getAns(i + 1 , end);
			for (TreeNode leftTree: leftTrees) {
				for(TreeNode rightTree : rightTrees) {
					TreeNode root = new TreeNode(i);
					root.left = leftTree;
					root.right = rightTree;
					ans.add(root);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		Code95_unique_binary_search_trees_ii c = new Code95_unique_binary_search_trees_ii();

		List<TreeNode> l = c.generateTrees(3);
	}
}
