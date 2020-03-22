package leetcode.dataStructure.LinkedList;

public class Code25_reverse_nodes_in_k_group {

	private static ListNode reverseList(ListNode head) {		
		ListNode prev = null;
		ListNode current = head;
		
		while(current != null) {
			ListNode tempNext = current.next;
			current.next = prev;
			prev = current;
			current = tempNext;
		}
		
		return prev;
	}
	
	public static ListNode reverseKGroup(ListNode head, int k) {
		if(head == null || head.next == null || k < 1) {
			return head;
		}

		ListNode p = head;
		for(int i=0; i<k-1; i++) {
			p = p.next;
			if(p == null) {
				return head;
			}
		}
		
		ListNode nextStart = p.next;
		p.next = null;
		ListNode newHead = reverseList(head);
		
		head.next = reverseKGroup(nextStart, k);

		return newHead;
    }

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 };
		ListNode l = GenerateList.generate(array);

		//ListNode r = reverseList(l);
		ListNode r = reverseKGroup(l,3);
		//ListNode r = reverseList(r2);
		//ListNode r = reverseKGroup(n1,3);
		while(r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}
