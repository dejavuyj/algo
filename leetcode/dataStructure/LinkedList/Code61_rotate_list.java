package leetcode.dataStructure.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class Code61_rotate_list {

    public ListNode rotateRight(ListNode head, int k) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int size = list.size();
        if (size == 0) {
            return head;
        }
        k = k % size;
        if (k != 0) {
            list.get(size - 1).next = list.get(0);
            list.get(size - 1 - k).next = null;
            return list.get(size - k);
        } else {
            return list.get(0);
        }
    }

    public ListNode rotateRight2(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }

    public static void main(String[] args) {
        Code61_rotate_list c = new Code61_rotate_list();
//        int[] array = {1, 2, 3, 4, 5};
        int[] array = {1, 2};
        ListNode l = GenerateList.generate(array);

        ListNode r = c.rotateRight(l, 1);
        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
