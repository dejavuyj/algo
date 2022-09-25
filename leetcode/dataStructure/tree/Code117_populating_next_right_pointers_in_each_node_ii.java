package leetcode.dataStructure.tree;

public class Code117_populating_next_right_pointers_in_each_node_ii {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur != null) {
            Node dummy = new Node(0);
            Node pre = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            cur = dummy.next;
        }
        return root;
    }

    public static void main(String[] args) {
        Code117_populating_next_right_pointers_in_each_node_ii c = new Code117_populating_next_right_pointers_in_each_node_ii();
        Integer[] arr = new Integer[]{1, 2, 3, 4, null, null, 5};
        Node root = Node.createBinaryTreeByArray(arr, 0);
        Node newRoot = c.connect(root);
        System.out.println("done");
    }
}