package leetcode.dataStructure.LinkedList;

public class Code203_remove_linked_list_elements {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (head != null) {
            while (head != null && head.val == val) {
                head = head.next;
            }
            prev.next = head;
            prev = head;
            if (head != null) {
                head = head.next;
            }
        }
        return dummy.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Code203_remove_linked_list_elements c = new Code203_remove_linked_list_elements();
        int[] array1 = {7, 7, 7, 7};
        ListNode l1 = GenerateList.generate(array1);
        int val = 7;

        ListNode ret = c.removeElements(l1, val);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
    }
}
