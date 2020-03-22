package leetcode.dataStructure.stackAndQueue;

import java.util.Stack;

public class Code232_implement_queue_using_stacks {

	Stack<Integer> in = new Stack<Integer>();
	Stack<Integer> out = new Stack<Integer>();
	int front;

    /** Initialize your data structure here. */
    public Code232_implement_queue_using_stacks() {
//        in = new Stack<Integer>();
//        out = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
//        while(!out.empty()) {
//        	in.push(out.pop());
//        }
    	if(in.empty()) {
    		front = x;
    	}
        in.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	if(out.empty()) {
            while(!in.empty()) {
            	out.push(in.pop());
            }	
    	}
        return out.pop();
    }
    
    /** Get the front element. */
    public int peek() {
//        while(!in.empty()) {
//        	out.push(in.pop());
//        }
    	if(!out.empty()) {
    		return out.peek();
    	}
        return front;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(in.empty() && out.empty()) {
        	return true;
        }
        return false;
    }

	public static void main(String[] args) {
		Code232_implement_queue_using_stacks q = new Code232_implement_queue_using_stacks();
		q.push(1);
		q.push(2);
		q.push(3);
//		System.out.println(q.peek());
		System.out.println(q.pop());

		while(!q.empty()) {
			System.out.println(q.pop());
		}
	}
}
