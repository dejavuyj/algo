package leetcode.dataStructure.LinkedList;

public class Code86_partition_list {

    public ListNode partition(ListNode head, int x) {
		ListNode dummyNodeLess = new ListNode(-1);
		ListNode dummyNodeMore = new ListNode(-1);
		ListNode currLess = dummyNodeLess;
		ListNode currMore = dummyNodeMore;

		ListNode n = head;
		while (n != null) {
			if (n.val < x) {
				currLess.next = n;
				currLess = currLess.next;
			} else {
				currMore.next = n;
				currMore = currMore.next;
			}
			n = n.next;
		}
		currMore.next = null;
		currLess.next = dummyNodeMore.next;
		return dummyNodeLess.next;
    }

    public static void main(String[] args) {
        Code86_partition_list c = new Code86_partition_list();
        int[] array = {2, 1};
        int x = 2;
        ListNode l = GenerateList.generate(array);

        ListNode r = c.partition(l, x);
        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
