package leetcode.dataStructure.tree;

public class Code110_balanced_binary_tree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public boolean isBalanced2(TreeNode root) {
        return height2(root) >= 0;
    }

    private int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height2(root.left);
        int rightHeight = height2(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        Code110_balanced_binary_tree c = new Code110_balanced_binary_tree();
        Integer[] array = {};
        TreeNode root = TreeNode.createBinaryTreeByArray(array, 0);
        boolean ret = c.isBalanced(root);
        System.out.println(ret);
    }
}
