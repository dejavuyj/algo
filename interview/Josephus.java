package interview;

// Լɪ������
public class Josephus {

	// �������������Ϊ 1-N �� N ��ʿ��Χ����һ���γ�һ��ԲȦ���ӱ��Ϊ 1 ��ʿ����ʼ���α�����1��2��3...�������α�����
	// ���� m ��ʿ���ᱻɱ�����У�֮���ʿ���ٴ� 1 ��ʼ������ֱ�����ʣ��һʿ���������ʿ���ı�š�

	// ������,�ռ临�Ӷ�O(n),ʱ�临�Ӷ�O(m*n)
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

	// ������,�ռ临�Ӷ�O(n),ʱ�临�Ӷ�O(m*n)
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

	// �õݹ�,�ռ临�Ӷ�O(n),ʱ�临�Ӷ�O(n)
	public int josephus3(int n, int m) {
		// if (n == 1) {
		// return 1;
		// }
		// return (josephus3(n - 1, m) + m - 1) % n + 1;
		return n == 1 ? 1 : (josephus3(n - 1, m) + m - 1) % n + 1;
	}

	// ��ʽ
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