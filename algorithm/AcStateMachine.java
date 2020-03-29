package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// AC自动机 多模式串匹配
public class AcStateMachine {

	static final int ALPHABET_SIZE = 26;
	AcNode root;

	class AcNode {
		char data;
		AcNode[] children = new AcNode[ALPHABET_SIZE];
		public boolean isEndingChar = false;
		public int length = -1; // 当isEndingChar=true时,记录模式串长度
		public AcNode fail; // 失败指针
		String word;

		AcNode(char c) {
			this.data = c;
		}
	}

	/** Initialize your data structure here. */
	public AcStateMachine() {
		root = new AcNode(' ');
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		char[] ca = word.toCharArray();
		AcNode node = this.root;
		AcNode[] ptr = node.children;
		int index = 0;
		AcNode lastTrie = null;
		for (char c : ca) {
			index = c - 'a';
			if (ptr[index] == null) {
				ptr[index] = new AcNode(c);
			}
			lastTrie = ptr[index];
			ptr = ptr[index].children;
		}
		lastTrie.isEndingChar = true;
		lastTrie.word = word;
		lastTrie.length = word.length();
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		char[] ca = word.toCharArray();
		AcNode node = this.root;
		AcNode[] ptr = node.children;
		int index = 0;
		AcNode lastTrie = null;
		for (char c : ca) {
			index = c - 'a';
			if (ptr[index] == null) {
				return false;
			}
			lastTrie = ptr[index];
			ptr = ptr[index].children;
		}
		return lastTrie.isEndingChar;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given
	 * prefix.
	 */
	public boolean startsWith(String prefix) {
		char[] ca = prefix.toCharArray();
		AcNode node = this.root;
		AcNode[] ptr = node.children;
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

	// 返回以prefix开头的words
	public List<String> getWordsWithPrefix(String prefix) {
		List<String> l = new ArrayList<String>();
		AcNode[] ptr = root.children;
		int index = 0;
		for (int i = 0; i < prefix.length(); i++) {
			index = prefix.charAt(i) - 'a';
			if (ptr[index] == null) {
				return l;
			}
			ptr = ptr[index].children;
		}
		Queue<AcNode> queue = new LinkedList<AcNode>();
		processChildren(l, ptr, queue);
		while (!queue.isEmpty()) {
			AcNode node = queue.poll();
			ptr = node.children;
			processChildren(l, ptr, queue);
		}
		return l;
	}

	private void processChildren(List<String> l, AcNode[] ptr, Queue<AcNode> queue) {
		for (AcNode node : ptr) {
			if (node != null) {
				queue.add(node);
				if (node.isEndingChar) {
					l.add(node.word);
				}
			}
		}
	}

	public void buildFailurePointer() {
		Queue<AcNode> queue = new LinkedList<>();
		root.fail = null;
		queue.add(root);
		while (!queue.isEmpty()) {
			AcNode p = queue.remove();
			for (int i = 0; i < 26; ++i) {
				AcNode pc = p.children[i];
				if (pc == null)
					continue;
				if (p == root) {
					pc.fail = root;
				} else {
					AcNode q = p.fail;
					while (q != null) {
						AcNode qc = q.children[pc.data - 'a'];
						if (qc != null) {
							pc.fail = qc;
							break;
						}
						q = q.fail;
					}
					if (q == null) {
						pc.fail = root;
					}
				}
				queue.add(pc);
			}
		}
	}

	public void match(char[] text) { // text是主串
		int n = text.length;
		AcNode p = root;
		for (int i = 0; i < n; ++i) {
			int idx = text[i] - 'a';
			while (p.children[idx] == null && p != root) {
				p = p.fail; // 失败指针发挥作用的地方
			}
			p = p.children[idx];
			if (p == null)
				p = root; // 如果没有匹配的，从root开始重新匹配
			AcNode tmp = p;
			while (tmp != root) { // 打印出可以匹配的模式串
				if (tmp.isEndingChar == true) {
					int pos = i - tmp.length + 1;
					System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
				}
				tmp = tmp.fail;
			}
		}
	}

	// 将关键词替换成*
	public void replaceMatchs(char[] text) { // text是主串
		int n = text.length;
		AcNode p = root;
		for (int i = 0; i < n; ++i) {
			int idx = text[i] - 'a';
			while (p.children[idx] == null && p != root) {
				p = p.fail; // 失败指针发挥作用的地方
			}
			p = p.children[idx];
			if (p == null)
				p = root; // 如果没有匹配的，从root开始重新匹配
			AcNode tmp = p;
			while (tmp != root) { // 打印出可以匹配的模式串
				if (tmp.isEndingChar == true) {
					int pos = i - tmp.length + 1;
					System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
					for (int j = pos; j <= i; j++) {
						text[j] = '*';
					}
				}
				tmp = tmp.fail;
			}
		}

		String filtered = new String(text);
		//String filtered = new String(text).replaceAll("^a{2,9}$", "");
		System.out.println(filtered);
	}

	public static void main(String[] args) {
		AcStateMachine c = new AcStateMachine();
		c.insert("al");
		c.insert("also");
		c.insert("apple");
		c.insert("apk");
		c.insert("app");
		c.insert("delta");
		c.insert("program");
		c.insert("see");
		// System.out.println(trie.search("apple")); // 返回 true
		// System.out.println(trie.search("app")); // 返回 false
		// System.out.println(trie.startsWith("app")); // 返回 true
		// trie.insert("app");
		// System.out.println(trie.search("app")); // 返回 true
		// List<String> l = trie.getWordsWithPrefix("a");
		// for (String s : l) {
		// System.out.println(s);
		// }
		c.buildFailurePointer();
		// c.match("alalsoddedeltaxseeapk".toCharArray());
		c.replaceMatchs("alalsoddeeflsldeltaxseeapkslelsl".toCharArray());
	}
}
