package leetcode.dataStructure.tree;

import java.util.*;

public class Code107_binary_tree_level_order_traversal_ii {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i< size; i ++) {
                TreeNode n = deque.pop();
                if (n.left != null) {
                    deque.addLast(n.left);
                }
                if (n.right != null) {
                    deque.addLast(n.right);
                }
                curLevel.add(n.val);
            }
            stack.push(curLevel);
        }
        while (!stack.empty()) {
            ret.add(stack.pop());
        }
        return ret;
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i< size; i ++) {
                TreeNode n = queue.poll();
                if (n.left != null) {
                    queue.offer(n.left);
                }
                if (n.right != null) {
                    queue.offer(n.right);
                }
                curLevel.add(n.val);
            }
            ret.add(0, curLevel);
        }
        return ret;
    }

    public static void main(String[] args) {
        Code107_binary_tree_level_order_traversal_ii c = new Code107_binary_tree_level_order_traversal_ii();
        Integer[] pArr = {3, 9, 20, null, null, 15, 7};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        List<List<Integer>> r = c.levelOrderBottom(p);
        r.forEach(l -> {
            System.out.print("[");
            l.forEach(i -> System.out.print(i + ","));
            System.out.println("]");
        });
    }
}
