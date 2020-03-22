package leetcode.dataStructure.LinkedList;

public class Code92_reverse_linked_list_ii {

	public ListNode reverseBetween(ListNode head, int m, int n) {
		// head2作为反转前的最后一个节点
		ListNode head2 = head;
		ListNode cur = head;
		int i = 1;
		while (i < m) {
			if (i == m - 1) {
				head2 = cur;
			}
			cur = cur.next;
			i++;
		}

		// tail是反转部分链表结束后的尾结点
		// pre将成为反转部分链表结束后的头结点
		ListNode tail = cur;
		ListNode pre = null;
		for (i = 0; i <= n - m; i++) {
			ListNode tmpNext = cur.next;
			cur.next = pre;
			pre = cur;
			cur = tmpNext;
		}
		tail.next = cur;
		if (m == 1) {
			// head2无用,pre成为新的头节点
			return pre;
		} else {
			// head2->pre
			head2.next = pre;
			return head;
		}
	}

	public static void main(String[] args) {
		Code92_reverse_linked_list_ii c = new Code92_reverse_linked_list_ii();
		int[] array = { 1, 2, 3, 4, 5 };
		ListNode l = GenerateList.generate(array);

		ListNode r = c.reverseBetween(l, 2, 4);
		while (r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}
