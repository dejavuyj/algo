package leetcode.dataStructure.tree;

public class Node {
	int val;
	Node left;
	Node right;
	Node next;

	Node(int x) {
		val = x;
	}

	public static Node createBinaryTreeByArray(Integer[] array, int index) {
		Node tn = null;
		if (index < array.length) {
			Integer value = array[index];
			if (value == null) {
				return null;
			}
			tn = new Node(value);
			tn.left = createBinaryTreeByArray(array, 2 * index + 1);
			tn.right = createBinaryTreeByArray(array, 2 * index + 2);
			return tn;
		}
		return tn;
	}
}
