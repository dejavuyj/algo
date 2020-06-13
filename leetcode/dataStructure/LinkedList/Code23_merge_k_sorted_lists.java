package leetcode.dataStructure.LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Code23_merge_k_sorted_lists {

	// 逐一比较,时间复杂度 O(kN),k是链表条数
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		ListNode ans = null;
		ListNode ansCur = null;
		int size = lists.length;
		int leftCnt = size;
		ListNode[] curs = new ListNode[size];
		for (int i = 0; i < size; i++) {
			curs[i] = lists[i];
			if (curs[i] == null) {
				leftCnt--;
			}
		}

		while (leftCnt > 0) {
			int min = Integer.MAX_VALUE;
			int minIndex = 0;
			for (int i = 0; i < size; i++) {
				if (curs[i] != null && curs[i].val < min) {
					min = curs[i].val;
					minIndex = i;
				}
			}
			if (ansCur == null) {
				ans = curs[minIndex];
				ansCur = curs[minIndex];
			} else {
				ansCur.next = curs[minIndex];
				ansCur = ansCur.next;
			}
			curs[minIndex] = curs[minIndex].next;
			if (curs[minIndex] == null) {
				leftCnt--;
			}
		}
		return ans;
	}

	// 优先队列,时间复杂度 O(Nlogk),k是链表条数
	public ListNode mergeKLists2(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		int size = lists.length;

		Queue<ListNode> queue = new PriorityQueue<ListNode>(size, new Comparator<ListNode>() {
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});

		for (int i = 0; i < size; i++) {
			if (lists[i] != null) {
				queue.add(lists[i]);
			}
		}

		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		while (!queue.isEmpty()) {
			ListNode top = queue.poll();
			cur.next = top;
			cur = cur.next;

			if (top.next != null) {
				queue.add(top.next);
			}
		}

		return dummy.next;
	}

	public static void main(String[] args) {
		Code23_merge_k_sorted_lists c = new Code23_merge_k_sorted_lists();
		ListNode[] lists = new ListNode[3];
		lists[0] = GenerateList.generate(new int[] { -10, -9, -9, -9, -7, -2, -1, 2, 4 });
		lists[1] = GenerateList.generate(new int[] { -9, -7, -6, -6, -3, 0, 1, 3 });
		lists[2] = GenerateList.generate(new int[] { -10, -9, -2, -1, 1, 3 });

		ListNode r = c.mergeKLists2(lists);
		while (r != null) {
			System.out.print(r.val + "->");
			r = r.next;
		}
	}
}