package leetcode.dataStructure.SkipList;

@SuppressWarnings("unused")
public class Code1206_design_skiplist {

	// 17  跳表 https://time.geekbang.org/column/article/42896
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

	// 从第0层往下查找第level层索引中第一个等于num的indexNode或最后一个小于num的indexNode
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

		// 已经到了第level层
		while (true) {
			IndexNode next = currIdNode.nextIdNode;
			if (currIdNode.value == num || next == null || next.value > num) {
				break;
			}
			currIdNode = next;
		}
		return currIdNode;
	}

	// 查找第一个等于num的node或最后一个小于num的node
	private Node getNearestNode(int num) {
		IndexNode currIdNode = idHead;
		while (true) {
			IndexNode nextIdNode = currIdNode.nextIdNode;
			if (currIdNode.value == num || nextIdNode == null
					|| nextIdNode.value > num) {
				if (currIdNode.down != null) {
					// 已经到了第一层索引
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

	// 不够好的随机函数,没有考虑到每一层索引的数量
	private int getRandomLevels_old() {
		// 期望levels log(n)
		int expectedLevels = (int) (Math.log((double) count) / Math
				.log((double) 2));
		// 随机levels
		int randomLevels = (int) (Math.random() * expectedLevels);
		return randomLevels;
	}

	// 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
	// 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
	// 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
	// 50%的概率返回 1
	// 25%的概率返回 2
	// 12.5%的概率返回 3 ...
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
			// 插入每层索引的头节点
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
		int currLevel = 0; // 从上往下数 目前处于第0层索引
		int skipLevels = idLevels - randomLevels;
		while (skipLevels > 0) {
			currHeadNode = currHeadNode.downIdNode;
			skipLevels--;
			currLevel++;
		}

		// 开始插入IndexNode
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
				// 到了第一层索引
				insertIdNode.down = node;
			}
			currLevel++;
		}
	}

	// 插入新的头节点
	private void addHead(Node newHead) {
		newHead.next = head;
		head = newHead;
		// 逐层处理索引
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

	// 从第0层往下,删除第level层索引中第一个等于num的indexNode
	private void removeIndexNode(int num, int level) {
		IndexNode prev = getNearestIndexNode(num - 1, level);
		// num前面可能的值可能会重复
		while (prev.nextIdNode != null && prev.nextIdNode.value < num) {
			prev = prev.nextIdNode;
		}
		if (prev.nextIdNode != null && prev.nextIdNode.value == num) {
			// 找到第一个等于num的前继结点
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
				// 删除的是头节点
				currHeadNode.value = nextValue;
			} else {
				// 逐层删除索引
				removeIndexNode(num, currLevel);
			}
			currHeadNode = currHeadNode.downIdNode;
			currLevel++;
		}

		// 到了第一层索引
		if (head.value == num) {
			// 删除的是头节点
			currHeadNode.down = node.next;
			head = node.next;
		} else {
			// 删除第一层索引
			removeIndexNode(num, currLevel);

			// 删除数据链表里的node
			Node preNode = getNearestNode(num - 1);
			if (preNode.next != null) {
				// num前面可能的值可能会重复
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
		// ret = skiplist.search(0); // 返回 false
		// System.out.println(ret);
		// skiplist.add(4);
		// ret = skiplist.search(1); // 返回 true
		// System.out.println(ret);
		// ret = skiplist.erase(0); // 返回 false，0 不在跳表中
		// System.out.println(ret);
		// ret = skiplist.erase(1); // 返回 true
		// System.out.println(ret);
		// ret = skiplist.search(1); // 返回 false，1 已被擦除
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
