package leetcode.dataStructure.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Code225_implement_stack_using_queues {

	Queue<Integer> q1 = new LinkedList<Integer>();
	Queue<Integer> q2 = new LinkedList<Integer>();
	int end;

    /** Initialize your data structure here. */
    public Code225_implement_stack_using_queues() {
    	
    }

    /** Push element x onto stack. */
    public void push(int x) {
    	if(!q2.isEmpty()) {
    		q2.add(x);
    	} else {
    		q1.add(x);
    	}
    	end = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    	int r = 0;
    	if(!q1.isEmpty()) {
            while(q1.size() > 1) {
            	if(q1.size() == 2) {
            		end = q1.peek();
            	}
            	q2.add(q1.poll());
            }
            r = q1.poll();
		} else if (!q2.isEmpty()) {
			while (q2.size() > 1) {
            	if(q2.size() == 2) {
            		end = q2.peek();
            	}
				q1.add(q2.poll());
			}
			r = q2.poll();
		}
        return r;
    }
    
    /** Get the top element. */
    public int top() {
        return end;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        if(q1.isEmpty() && q2.isEmpty()) {
        	return true;
        }
        return false;
    }

	public static void main(String[] args) {
		Code225_implement_stack_using_queues s = new Code225_implement_stack_using_queues();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.pop());
		System.out.println(s.top());
//		s.push(4);
//		s.push(5);

//		while(!s.empty()) {
//			System.out.println(s.pop());
//		}
	}
}
