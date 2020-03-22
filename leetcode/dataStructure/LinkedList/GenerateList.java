package leetcode.dataStructure.LinkedList;

public class GenerateList {

	public static ListNode generate(int[] array) {
		if(array == null || array.length == 0) {
			return null;
		}
		ListNode head = new ListNode(array[0]);
		ListNode pre = head;
		int size = array.length;
		for(int i=1; i<size; i++) {
			ListNode cur = new ListNode(array[i]);
			pre.next = cur;
			pre = cur;
		}
		return head;
	}

	public static void main(String[] args) {
		int[] array = {1,2,3};

		ListNode r = generate(array);
		while(r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}
