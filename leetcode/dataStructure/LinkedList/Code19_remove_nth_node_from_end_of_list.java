package leetcode.dataStructure.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class Code19_remove_nth_node_from_end_of_list {

	public ListNode removeNthFromEnd(ListNode head, int n) {
		List<ListNode> l = new ArrayList<ListNode>();
		ListNode cur = head;
		while (cur != null) {
			l.add(cur);
			cur = cur.next;
		}

		int size = l.size();
		if (n == 1) {
			if (size > 1) {
				l.get(size - 2).next = null;
			} else {
				return null;
			}
		} else if (n == size) {
			return l.get(1);
		} else {
			int index = size - n;
			l.get(index - 1).next = l.get(index + 1);
		}
		return head;
	}

	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p = dummy;
		ListNode q = dummy;
		// 先让q向前移n+1
		for (int i = 0; i < n + 1; i++) {
			q = q.next;
		}
		// 把q移到null,删除p.next即可
		while (q != null) {
			p = p.next;
			q = q.next;
		}
		p.next = p.next.next;
		return dummy.next;
	}

	public static void main(String[] args) {
		Code19_remove_nth_node_from_end_of_list c = new Code19_remove_nth_node_from_end_of_list();
		int[] array = { 1, 2, 3, 4, 5 };
		// int[] array = { 1, 2 };
		ListNode l = GenerateList.generate(array);

		ListNode r = c.removeNthFromEnd2(l, 2);
		while (r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}
