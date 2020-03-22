package leetcode.dataStructure.LinkedList;

public class Code445_add_two_numbers_ii {

	public ListNode addTwoNumbersReverse(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode pre = dummy;

		int tens = 0;
		while (l1 != null || l2 != null) {
			int val1 = l1 == null ? 0 : l1.val;
			int val2 = l2 == null ? 0 : l2.val;
			int sum = val1 + val2 + tens;
			tens = sum / 10;
			ListNode cur = new ListNode(sum % 10);
			pre.next = cur;
			pre = cur;
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
		if (tens == 1) {
			ListNode cur = new ListNode(1);
			pre.next = cur;
		}

		return dummy.next;
	}

	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode tmpNext = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmpNext;
		}
		return prev;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode r1 = reverseList(l1);
		ListNode r2 = reverseList(l2);
		ListNode r = addTwoNumbersReverse(r1, r2);
		return reverseList(r);
	}

	public static void main(String[] args) {
		Code445_add_two_numbers_ii c = new Code445_add_two_numbers_ii();
		int[] array1 = { 7, 2, 4, 3 };
		int[] array2 = { 5, 6, 4 };
		ListNode l1 = GenerateList.generate(array1);
		ListNode l2 = GenerateList.generate(array2);

		ListNode r = c.addTwoNumbers(l1, l2);
		while (r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}