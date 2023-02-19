package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Code144_binary_tree_preorder_traversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recurse(root, list);
        return list;
    }

    private void recurse(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);
            recurse(node.left, list);
            recurse(node.right, list);
        }
    }

    public static void main(String[] args) {
        Code144_binary_tree_preorder_traversal c = new Code144_binary_tree_preorder_traversal();
        Integer[] pArr = {1, null, 2, null, null, 3};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        List<Integer> ret = c.preorderTraversal(p);
        ret.forEach(System.out::println);
    }
}
