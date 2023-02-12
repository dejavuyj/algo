package leetcode.dataStructure.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class Code143_reorder_list {

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode middle = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = middle.next;
        middle.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmpNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmpNext;
        }
        return prev;
    }

    private void mergeList(ListNode l1, ListNode l2) {
        ListNode tmp1;
        ListNode tmp2;
        while (l1 != null && l2 != null) {
            tmp1 = l1.next;
            tmp2 = l2.next;

            l1.next = l2;
            l1 = tmp1;

            l2.next = l1;
            l2 = tmp2;
        }
    }

    public static void main(String[] args) {
        Code143_reorder_list c = new Code143_reorder_list();
        int[] array = {1, 2, 3, 4, 5};
        ListNode l = GenerateList.generate(array);
        c.reorderList2(l);
        while (l != null) {
            System.out.println(l.val);
            l = l.next;
        }
    }
}
