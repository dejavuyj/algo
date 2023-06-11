package leetcode.dataStructure.tree;

import java.util.Deque;
import java.util.LinkedList;

public class Code173_binary_search_tree_iterator {

//    static class BSTIterator {
//        Deque<Integer> deque = new LinkedList<>();
//
//        public BSTIterator(TreeNode root) {
//            inRecurse(root);
//        }
//
//        private void inRecurse(TreeNode node) {
//            if (node != null) {
//                inRecurse(node.left);
//                deque.addLast(node.val);
//                inRecurse(node.right);
//            }
//        }
//
//        public int next() {
//            return deque.pop();
//        }
//
//        public boolean hasNext() {
//            return deque.size() > 0;
//        }
//    }

    static class BSTIterator {
        private TreeNode cur;
        private Deque<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            cur = root;
            stack = new LinkedList<>();
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ret = cur.val;
            cur = cur.right;
            return ret;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        Integer[] pArr = {7, 3, 15, null, null, 9, 20};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        BSTIterator b = new BSTIterator(p);
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.hasNext());
        System.out.println(b.next());
        System.out.println(b.hasNext());
        System.out.println(b.next());
        System.out.println(b.hasNext());
        System.out.println(b.next());
        System.out.println(b.hasNext());
    }
}
