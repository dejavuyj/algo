package leetcode.dataStructure.LinkedList;

public class Code24_swap_nodes_in_pairs {

	public static ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}

        ListNode next = head.next;
        ListNode next2 = head.next.next;
        head.next.next = head;
        
        ListNode p = swapPairs(next2);

        head.next = p;

        return next;
    }

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5 };
		ListNode l = GenerateList.generate(array);
		ListNode r = swapPairs(l);
		while(r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}
