package leetcode.dataStructure.LinkedList;

public class Code234_palindrome_linked_list {

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

	public boolean isPalindrome(ListNode head) {
		boolean ret = true;
		//用快慢指针找出中间结点
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null) {
			slow = slow.next;
			fast = fast.next;
			if(fast == null) {
				break;
			}
			fast = fast.next;
		}

		ListNode l1 = head;
		//反转后半部分链表
		ListNode l2 = reverseList(slow);
		while(l2 != null) {
			if(l1.val != l2.val) {
				ret = false;
				break;
			}
			l1 = l1.next;
			l2 = l2.next;
		}

		return ret;
	}

	public static void main(String[] args) {
		Code234_palindrome_linked_list c = new Code234_palindrome_linked_list();
		int[] array = { 1, 2, 1, 1 };
		ListNode l = GenerateList.generate(array);
		boolean ret = c.isPalindrome(l);
		System.out.println(ret);
	}
}
