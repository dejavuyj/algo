package leetcode.dataStructure.SkipList;

@SuppressWarnings("unused")
public class Code1206_design_skiplist {

	// 17  ���� https://time.geekbang.org/column/article/42896
	// https://github.com/wangzheng0822/algo/tree/master/java/17_skiplist

	private static final float SKIPLIST_P = 0.5f;
	private static final int MAX_LEVEL = 16;

	class Node {
		int value;
		Node next;

		public Node(int v) {
			this.value = v;
		}
	}

	class IndexNode {
		int value;
		IndexNode nextIdNode;
		IndexNode downIdNode;
		Node down;

		public IndexNode(int v) {
			this.value = v;
		}
	}

	Node head;
	IndexNode idHead;
	long count = 0;
	int idLevels = 0;

	// LinkedList<IndexNode> levels = new LinkedList<IndexNode>();

	// public Code1206_design_skiplist() {
	//
	// }

	// �ӵ�0�����²��ҵ�level�������е�һ������num��indexNode�����һ��С��num��indexNode
	private IndexNode getNearestIndexNode(int num, int level) {
		IndexNode currIdNode = idHead;
		int i = 0;
		while (i < level) {
			IndexNode nextIdNode = currIdNode.nextIdNode;
			if (currIdNode.value == num || nextIdNode == null
					|| nextIdNode.value >= num) {
				currIdNode = currIdNode.downIdNode;
				i++;
			} else {
				currIdNode = nextIdNode;
			}
			if (i > level - 1) {
				break;
			}
		}

		// �Ѿ����˵�level��
		while (true) {
			IndexNode next = currIdNode.nextIdNode;
			if (currIdNode.value == num || next == null || next.value > num) {
				break;
			}
			currIdNode = next;
		}
		return currIdNode;
	}

	// ���ҵ�һ������num��node�����һ��С��num��node
	private Node getNearestNode(int num) {
		IndexNode currIdNode = idHead;
		while (true) {
			IndexNode nextIdNode = currIdNode.nextIdNode;
			if (currIdNode.value == num || nextIdNode == null
					|| nextIdNode.value > num) {
				if (currIdNode.down != null) {
					// �Ѿ����˵�һ������
					break;
				} else {
					currIdNode = currIdNode.downIdNode;
				}
			} else {
				currIdNode = nextIdNode;
			}
		}
		Node curr = currIdNode.down;
		while (true) {
			Node next = curr.next;
			if (curr.value == num || next == null || next.value > num) {
				break;
			}
			curr = next;
		}
		return curr;
	}

	// �����õ��������,û�п��ǵ�ÿһ������������
	private int getRandomLevels_old() {
		// ����levels log(n)
		int expectedLevels = (int) (Math.log((double) count) / Math
				.log((double) 2));
		// ���levels
		int randomLevels = (int) (Math.random() * expectedLevels);
		return randomLevels;
	}

	// ����������һ��������Ԫ�ظ���Ӧ��ռԭʼ���ݵ� 50%������������Ԫ�ظ���ռ 25%����������12.5% ��һֱ����㡣
	// ��Ϊ����ÿһ��Ľ��������� 50%������ÿһ���²���Ľڵ㣬����Ҫ���� randomLevel ����һ������Ĳ�����
	// �� randomLevel ������������� 1~MAX_LEVEL ֮��������� ��
	// 50%�ĸ��ʷ��� 1
	// 25%�ĸ��ʷ��� 2
	// 12.5%�ĸ��ʷ��� 3 ...
	private int getRandomLevels() {
		int level = 0;

		while (Math.random() < SKIPLIST_P && level < MAX_LEVEL)
			level += 1;
		return level;
	}

	private void addIndexs(Node node) {
		int randomLevels = getRandomLevels();
		if (randomLevels == 0) {
			return;
		}

		if (randomLevels > idLevels) {
			// ����ÿ��������ͷ�ڵ�
			int levelCount = randomLevels;
			IndexNode preIdNode = idHead;
			while (levelCount > idLevels) {
				IndexNode idNode = new IndexNode(idHead.value);
				idNode.downIdNode = preIdNode;
				preIdNode = idNode;
				levelCount--;
			}
			idHead = preIdNode;
			idLevels = randomLevels;
		}

		IndexNode currHeadNode = idHead;
		int currLevel = 0; // ���������� Ŀǰ���ڵ�0������
		int skipLevels = idLevels - randomLevels;
		while (skipLevels > 0) {
			currHeadNode = currHeadNode.downIdNode;
			skipLevels--;
			currLevel++;
		}

		// ��ʼ����IndexNode
		int value = node.value;
		IndexNode preInsertIdNode = null;
		while (currLevel < idLevels) {
			IndexNode insertIdNode = new IndexNode(value);
			IndexNode preIndexNode = getNearestIndexNode(value, currLevel);
			insertIdNode.nextIdNode = preIndexNode.nextIdNode;
			preIndexNode.nextIdNode = insertIdNode;

			if (preInsertIdNode != null) {
				preInsertIdNode.downIdNode = insertIdNode;
			}
			preInsertIdNode = insertIdNode;

			if (currLevel == idLevels - 1) {
				// ���˵�һ������
				insertIdNode.down = node;
			}
			currLevel++;
		}
	}

