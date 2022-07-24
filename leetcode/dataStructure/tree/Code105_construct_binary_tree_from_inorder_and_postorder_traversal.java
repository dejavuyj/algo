package leetcode.dataStructure.tree;

import java.util.HashMap;
import java.util.Map;

public class Code105_construct_binary_tree_from_inorder_and_postorder_traversal {
    int post_idx;
    int[] postorder;
    int[] inorder;
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        post_idx = postorder.length - 1;

        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return recurseBuildTree(0, inorder.length - 1);
    }

    private TreeNode recurseBuildTree(int in_left, int in_right) {
        if (in_left > in_right) {
            return null;
        }

        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        int index = indexMap.get(root_val);

        post_idx--;
        root.right = recurseBuildTree(index + 1, in_right);
        root.left = recurseBuildTree(in_left, index - 1);
        return root;
    }

    public static void main(String[] args) {
        Code105_construct_binary_tree_from_inorder_and_postorder_traversal c = new Code105_construct_binary_tree_from_inorder_and_postorder_traversal();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = c.buildTree(postorder, inorder);
        RecurseTree.levelRecurse(root);
    }
}
