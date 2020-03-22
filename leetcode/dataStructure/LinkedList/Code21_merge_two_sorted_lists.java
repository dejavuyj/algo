package leetcode.dataStructure.LinkedList;

public class Code21_merge_two_sorted_lists {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head;
		if (l1 == null && l2 == null) {
			return null;
		} else if (l1 == null) {
			head = l2;
			l2 = l2.next;
		} else if (l2 == null) {
			head = l1;
			l1 = l1.next;
		} else {
			if (l1.val < l2.val) {
				head = l1;
				l1 = l1.next;
			} else {
				head = l2;
				l2 = l2.next;
			}
		}

		ListNode prev = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				prev.next = l1;
				prev = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				prev = l2;
				l2 = l2.next;
			}
		}

		while (l1 != null) {
			prev.next = l1;
			prev = l1;
			l1 = l1.next;
		}

		while (l2 != null) {
			prev.next = l2;
			prev = l2;
			l2 = l2.next;
		}

		return head;
	}

	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		// ÊØ»¤Õß/ÉÚ±ø
		ListNode head = new ListNode(-1);

		ListNode prev = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}

		prev.next = l1 == null ? l2 : l1;

		return head.next;
	}

	// µÝ¹é
	public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		if(l1.val < l2.val) {
			l1.next = mergeTwoLists3(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists3(l1, l2.next);
			return l2;
		}
	}

	public static void main(String[] args) {
		Code21_merge_two_sorted_lists c = new Code21_merge_two_sorted_lists();
		int[] array1 = { 1, 2, 4 };
		int[] array2 = { 1, 3, 4 };
		ListNode l1 = GenerateList.generate(array1);
		ListNode l2 = GenerateList.generate(array2);

		ListNode r = c.mergeTwoLists3(l1, l2);
		while (r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}