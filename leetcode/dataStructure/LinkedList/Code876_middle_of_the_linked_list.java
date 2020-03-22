package leetcode.dataStructure.LinkedList;

public class Code876_middle_of_the_linked_list {

	public ListNode middleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null && fast.next!=null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static void main(String[] args) {
		Code876_middle_of_the_linked_list c = new Code876_middle_of_the_linked_list();
		int[] array = { 1, 2, 3, 4, 5, 6 };
		ListNode l = GenerateList.generate(array);

		ListNode r = c.middleNode(l);
		while (r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}