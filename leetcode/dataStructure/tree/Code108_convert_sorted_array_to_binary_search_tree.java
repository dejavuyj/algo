package leetcode.dataStructure.tree;

public class Code108_convert_sorted_array_to_binary_search_tree {

    public TreeNode sortedArrayToBST(int[] nums) {
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, 0, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, nums.length - 1);
        return root;
    }

    private TreeNode sortedArrayToBST(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        int mid = startIndex + (endIndex - startIndex) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, startIndex, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, endIndex);
        return root;
    }

    public static void main(String[] args) {
        Code108_convert_sorted_array_to_binary_search_tree c = new Code108_convert_sorted_array_to_binary_search_tree();
        int[] pArr = {-10, -3, 0, 5, 9};
        TreeNode root = c.sortedArrayToBST(pArr);
        RecurseTree.levelRecurse(root);
    }
}
