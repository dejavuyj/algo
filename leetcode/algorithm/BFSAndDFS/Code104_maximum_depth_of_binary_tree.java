//package leetcode.algorithm.BFSAndDFS;
//
//import java.util.LinkedList;
//import java.util.Queue;
//
//import javafx.util.Pair;
//
//public class Code104_maximum_depth_of_binary_tree {
//
//	public class TreeNode {
//		int val;
//		TreeNode left;
//		TreeNode right;
//
//		TreeNode(int x) {
//			val = x;
//		}
//	}
//
//	private TreeNode createBinaryTreeByArray(Integer[] array, int index) {
//		TreeNode tn = null;
//		if (index < array.length) {
//			Integer value = array[index];
//			if (value == null) {
//				return null;
//			}
//			tn = new TreeNode(value);
//			tn.left = createBinaryTreeByArray(array, 2 * index + 1);
//			tn.right = createBinaryTreeByArray(array, 2 * index + 2);
//			return tn;
//		}
//		return tn;
//	}
//
//	public int maxDepth(TreeNode root) {
//		int depth = 0;
//		if (root == null) {
//			return depth;
//		}
//
//		Queue<TreeNode> queue = new LinkedList<TreeNode>();
//		queue.add(root);
//
//		while (!queue.isEmpty()) {
//			int level_length = queue.size();
//			for (int i = 0; i < level_length; i++) {
//				TreeNode node = queue.remove();
//				if (node.left != null) {
//					queue.add(node.left);
//				}
//				if (node.right != null) {
//					queue.add(node.right);
//				}
//			}
//			depth++;
//		}
//
//		return depth;
//	}
//
//	public int maxDepth2(TreeNode root) {
//		int depth = 0;
//		if (root == null) {
//			return depth;
//		}
//
//		int leftDepth = maxDepth2(root.left);
//		int rightDepth = maxDepth2(root.right);
//
//		depth = Math.max(leftDepth, rightDepth) + 1;
//
//		return depth;
//	}
//
//	public int maxDepth3(TreeNode root) {
//		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
//		if (root != null) {
//			queue.add(new Pair<>(root, 1));
//		}
//
//		int depth = 0;
//		while(!queue.isEmpty()) {
//			Pair<TreeNode, Integer> current = queue.poll();
//			TreeNode node = current.getKey();
//			int current_depth = current.getValue();
//
//			if(node != null) {
//				depth = Math.max(depth, current_depth);
//				queue.add(new Pair<>(node.left, current_depth +1));
//				queue.add(new Pair<>(node.right, current_depth +1));
//			}
//		}
//		return depth;
//	}
//
//	public static void main(String[] args) {
//		Integer[] arr = new Integer[] { 3, 9, 20, null, null, 15, 7 };
//		// Integer[] arr = new Integer[] { 0 };
//		Code104_maximum_depth_of_binary_tree code = new Code104_maximum_depth_of_binary_tree();
//		TreeNode root = code.createBinaryTreeByArray(arr, 0);
//		System.out.println(code.maxDepth3(root));
//	}
//}
