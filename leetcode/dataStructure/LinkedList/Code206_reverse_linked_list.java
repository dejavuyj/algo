package leetcode.dataStructure.LinkedList;

import java.util.ArrayList;

public class Code206_reverse_linked_list {
	
	public static ListNode reverseList_1(ListNode head) {
		if(head == null) {
			return head;
		}

        ListNode r = head;
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        while(r != null) {
        	list.add(r);
			r = r.next;
        }
        for(int i=list.size()-1; i>=1; i--) {
        	list.get(i).next = list.get(i-1);
        }
        list.get(0).next = null;
        return list.get(list.size()-1);
    }

	public static ListNode reverseList_2(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while(curr != null) {
			ListNode tmpNext = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmpNext;
		}
		return prev;
    }

	//ตÝน้
	public static ListNode reverseList_3(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode p = reverseList_3(head.next);
		head.next.next = head;
		head.next = null;
		return p;
    }

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5 };
		ListNode l = GenerateList.generate(array);
		ListNode r = reverseList_3(l);
		while(r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}
