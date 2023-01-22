package leetcode.dataStructure.LinkedList;

public class Node {
    public int val;
    public Node next;
    public Node random;

    Node(int x) {
        val = x;
    }

    Node(int x, Node next) {
        val = x;
        this.next = next;
    }
}
