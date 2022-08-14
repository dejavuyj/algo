package leetcode.dataStructure.tree;

import leetcode.dataStructure.LinkedList.GenerateList;
import leetcode.dataStructure.LinkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Code109_convert_sorted_list_to_binary_search_tree {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        ListNode cur = head.next;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        return getRootNode(list, 0, list.size() - 1);
    }

    private TreeNode getRootNode(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        int midIndex;
        midIndex = left + (right - left + 1) / 2;
        TreeNode node = new TreeNode(list.get(midIndex));

        node.left = getRootNode(list, left, midIndex - 1);
        node.right = getRootNode(list, midIndex + 1, right);
        return node;
    }

    public TreeNode sortedListToBST2(ListNode head) {
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    private ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    ListNode globalHead;

    public TreeNode sortedListToBST3(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    private int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ret++;
            head = head.next;
        }
        return ret;
    }

    private TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(0);
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

    public static void main(String[] args) {
        Code109_convert_sorted_list_to_binary_search_tree c = new Code109_convert_sorted_list_to_binary_search_tree();
        int[] array = {-10, -3, 0, 5, 9};
        ListNode l = GenerateList.generate(array);
        TreeNode root = c.sortedListToBST(l);
        RecurseTree.levelRecurse(root);
    }
}
