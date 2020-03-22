package leetcode.dataStructure.LinkedList;

import java.util.HashSet;

public class Code141_linked_list_cycle {

	public static boolean hasCycle1(ListNode head) {
		if(head == null || head.next == null) {
			return false;
		}

		HashSet<ListNode> s = new HashSet<ListNode>();
        ListNode r = head;
        while(r != null) {
        	if(s.contains(r)) {
        		return true;
        	}
        	s.add(r);
			r = r.next;
        }
        
        return false;
    }

	//Ë«Ö¸Õë
	public static boolean hasCycle2(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast) {
			if (fast == null || fast.next == null) {
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n3;
		boolean r = hasCycle2(n1);
		System.out.println(r);
	}
}
