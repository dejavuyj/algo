package leetcode.dataStructure.stackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Code20_valid_parentheses {

    public static boolean isValid(String s) {
    	Stack<String> leftStack = new Stack<String>();
        for(int i=0; i<s.length(); i++) {
        	char c1 = s.charAt(i);
        	String c = String.valueOf(c1);
        	if("(".equals(c) || "[".equals(c) || "{".equals(c)) {
        		leftStack.push(c);
        	} else {
        		if(leftStack.empty()) {
        			return false;
        		}
        		if(")".equals(c)) {
        			if(!"(".equals(leftStack.pop())) {
        				return false;
        			}
        		} else if("]".equals(c)) {
        			if(!"[".equals(leftStack.pop())) {
        				return false;
        			}
        		} else if("}".equals(c)) {
        			if(!"{".equals(leftStack.pop())) {
        				return false;
        			}
        		}
        	}
        }
		if(!leftStack.empty()) {
			return false;
		}
        return true;
    }

    public static boolean isValid2(String s) {
    	Stack<Character> leftStack = new Stack<Character>();
    	Map<Character,Character> m = new HashMap<Character,Character>();
    	m.put(')', '(');
    	m.put(']', '[');
    	m.put('}', '{');
        for(int i=0; i<s.length(); i++) {
        	char c = s.charAt(i);
        	if(!m.containsKey(c)) {
        		leftStack.push(c);
        	} else {
        		if(leftStack.empty() || !m.get(c).equals(leftStack.pop())) {
        			return false;
        		}
        	}
        }
        return leftStack.empty();
    }

    public static void main(String[] args) {
    	String s = "{[]}";
    	System.out.println(isValid2(s));
    }
}
