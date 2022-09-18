package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RecurseTree {

    private static List<Integer> prevList = new ArrayList<>();
    private static List<Integer> inList = new ArrayList<>();
    private static List<Integer> postList = new ArrayList<>();
    private static List<Integer> levelList = new ArrayList<>();

    private static void prevRecurse(TreeNode root) {
        if (root != null) {
            prevList.add(root.val);
            prevRecurse(root.left);
            prevRecurse(root.right);
        }
    }

    private static void inRecurse(TreeNode root) {
        if (root != null) {
            inRecurse(root.left);
            inList.add(root.val);
            inRecurse(root.right);
        }
    }

    private static void postRecurse(TreeNode root) {
        if (root != null) {
            postRecurse(root.left);
            postRecurse(root.right);
            postList.add(root.val);
        }
    }

    // 按层遍历
    public static void levelRecurse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            levelList.add(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        for (Integer i : levelList) {
            System.out.print(i);
            System.out.print(',');
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Integer[] arr = new Integer[] { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};
        Integer[] arr = new Integer[]{10, 5, 15, null, null, 13, 20, null, null, null, null, 11, 14};
        TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);
        prevRecurse(root);
        inRecurse(root);
        postRecurse(root);
        levelRecurse(root);
        for (Integer i : prevList) {
            System.out.print(i);
            System.out.print(',');
        }
        System.out.println();
        for (Integer i : inList) {
            System.out.print(i);
            System.out.print(',');
        }
        System.out.println();
        for (Integer i : postList) {
            System.out.print(i);
            System.out.print(',');
        }
        System.out.println();
    }
}
