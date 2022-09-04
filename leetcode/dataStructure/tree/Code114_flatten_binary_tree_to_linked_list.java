package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Code114_flatten_binary_tree_to_linked_list {

    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrderTraversal(root, list);
        for (int i=1; i<list.size(); i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    private void preOrderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preOrderTraversal(root.left, list);
            preOrderTraversal(root.right, list);
        }
    }

    public void flatten2(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }

    public static void main(String[] args) {
        Code114_flatten_binary_tree_to_linked_list c = new Code114_flatten_binary_tree_to_linked_list();
        Integer[] array = {1, 2, 5, 3, 4, null, 6};
        TreeNode root = TreeNode.createBinaryTreeByArray(array, 0);
        c.flatten(root);
        RecurseTree.levelRecurse(root);
    }
}
