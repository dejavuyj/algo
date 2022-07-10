package leetcode.dataStructure.tree;

import java.util.*;

public class Code103_binary_tree_zigzag_level_order_traversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ansList = new ArrayList<>();
        if (root == null) {
            return ansList;
        }

        boolean isOrderLeft = true;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(node.val);
                } else {
                    levelList.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ansList.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ansList;
    }

    public static void main(String[] args) {
        Code103_binary_tree_zigzag_level_order_traversal c = new Code103_binary_tree_zigzag_level_order_traversal();
        Integer[] pArr = {3, 9, 20, null, null, 15, 7};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        List<List<Integer>> r = c.zigzagLevelOrder(p);

        r.forEach(l -> {
            System.out.print("[");
            l.forEach(i -> System.out.print(i + ","));
            System.out.println("]");
        });
    }
}
