package leetcode.dataStructure.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Code113_path_sum_ii {

    List<List<Integer>> ret = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        path.pollLast();
    }

    public static void main(String[] args) {
        Code113_path_sum_ii c = new Code113_path_sum_ii();
        Integer[] array = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        int targetSum = 22;
        TreeNode root = TreeNode.createBinaryTreeByArray(array, 0);
        List<List<Integer>> ret = c.pathSum(root, targetSum);
        for (List<Integer> l : ret) {
            for (Integer i : l) {
                System.out.print(i);
                System.out.print(',');
            }
            System.out.println();
        }
    }
}
