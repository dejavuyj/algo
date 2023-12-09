package leetcode.algorithm.trie;

/**
 * 前缀树, 字典树
 */
public class Code211_design_add_and_search_words_data_structure {

    private Trie root;

    class Trie {
        Trie[] children;
        public boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        /**
         * addWords a word into the trie.
         */
        public void addWord(String word) {
            char[] ca = word.toCharArray();
            Trie node = this;
            Trie[] ptr = node.children;
            Trie lastTrie = null;
            for (char c : ca) {
                int index = c - 'a';
                if (ptr[index] == null) {
                    ptr[index] = new Trie();
                }
                lastTrie = ptr[index];
                ptr = ptr[index].children;
            }
            lastTrie.isEnd = true;
        }

        public Trie[] getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public Code211_design_add_and_search_words_data_structure() {
        root = new Trie();
    }

    public void addWord(String word) {
        root.addWord(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, Trie node) {
        if (index == word.length()) {
            return node.isEnd;
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            int childIndex = ch - 'a';
            Trie child = node.getChildren()[childIndex];
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        } else {
            for (int i = 0; i < 26; i++) {
                Trie child = node.getChildren()[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Code211_design_add_and_search_words_data_structure trie = new Code211_design_add_and_search_words_data_structure();
        trie.addWord("bad");
        trie.addWord("dad");
        trie.addWord("mad");

        System.out.println(trie.search("pad")); // 返回 false
        System.out.println(trie.search("bad")); // 返回 true
        System.out.println(trie.search(".ad")); // 返回 true
        System.out.println(trie.search("b..")); // 返回 true
    }
}
