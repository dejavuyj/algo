package _dataStructure;

public class BinarySearchTree {

	private Node tree;

	public static class Node {
		private int data;
		private Node left;
		private Node right;

		public Node(int data) {
			this.data = data;
		}
	}

	public Node find(int data) {
		Node p = tree;
		while (p != null) {
			if (p.data == data) {
				return p;
			} else if (data < p.data) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		return null;
	}

	public void insert(int data) {
		if (tree == null) {
			tree = new Node(data);
			return;
		}

		Node p = tree;
		while (p != null) {
			if (data < p.data) {
				if (p.left == null) {
					p.left = new Node(data);
					return;
				}
				p = p.left;
			} else {
				if (p.right == null) {
					p.right = new Node(data);
					return;
				}
				p = p.right;
			}
		}
	}

	public void delete(int data) {
		Node p = tree;
		Node pp = null; // parent
		while (p != null) {
			pp = p;
			if (p.data == data) {
				break;
			} else if (data < p.data) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		if (p == null) {
			// not found
			return;
		}

		// has two children
		if (p.left != null && p.right != null) {
			// find min node in right child tree, and replace it
			Node minP = p.right;
			Node minPP = p; // minP parent
			while (minP.left != null) {
				minPP = minP;
				minP = minP.left;
			}
			p.data = minP.data;
			// now,p has only one child,we will delete p
			p = minP;
			pp = minPP;
		}

		Node child;
		if (p.left != null) {
			child = p.left;
		} else if (p.right != null) {
			child = p.right;
		} else {
			child = null;
		}

		if (pp == null) {
			// delete root and root has only one child
			tree = child;
		} else if (pp.left == p) {
			pp.left = child;
		} else {
			pp.right = child;
		}
	}
}
