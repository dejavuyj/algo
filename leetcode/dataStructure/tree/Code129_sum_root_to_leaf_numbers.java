package leetcode.dataStructure.tree;

public class Code129_sum_root_to_leaf_numbers {

    public int sumNumbers(TreeNode root) {
        return recurseGetLeaf(0, root);
    }

    private int recurseGetLeaf(int parentVal, TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = parentVal * 10 + node.val;
        if (node.left == null && node.right == null) {
            return sum;
        } else {
            return recurseGetLeaf(sum, node.left) + recurseGetLeaf(sum, node.right);
        }
    }

    public static void main(String[] args) {
        Code129_sum_root_to_leaf_numbers c = new Code129_sum_root_to_leaf_numbers();
        Integer[] pArr = {1, 0};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        int ret = c.sumNumbers(p);
        System.out.println(ret);
    }
}
