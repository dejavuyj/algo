package leetcode.dataStructure.stackAndQueue;

import java.util.Stack;

public class Code71_simplify_path {

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] arr = path.split("/");
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
        	switch (s) {
				case "..":
					if (stack.size() > 0) {
						stack.pop();
					}
					break;
				case ".":
				case "":
					break;
				default:
					stack.push(s);
					break;
			}
        }
        if (stack.empty()) {
        	return "/";
		}

        arr = stack.toArray(new String[0]);

		for (String s : arr) {
			sb.append("/").append(s);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String path = "/a/./b/../../c/";
        Code71_simplify_path c = new Code71_simplify_path();
        System.out.println(c.simplifyPath(path));
    }
}
