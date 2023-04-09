package leetcode.dataStructure.LinkedList;

public class Code160_intersection_of_two_linked_lists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode n1 = headA;
        while (n1 != null) {
            ListNode n2 = headB;
            while (n2 != null) {
                if (n1 == n2) {
                    return n1;
                }
                n2 = n2.next;
            }
            n1 = n1.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode n1 = headA;
        ListNode n2 = headB;
        while (n1 != n2) {
            n1 = n1 == null ? headB : n1.next;
            n2 = n2 == null ? headA : n2.next;
        }
        return n1;
    }

    public static void main(String[] args) {
        Code160_intersection_of_two_linked_lists c = new Code160_intersection_of_two_linked_lists();
        int[] array1 = {4, 1};
        int[] array2 = {5, 6, 1};
        int[] array3 = {8, 4, 5};
        ListNode l1 = GenerateList.generate(array1);
        ListNode l2 = GenerateList.generate(array2);
        ListNode l3 = GenerateList.generate(array3);

        l1.next.next = l3;
        l2.next.next.next = l3;
        ListNode ret = c.getIntersectionNode(l1, l2);
        System.out.println(ret);
        if (ret != null) {
            System.out.println(ret.val);
        }
    }
}
