package leetcode.dataStructure.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class Code230_kth_smallest_element_in_a_bst {

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

    public static void main(String[] args) {
        Integer[] pArr = {5, 3, 6, 2, 4, null, null, 1};
        int k = 3;
        TreeNode root = TreeNode.createBinaryTreeByArray(pArr, 0);
        Code230_kth_smallest_element_in_a_bst c = new Code230_kth_smallest_element_in_a_bst();
        int ans = c.kthSmallest(root, k);
        System.out.println(ans);
    }
}
