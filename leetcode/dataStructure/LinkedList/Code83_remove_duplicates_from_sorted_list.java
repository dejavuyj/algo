package leetcode.dataStructure.LinkedList;

public class Code83_remove_duplicates_from_sorted_list {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode curr = head;
        while (curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Code83_remove_duplicates_from_sorted_list c = new Code83_remove_duplicates_from_sorted_list();
        int[] array = {0,0,0,1,1,1,2,3,3};
        ListNode l = GenerateList.generate(array);
        ListNode r = c.deleteDuplicates(l);
        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
