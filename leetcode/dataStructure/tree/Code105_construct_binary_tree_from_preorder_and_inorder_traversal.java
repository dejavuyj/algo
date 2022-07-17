package leetcode.dataStructure.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Code105_construct_binary_tree_from_preorder_and_inorder_traversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length == 1) {
            return root;
        }

        int inorderRootIndex = findInorderRootIndex(root.val, inorder);
        int[] inorderLeftArr = Arrays.copyOfRange(inorder, 0, inorderRootIndex);
        int[] inorderRightArr = Arrays.copyOfRange(inorder, inorderRootIndex + 1, inorder.length);

        int[] preorderLeftArr = Arrays.copyOfRange(preorder, 1, inorderLeftArr.length + 1);
        int[] preorderRightArr = Arrays.copyOfRange(preorder, inorderLeftArr.length + 1, preorder.length);

        root.left = buildTree(preorderLeftArr, inorderLeftArr);
        root.right = buildTree(preorderRightArr, inorderRightArr);

        return root;
    }

    private int findInorderRootIndex(int root, int[] inorder) {
        int index = 0;
        while (inorder[index] != root) {
            index++;
        }
        return index;
    }

    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return recurseBuildTree(preorder, inorder, 0, inorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode recurseBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        int preorder_root = preorder_left;
        int inorder_root = indexMap.get(preorder[preorder_root]);
        TreeNode root = new TreeNode(preorder[preorder_root]);
        int size_left_subtree = inorder_root - inorder_left;
        root.left = recurseBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        root.right = recurseBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public static void main(String[] args) {
        Code105_construct_binary_tree_from_preorder_and_inorder_traversal c = new Code105_construct_binary_tree_from_preorder_and_inorder_traversal();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = c.buildTree(preorder, inorder);
        RecurseTree.levelRecurse(root);
    }
}
