package leetcode.dataStructure.tree;


import java.util.LinkedList;
import java.util.Queue;

public class Code100_same_tree {

    // 深度优先
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    // 广度优先
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == null && q == null;
        }

        Queue<TreeNode> pQueue = new LinkedList<>();
        pQueue.add(p);
        Queue<TreeNode> qQueue = new LinkedList<>();
        qQueue.add(q);

        while (!pQueue.isEmpty() && !qQueue.isEmpty()) {
            TreeNode pNode = pQueue.poll();
            TreeNode qNode = qQueue.poll();

            if (pNode.val != qNode.val) {
                return false;
            }

            if (pNode.left == null ^ qNode.left == null) {
                return false;
            }
            if (pNode.right == null ^ qNode.right == null) {
                return false;
            }

            if (pNode.left != null) {
                pQueue.add(pNode.left);
            }
            if (pNode.right != null) {
                pQueue.add(pNode.right);
            }
            if (qNode.left != null) {
                qQueue.add(qNode.left);
            }
            if (qNode.right != null) {
                qQueue.add(qNode.right);
            }
        }

        return pQueue.isEmpty() && qQueue.isEmpty();
    }

    public static void main(String[] args) {
        Code100_same_tree c = new Code100_same_tree();
        Integer[] pArr = {};
        Integer[] qArr = {};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        TreeNode q = TreeNode.createBinaryTreeByArray(qArr, 0);
        boolean ret = c.isSameTree(p, q);
        System.out.println(ret);
    }
}
