package test;

import java.util.NoSuchElementException;

class LinkedList<E> {

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev; // 双向队列

		public Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.prev = prev;
			this.next = next;
		}
	}

	Node<E> first;
	Node<E> tail;
	int size = 0;

	// 尾部插入
	public boolean add(E e) {
		Node<E> t = tail;
		Node<E> node = new Node<>(t, e, null);
		tail = node;
		if (t == null) {
			first = node;
		} else {
			t.next = node;
			node.prev = t;
		}
		size++;
		return true;
	}

	// 头部删除
	public E remove() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		E element = first.item;
		Node<E> next = first.next;
		first = next;
		if (next == null) {
			tail = null;
		} else {
			next.prev = null;
		}
		size--;
		return element;
	}

	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			l.add(i);
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(l.remove());
		}
	}
}