package leetcode.dataStructure.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Code297_serialize_and_deserialize_binary_tree {

    static public class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] dataArray = data.split(",");
            List<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
            return rdeserialize(dataList);
        }

        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "None,";
            } else {
                str += root.val + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        public TreeNode rdeserialize(List<String> dataList) {
            if (dataList.get(0).equals("None")) {
                dataList.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
            dataList.remove(0);
            root.left = rdeserialize(dataList);
            root.right = rdeserialize(dataList);

            return root;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, null, null, 4, 5};
        TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);

        Codec c = new Codec();
        String str = c.serialize(root);
        System.out.println(str);

        TreeNode root2 = c.deserialize(str);
        System.out.println(root);
    }
}