	// �����µ�ͷ�ڵ�
	private void addHead(Node newHead) {
		newHead.next = head;
		head = newHead;
		// ��㴦������
		IndexNode currHeadNode = idHead;
		while (currHeadNode.down == null) {
			currHeadNode.value = newHead.value;
			currHeadNode = currHeadNode.downIdNode;
		}
		currHeadNode.value = newHead.value;
		currHeadNode.down = head;
	}

	public boolean search(int target) {
		Node node = getNearestNode(target);
		return node.value == target;
	}

	public void add(int num) {
		Node node = new Node(num);
		count++;
		if (head == null) {
			head = node;
			idHead = new IndexNode(num);
			idHead.down = head;
			idLevels = 1;
			return;
		}

		if (num < head.value) {
			addHead(node);
		} else {
			Node nearestNode = getNearestNode(num);
			node.next = nearestNode.next;
			nearestNode.next = node;

			addIndexs(node);
		}
	}

	// �ӵ�0������,ɾ����level�������е�һ������num��indexNode
	private void removeIndexNode(int num, int level) {
		IndexNode prev = getNearestIndexNode(num - 1, level);
		// numǰ����ܵ�ֵ���ܻ��ظ�
		while (prev.nextIdNode != null && prev.nextIdNode.value < num) {
			prev = prev.nextIdNode;
		}
		if (prev.nextIdNode != null && prev.nextIdNode.value == num) {
			// �ҵ���һ������num��ǰ�̽��
			prev.nextIdNode = prev.nextIdNode.nextIdNode;
		}
	}

