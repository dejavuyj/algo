package leetcode.algorithm.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code212_word_search_ii {

	static final int ALPHABET_SIZE = 26;

	class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		String val;
		public boolean isEndOfWord = false;
	}

	class Trie {
		TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			char[] ca = word.toCharArray();
			TrieNode node = this.root;
			TrieNode[] ptr = node.children;
			int index = 0;
			TrieNode lastTrie = null;
			for (char c : ca) {
				index = c - 'a';
				if (ptr[index] == null) {
					ptr[index] = new TrieNode();
				}
				lastTrie = ptr[index];
				ptr = ptr[index].children;
			}
			lastTrie.isEndOfWord = true;
			lastTrie.val = word;
		}
	}

	public List<String> findWords(char[][] board, String[] words) {
		Trie trie = new Trie();
		TrieNode root = trie.root;

		for (String word : words) {
			trie.insert(word);
		}
		Set<String> result = new HashSet<String>();
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(root.children[board[i][j] - 'a'] != null) {					
					find(board, visited, i, j, m, n, result, root);
				}
			}
		}
		return new ArrayList<>(result);
	}

	public void find(char[][] board, boolean[][] visited, int i, int j, int m, int n, Set<String> result,
			TrieNode cur) {
		if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
			return;
		}
		cur = cur.children[board[i][j] - 'a'];
		if (cur == null) {
			return;
		}
		visited[i][j] = true;
		if (cur.isEndOfWord) {
			result.add(cur.val);
		}
		find(board, visited, i + 1, j, m, n, result, cur);
		find(board, visited, i, j + 1, m, n, result, cur);
		find(board, visited, i, j - 1, m, n, result, cur);
		find(board, visited, i - 1, j, m, n, result, cur);

		visited[i][j] = false;
	}

	public static void main(String[] args) {
		Code212_word_search_ii code = new Code212_word_search_ii();
		char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
				{ 'i', 'f', 'l', 'v' } };
		String[] words = { "oath", "pea", "eat", "rain" };
		long t1 = System.nanoTime();
		List<String> ans = code.findWords(board, words);
		long t2 = System.nanoTime();
		System.out.println(t2-t1);
		for (String s : ans) {
			System.out.println(s);
		}
	}
}
