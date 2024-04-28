package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Code257_binary_tree_paths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        recurse(root, "", paths);
        return paths;
    }

    private void recurse(TreeNode node, String path, List<String> paths) {
        if (node != null) {
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(node.val);
            if (node.left == null && node.right == null) {
                paths.add(pathSB.toString());
            } else {
                pathSB.append("->");
                recurse(node.left, pathSB.toString(), paths);
                recurse(node.right, pathSB.toString(), paths);
            }
        }
    }

    public static void main(String[] args) {
        Code257_binary_tree_paths c = new Code257_binary_tree_paths();
        Integer[] pArr = {1, 2, 3, null, 5};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        List<String> ret = c.binaryTreePaths(p);
        ret.forEach(System.out::println);
    }
}
