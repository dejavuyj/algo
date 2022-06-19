package leetcode.dataStructure.tree;


public class Code99_recover_binary_search_tree {

    private TreeNode x = null;
    private TreeNode y = null;
    private TreeNode pre = null;

    public void recoverTree(TreeNode root) {
        dfs(root);
        if (x != null && y != null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (pre == null) {
            pre = node;
        } else {
            if (pre.val > node.val) {
                y = node;
                if (x == null) {
                    x = pre;
                }
            }
            pre = node;
        }
        dfs(node.right);
    }

    public static void main(String[] args) {
        Code99_recover_binary_search_tree c = new Code99_recover_binary_search_tree();
        Integer[] arr = {1, 3, null, null, 2, null, null};
        TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);
        c.recoverTree(root);
        RecurseTree.levelRecurse(root);
    }
}