	public boolean erase(int num) {
		Node node = getNearestNode(num);
		if (node.value != num) {
			return false;
		}

		int nextValue = 0;
		if (node.next != null) {
			nextValue = node.next.value;
		}

		IndexNode currHeadNode = idHead;
		int currLevel = 0;
		while (currHeadNode.down == null) {
			if (idHead.value == num) {
				// ɾ������ͷ�ڵ�
				currHeadNode.value = nextValue;
			} else {
				// ���ɾ������
				removeIndexNode(num, currLevel);
			}
			currHeadNode = currHeadNode.downIdNode;
			currLevel++;
		}

		// ���˵�һ������
		if (head.value == num) {
			// ɾ������ͷ�ڵ�
			currHeadNode.down = node.next;
			head = node.next;
		} else {
			// ɾ����һ������
			removeIndexNode(num, currLevel);

			// ɾ�������������node
			Node preNode = getNearestNode(num - 1);
			if (preNode.next != null) {
				// numǰ����ܵ�ֵ���ܻ��ظ�
				while (preNode.next != null && preNode.next.value < num) {
					preNode = preNode.next;
				}
				preNode.next = preNode.next.next;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Code1206_design_skiplist skiplist = new Code1206_design_skiplist();
		// 1
		// boolean ret;
		// skiplist.add(1);
		// skiplist.add(2);
		// skiplist.add(3);
		// ret = skiplist.search(0); // ���� false
		// System.out.println(ret);
		// skiplist.add(4);
		// ret = skiplist.search(1); // ���� true
		// System.out.println(ret);
		// ret = skiplist.erase(0); // ���� false��0 ����������
		// System.out.println(ret);
		// ret = skiplist.erase(1); // ���� true
		// System.out.println(ret);
		// ret = skiplist.search(1); // ���� false��1 �ѱ�����
		// System.out.println(ret);

		// 2
		// skiplist.add(0);
		// skiplist.add(5);
		// skiplist.add(2);
		// skiplist.add(1);
		// System.out.println(skiplist.search(0));
		// System.out.println(skiplist.erase(5));
		// System.out.println(skiplist.search(2));
		// System.out.println(skiplist.search(3));
		// System.out.println(skiplist.search(2));

		// 3
		// skiplist.add(9);
		// skiplist.add(4);
		// skiplist.add(5);
		// skiplist.add(6);
		// skiplist.add(9);
		// System.out.println(skiplist.erase(2));
		// System.out.println(skiplist.erase(1));
		// skiplist.add(2);
		// System.out.println(skiplist.search(7));
		// System.out.println(skiplist.search(4));
		// skiplist.add(5);
		// System.out.println(skiplist.erase(6));
		// System.out.println(skiplist.search(5));
		// skiplist.add(6);
		// skiplist.add(7);
		// skiplist.add(4);
		// System.out.println(skiplist.erase(3));
		// System.out.println(skiplist.search(6));
		// System.out.println(skiplist.erase(3));

		// 5
		// skiplist.add(16);
		// skiplist.add(5);
		// skiplist.add(14);
		// skiplist.add(13);
		// skiplist.add(0);
		// skiplist.add(3);
		// skiplist.add(12);
		// skiplist.add(9);
		// skiplist.add(12);
		// System.out.println(skiplist.erase(3));
		// System.out.println(skiplist.search(6));
		// skiplist.add(7);
		// System.out.println(skiplist.erase(0));
		// System.out.println(skiplist.erase(1));
		// System.out.println(skiplist.erase(10));
		// skiplist.add(5);
		// System.out.println(skiplist.search(12));
		// System.out.println(skiplist.search(7));
		// System.out.println(skiplist.search(16));
		// System.out.println(skiplist.erase(7));
		// System.out.println(skiplist.search(0));
		// skiplist.add(9);
		// skiplist.add(16);
		// skiplist.add(3);
		// System.out.println(skiplist.erase(2));
		// System.out.println(skiplist.search(17));
		// skiplist.add(2);
		// System.out.println(skiplist.search(17));
		// System.out.println(skiplist.erase(0));
		// System.out.println(skiplist.search(9));
		// System.out.println(skiplist.search(14));
		// System.out.println(skiplist.erase(1));
		// System.out.println(skiplist.erase(6));
		// skiplist.add(1);
		// System.out.println(skiplist.erase(16));
		// System.out.println(skiplist.search(9));
		// System.out.println(skiplist.erase(10));
		// System.out.println(skiplist.erase(9));
		// System.out.println(skiplist.search(2));
		// skiplist.add(3);
		// skiplist.add(16);
		// System.out.println(skiplist.erase(15));
		// System.out.println(skiplist.erase(12));
		// System.out.println(skiplist.erase(7));
		// skiplist.add(4);
		// System.out.println(skiplist.erase(3));
		// skiplist.add(2);
		// System.out.println(skiplist.erase(1));
		// System.out.println(skiplist.erase(14));
		// skiplist.add(13);
		// skiplist.add(12);
		// skiplist.add(3);
		// System.out.println(skiplist.search(6));
		// System.out.println(skiplist.search(17));
		// skiplist.add(2);
		// System.out.println(skiplist.erase(3));
		// System.out.println(skiplist.search(14));
		// skiplist.add(11);
		// skiplist.add(0);
		// System.out.println(skiplist.search(13));
		// skiplist.add(2);
		// System.out.println(skiplist.search(1));
		// System.out.println(skiplist.erase(10));
		// System.out.println(skiplist.erase(17));
		// System.out.println(skiplist.search(0));
		// System.out.println(skiplist.search(5));
		// System.out.println(skiplist.erase(8));
		// System.out.println(skiplist.search(9));
		// skiplist.add(8);
		// System.out.println(skiplist.erase(11));
		// System.out.println(skiplist.search(10));
		// System.out.println(skiplist.erase(11));
		// System.out.println(skiplist.search(10));
		// System.out.println(skiplist.erase(9));
		// System.out.println(skiplist.erase(8));
		// System.out.println(skiplist.search(15));
		// System.out.println(skiplist.search(14));
		// skiplist.add(1);
		// skiplist.add(6);
		// skiplist.add(17);
		// skiplist.add(16);
		// System.out.println(skiplist.search(13));
		// System.out.println(skiplist.search(4));
		// System.out.println(skiplist.search(5));
		// System.out.println(skiplist.search(4));
		// System.out.println(skiplist.search(17));
		// System.out.println(skiplist.search(16));
		// System.out.println(skiplist.search(7));
		// System.out.println(skiplist.search(14));
		// System.out.println(skiplist.search(1));

		long t1 = System.currentTimeMillis();
		for (int i = 1; i <= 30000; i++) {
			skiplist.add(i);
		}
		for (int i = 30000; i <= 1; i--) {
			skiplist.erase(i);
		}
		long t2 = System.currentTimeMillis();
		System.out.println((double) (t2 - t1) / 1000);
	}
}
