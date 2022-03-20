package leetcode.dataStructure.LinkedList;

public class Code82_remove_duplicates_from_sorted_list_ii {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        boolean removeCurrNode = false;
        ListNode ret = head;
        ListNode curr = head;
        ListNode prev = null;
        while (curr.next != null) {
            if (curr.val == curr.next.val) {
                removeCurrNode = true;
                curr.next = curr.next.next;
                continue;
            }
            if (removeCurrNode) {
                if (prev == null) {
                    ret = curr.next;
                } else {
                    prev.next = curr.next;
                }
                removeCurrNode = false;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        if (removeCurrNode) {
            if (prev != null) {
                prev.next = null;
            } else {
                ret = null;
            }
            return ret;
        }
        return ret;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        boolean removeCurrNode = false;
        ListNode curr = head;
        ListNode prev = dummy;

        while (curr.next != null) {
            if (curr.val == curr.next.val) {
                removeCurrNode = true;
                curr.next = curr.next.next;
                continue;
            }
            if (removeCurrNode) {
                prev.next = curr.next;
                removeCurrNode = false;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        if (removeCurrNode) {
            prev.next = null;
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode curr = dummy;

        while (curr.next != null && curr.next.next != null) {
            if (curr.next.val == curr.next.next.val) {
                int x = curr.next.val;
                while (curr.next != null && curr.next.val == x) {
                    curr.next = curr.next.next;
                }
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Code82_remove_duplicates_from_sorted_list_ii c = new Code82_remove_duplicates_from_sorted_list_ii();
        int[] array = {1, 2, 3, 3};
        ListNode l = GenerateList.generate(array);
        ListNode r = c.deleteDuplicates(l);
        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
