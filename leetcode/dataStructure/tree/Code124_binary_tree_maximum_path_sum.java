package leetcode.dataStructure.tree;

public class Code124_binary_tree_maximum_path_sum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int priceNewPath = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, priceNewPath);

        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        Code124_binary_tree_maximum_path_sum c = new Code124_binary_tree_maximum_path_sum();
        Integer[] arr = new Integer[]{-10, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);
        int ans = c.maxPathSum(root);
        System.out.println(ans);
    }
}