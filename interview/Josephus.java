package interview;

// 约瑟夫环问题
public class Josephus {

	// 问题描述：编号为 1-N 的 N 个士兵围坐在一起形成一个圆圈，从编号为 1 的士兵开始依次报数（1，2，3...这样依次报），
	// 数到 m 的士兵会被杀死出列，之后的士兵再从 1 开始报数。直到最后剩下一士兵，求这个士兵的编号。

	// 用数组,空间复杂度O(n),时间复杂度O(m*n)
	public int josephus(int n, int m) {
		if (m == 1 || n < 2) {
			return n;
		}

		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = i + 1;
		}

		int i = -1;
		int cnt = n;
		while (cnt > 1) {
			for (int j = 1; j <= m;) {
				i = (i + 1) % n;
				if (a[i] != -1) {
					j++;
				}
			}
			a[i] = -1;
			// System.out.print(i + 1 + ", ");
			cnt--;
		}
		// System.out.println();
		while (a[i] == -1) {
			i = (i + 1) % n;
		}
		return i + 1;
	}

	class Node {
		int value;
		Node next;

		public Node(int data) {
			value = data;
		}
	}

	// 用链表,空间复杂度O(n),时间复杂度O(m*n)
	public int josephus2(int n, int m) {
		if (m == 1 || n < 2) {
			return n;
		}

		Node head = new Node(1);
		Node pre = head;
		for (int i = 2; i <= n; i++) {
			Node node = new Node(i);
			pre.next = node;
			pre = node;
		}
		pre.next = head;

		Node cur = pre;
		while (cur.next != cur) {
			for (int i = 0; i < m - 1; i++) {
				cur = cur.next;
			}
			// System.out.print(cur.next.value + ", ");
			cur.next = cur.next.next;
		}
		// System.out.println();
		return cur.value;
	}

	// 用递归,空间复杂度O(n),时间复杂度O(n)
	public int josephus3(int n, int m) {
		// if (n == 1) {
		// return 1;
		// }
		// return (josephus3(n - 1, m) + m - 1) % n + 1;
		return n == 1 ? 1 : (josephus3(n - 1, m) + m - 1) % n + 1;
	}

	// 公式
	public int josephus4(int n, int m) {
		if (m == 1 || n < 2) {
			return n;
		}

		int p = 0;
		for (int i = 1; i <= n; i++) {
			p = (p + m) % i;
			// System.out.print(p + 1 + ", ");
		}
		// System.out.println();
		return p + 1;
	}

	public static void main(String[] args) {
		Josephus c = new Josephus();
		int n = 111;
		int m = 3;
		System.out.println(c.josephus(n, m));
		System.out.println(c.josephus2(n, m));
		System.out.println(c.josephus3(n, m));
		System.out.println(c.josephus4(n, m));
	}
}