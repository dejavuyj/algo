package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Code145_binary_tree_postorder_traversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recurse(root, list);
        return list;
    }

    private void recurse(TreeNode node, List<Integer> list) {
        if (node != null) {
            recurse(node.left, list);
            recurse(node.right, list);
            list.add(node.val);
        }
    }

    public static void main(String[] args) {
        Code145_binary_tree_postorder_traversal c = new Code145_binary_tree_postorder_traversal();
        Integer[] pArr = {1, null, 2, null, null, 3};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        List<Integer> ret = c.postorderTraversal(p);
        ret.forEach(System.out::println);
    }
}
