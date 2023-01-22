package leetcode.dataStructure.LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code138_copy_list_with_random_pointer {

    Map<Node, Node> cachedNode = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cachedNode.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        Node newHead = new Node(head.val);
        Node cur = head.next;

        List<Node> newArray = new ArrayList<>();
        newArray.add(newHead);
        Node pre = newHead;

        Map<Node, Integer> originMap = new HashMap<>();
        originMap.put(head, 0);

        int index = 1;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newArray.add(newNode);
            pre.next = newNode;
            originMap.put(cur, index++);

            cur = cur.next;
            pre = newNode;
        }

        cur = head;
        index = 0;
        while (cur != null) {
            Node originRandom = cur.random;
            if (originRandom == null) {
                newArray.get(index).random = null;
            } else {
                newArray.get(index).random = newArray.get(originMap.get(originRandom));
            }

            cur = cur.next;
            index++;
        }

        return newHead;
    }

    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = new Node(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = node.next;
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }

    public static void main(String[] args) {
        Code138_copy_list_with_random_pointer c = new Code138_copy_list_with_random_pointer();
        Integer[][] array = {{7, null}, {13, 0}, {11, 4}, {10, 2}, {1, 0}};
        Node l = GenerateList.generate(array);

        Node r = c.copyRandomList(l);
        while (r != null) {
            System.out.print(r.val + ", ");
            System.out.println(r.random == null ? null : r.random.val);
            r = r.next;
        }
    }
}
