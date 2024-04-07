package leetcode.dataStructure.LinkedList;

public class Code237_delete_node_in_a_linked_list {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        Code237_delete_node_in_a_linked_list c = new Code237_delete_node_in_a_linked_list();
        int[] array = {4, 5, 1, 9};
        ListNode l = GenerateList.generate(array);
        c.deleteNode(l.next.next);

        while(l != null) {
            System.out.println(l.val);
            l = l.next;
        }
    }
}
