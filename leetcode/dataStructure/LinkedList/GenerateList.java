package leetcode.dataStructure.LinkedList;

public class GenerateList {

    public static ListNode generate(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode pre = head;
        int size = array.length;
        for (int i = 1; i < size; i++) {
            ListNode cur = new ListNode(array[i]);
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public static Node generate(Integer[][] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Node head = new Node(array[0][0]);
        Node pre = head;
        int size = array.length;

        Node[] nodeArray = new Node[size];
        nodeArray[0] = head;

        for (int i = 1; i < size; i++) {
            Node cur = new Node(array[i][0]);
            pre.next = cur;
            pre = cur;
            nodeArray[i] = cur;
        }

        for (int i = 0; i < size; i++) {
            Integer randomIndex = array[i][1];
            nodeArray[i].random = randomIndex == null ? null : nodeArray[randomIndex];
        }

        return head;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3};

        ListNode r = generate(array);
        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
