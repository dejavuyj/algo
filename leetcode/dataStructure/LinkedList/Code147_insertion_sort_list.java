package leetcode.dataStructure.LinkedList;

public class Code147_insertion_sort_list {

    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head;
        ListNode sortedEnd = head;
        while (curr != null) {
            ListNode sortedPre = dummy;
            ListNode sortedCurr = dummy.next;

            ListNode tmpNext = curr.next;
            while (sortedCurr != curr) {
                if (curr.val < sortedCurr.val) {
                    sortedEnd.next = curr.next;
                    curr.next = sortedCurr;
                    sortedPre.next = curr;
                    break;
                } else {
                    sortedPre = sortedCurr;
                    sortedCurr = sortedCurr.next;
                }
            }
            while (sortedEnd.next != null && sortedEnd.val < sortedEnd.next.val) {
                sortedEnd = sortedEnd.next;
            }
            curr = tmpNext;
        }
        return dummy.next;
    }

    public ListNode insertionSortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Code147_insertion_sort_list c = new Code147_insertion_sort_list();
        int[] array = {4, 2, 1, 3};
        ListNode l = GenerateList.generate(array);
        l = c.insertionSortList(l);
        while (l != null) {
            System.out.println(l.val);
            l = l.next;
        }
    }
}
