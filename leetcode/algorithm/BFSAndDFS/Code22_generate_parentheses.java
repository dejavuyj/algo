package leetcode.algorithm.BFSAndDFS;

import java.util.ArrayList;
import java.util.List;

public class Code22_generate_parentheses {

	private void backTrack(List<String> ans, String cur, int open, int close, int max) {
		if(cur.length() == max*2) {
			ans.add(cur);
			return;
		}
		
		if(open < max) {
			backTrack(ans, cur+"(", open+1,close, max);
		}
		if(close < open) {
			backTrack(ans, cur+")", open, close+1, max);
		}
	}
	
	public List<String> generateParenthesis(int n) {
		List<String> ansList = new ArrayList<String>();
		backTrack(ansList, "", 0, 0, n);
		return ansList;
	}
	
	public static void main(String[] args) {
		Code22_generate_parentheses c = new Code22_generate_parentheses();
		List<String> l = c.generateParenthesis(3);
		for (String s : l) {
			System.out.println(s);
		}
	}
}
