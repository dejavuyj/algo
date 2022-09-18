package leetcode.dataStructure.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Code116_populating_next_right_pointers_in_each_node {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Node> list = new ArrayList<>();
            while (!deque.isEmpty()) {
                list.add(deque.pop());
            }
            for (int i = 0; i < size; i++) {
                if (i < size - 1) {
                    list.get(i).next = list.get(i + 1);
                }
                if (list.get(i).left != null) {
                    deque.addLast(list.get(i).left);
                }
                if (list.get(i).right != null) {
                    deque.addLast(list.get(i).right);
                }
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node node = deque.poll();
                if (i < size - 1) {
                    node.next = deque.peek();
                }
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
        }
        return root;
    }

    public Node connect3(Node root) {
        if (root == null) {
            return null;
        }
        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    public static void main(String[] args) {
        Code116_populating_next_right_pointers_in_each_node c = new Code116_populating_next_right_pointers_in_each_node();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        Node root = Node.createBinaryTreeByArray(arr, 0);
        Node newRoot = c.connect(root);
        System.out.println("done");
    }
}