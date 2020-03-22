package leetcode.dataStructure.LinkedList;

import java.util.HashSet;

public class Code142_linked_list_cycle_ii {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static ListNode detectCycle1(ListNode head) {
		if(head == null || head.next == null) {
			return null;
		}

		HashSet<ListNode> s = new HashSet<ListNode>();
        ListNode r = head;
        while(r != null) {
        	if(s.contains(r)) {
        		return r;
        	}
        	s.add(r);
			r = r.next;
        }
        
        return null;
    }

	//双指针查找交叉点
	public static ListNode getX(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}

		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast) {
			if (fast == null || fast.next == null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static ListNode detectCycle2(ListNode head) {
		ListNode x = getX(head);
		if(x == null) {
			return null;
		}

        ListNode r = head;
        while(r != x) {
			r = r.next;
			x = x.next;
        }

        return r;
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
		n5.next = n1;
		ListNode r = detectCycle2(n1);
		System.out.println(r.val);
	}
}
