package leetcode.algorithm.trie;

public class Code208_implement_trie_prefix_tree {

	static final int ALPHABET_SIZE = 26;
	TrieNode root;

	class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		public boolean isEndOfWord = false;

		// String item = "";
		TrieNode() {

		}
	}
	// Trie[] children = new Trie[ALPHABET_SIZE];

	/** Initialize your data structure here. */
	public Code208_implement_trie_prefix_tree() {
//		for (int i = 0; i < ALPHABET_SIZE; i++) {
//			children[i] = null;
//		}
		root = new TrieNode();
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
				ptr[index] = new TrieNode();
			}
			lastTrie = ptr[index];
			ptr = ptr[index].children;
		}
		lastTrie.isEndOfWord = true;
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

	public static void main(String[] args) {
		Code208_implement_trie_prefix_tree trie = new Code208_implement_trie_prefix_tree();
		trie.insert("apple");
		System.out.println(trie.search("apple")); // 返回 true
		System.out.println(trie.search("app")); // 返回 false
		System.out.println(trie.startsWith("app")); // 返回 true
		trie.insert("app");
		System.out.println(trie.search("app")); // 返回 true
	}
}
