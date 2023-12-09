package leetcode.algorithm.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 前缀树, 字典树
 */
public class Code208_implement_trie_prefix_tree {

	static final int ALPHABET_SIZE = 26;
	TrieNode root;

	class TrieNode {
		char data;
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		public boolean isEndOfWord = false;

		String word;

		TrieNode(char c) {
			this.data = c;
		}
	}

	/** Initialize your data structure here. */
	public Code208_implement_trie_prefix_tree() {
		root = new TrieNode(' ');
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		char[] ca = word.toCharArray();
		TrieNode node = this.root;
		TrieNode[] ptr = node.children;
		int index = 0;
		TrieNode lastTrie = null;
		for (char c : ca) {
			index = c - 'a';
			if (ptr[index] == null) {
				ptr[index] = new TrieNode(c);
			}
			lastTrie = ptr[index];
			ptr = ptr[index].children;
		}
		lastTrie.isEndOfWord = true;
		lastTrie.word = word;
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		char[] ca = word.toCharArray();
		TrieNode node = this.root;
		TrieNode[] ptr = node.children;
		int index = 0;
		TrieNode lastTrie = null;
		for (char c : ca) {
			index = c - 'a';
			if (ptr[index] == null) {
				return false;
			}
			lastTrie = ptr[index];
			ptr = ptr[index].children;
		}
		return lastTrie.isEndOfWord;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given
	 * prefix.
	 */
	public boolean startsWith(String prefix) {
		char[] ca = prefix.toCharArray();
		TrieNode node = this.root;
		TrieNode[] ptr = node.children;
		int index = 0;
		for (char c : ca) {
			index = c - 'a';
			if (ptr[index] == null) {
				return false;
			}
			ptr = ptr[index].children;
		}
		return true;
	}

	// 前缀提示功能,返回以prefix开头的words
	public List<String> getWordsWithPrefix(String prefix) {
		List<String> l = new ArrayList<String>();
		TrieNode[] ptr = root.children;
		int index = 0;
		for (int i = 0; i < prefix.length(); i++) {
			index = prefix.charAt(i) - 'a';
			if (ptr[index] == null) {
				return l;
			}
			ptr = ptr[index].children;
		}
		Queue<TrieNode> queue = new LinkedList<TrieNode>();
		processChildren(l, ptr, queue);
		while (!queue.isEmpty()) {
			TrieNode node = queue.poll();
			ptr = node.children;
			processChildren(l, ptr, queue);
		}
		return l;
	}

	private void processChildren(List<String> l, TrieNode[] ptr, Queue<TrieNode> queue) {
		for (TrieNode node : ptr) {
			if (node != null) {
				queue.add(node);
				if (node.isEndOfWord) {
					l.add(node.word);
				}
			}
		}
	}

	public static void main(String[] args) {
		Code208_implement_trie_prefix_tree trie = new Code208_implement_trie_prefix_tree();
		trie.insert("al");
		trie.insert("also");
		trie.insert("apple");
		trie.insert("apk");
		trie.insert("app");
		trie.insert("delta");
		trie.insert("program");
		trie.insert("see");
		// System.out.println(trie.search("apple")); // 返回 true
		// System.out.println(trie.search("app")); // 返回 false
		// System.out.println(trie.startsWith("app")); // 返回 true
		// trie.insert("app");
		// System.out.println(trie.search("app")); // 返回 true
		List<String> l = trie.getWordsWithPrefix("a");
		for (String s : l) {
			System.out.println(s);
		}
	}
}
