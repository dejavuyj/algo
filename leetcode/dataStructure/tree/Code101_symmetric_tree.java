package leetcode.dataStructure.tree;

public class Code101_symmetric_tree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null ^ root.right == null) {
            return false;
        } else if (root.left.val != root.right.val) {
            return false;
        } else {
            return isChildrenSymmetric(root.left, root.right);
        }
    }

    public boolean isChildrenSymmetric(TreeNode p, TreeNode q) {
        boolean n1 = false, n2 = false;

        if (p.left == null && q.right == null) {
            n1 = true;
        } else if (p.left == null ^ q.right == null) {
            return false;
        } else if (p.left.val != q.right.val) {
            return false;
        }

        if (p.right == null && q.left == null) {
            n2 = true;
        } else if (p.right == null ^ q.left == null) {
            return false;
        } else if (p.right.val != q.left.val) {
            return false;
        }

        return (n1 || isChildrenSymmetric(p.left, q.right))
                && (n2 || isChildrenSymmetric(p.right, q.left));
    }

    public boolean isSymmetric2(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    public static void main(String[] args) {
        Code101_symmetric_tree c = new Code101_symmetric_tree();
//        Integer[] pArr = {1,2,2,3,4,4,3};
        Integer[] pArr = {1,2,2,null,3,null,3};
        TreeNode p = TreeNode.createBinaryTreeByArray(pArr, 0);
        boolean ret = c.isSymmetric(p);
        System.out.println(ret);
    }
}
