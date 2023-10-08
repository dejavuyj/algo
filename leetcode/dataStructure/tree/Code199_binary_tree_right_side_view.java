package leetcode.dataStructure.tree;

import java.util.*;

public class Code199_binary_tree_right_side_view {

    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        int max_depth = -1;

        Deque<TreeNode> nodeStack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }

        List<Integer> rightView = new ArrayList<>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

    int maxHigh = 0;
    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 1);
        return res;
    }

    public void dfs(TreeNode root, int high) {
        if (root == null) return;
        if (high > maxHigh) {
            res.add(root.val);
            maxHigh = high;
        }
        dfs(root.right, high + 1);
        dfs(root.left, high + 1);
    }

    public static void main(String[] args) {
        Integer[] pArr = {7, 3, 15, null, null, 9, 20};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        Code199_binary_tree_right_side_view c = new Code199_binary_tree_right_side_view();
        List<Integer> ansList = c.rightSideView(p);
        ansList.forEach(System.out::println);
    }
}
