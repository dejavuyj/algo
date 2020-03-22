package leetcode.algorithm.LRUCache;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

	class Node{
		int key;
		int value;
		Node prev;
		Node next;
		
		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private Map<Integer, Node> m;
	private Node head; //虚拟头结点
	private Node tail; //虚拟尾结点
	private int capacity;

	public LRUCache(int capacity) {
		m = new HashMap<Integer, Node>();
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
		this.capacity = capacity;
	}

	private void addNode(Node node) {
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
		node.prev = head;
	}

	private void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	public int get(int key) {
		Node node = m.get(key);
		if (node == null) {
			return -1;
		} else {
			// 更改链表顺序,把node移到最前面
			removeNode(node);
			addNode(node);
			return node.value;
		}
	}

	public void put(int key, int value) {
		Node node = new Node(key, value);
		Node existed = m.get(key);
		if(existed != null) {
			removeNode(existed);
		}
		m.put(key, node);
		addNode(node);

		if(m.size() > capacity) {
			Node last = tail.prev;
			removeNode(last);
			m.remove(last.key);
		}
	}
}

public class Code146_lru_cache {

	public static void main(String[] args) {
		LRUCache c = new LRUCache(2);
		int v;
//		c.put(1, 1);
//		c.put(2, 2);
//		v = c.get(1);
//		System.out.println(v);
//		c.put(3, 3);
//		v = c.get(2);
//		System.out.println(v);
//		c.put(4, 4);
//		v = c.get(1);
//		System.out.println(v);
//		v = c.get(3);
//		System.out.println(v);
//		v = c.get(4);
//		System.out.println(v);
		
		c.put(2, 1);
		c.put(1, 1);
		c.put(2, 3);
		c.put(4, 1);
		v = c.get(1);
		System.out.println(v);
		v = c.get(2);
		System.out.println(v);
	}
}

//class LRUCache extends LinkedHashMap<Integer, Integer> {
//	private static final long serialVersionUID = 5440871089044149474L;
//	private int capacity;
//
//	public LRUCache(int capacity) {
//		super(capacity, 0.75f, true);
//		this.capacity = capacity;
//	}
//
//	public int get(int key) {
//		return super.getOrDefault(key, -1);
//	}
//
//	public void put(int key, int value) {
//		super.put(key, value);
//	}
//	
//	@Override
//	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//		return size() > capacity;
//	}
//}
