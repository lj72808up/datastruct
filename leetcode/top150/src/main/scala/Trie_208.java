/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *      Trie trie = new Trie();
 *
 *      trie.insert("apple");
 *      trie.search("apple");   // 返回 true
 *      trie.search("app");     // 返回 false
 *      trie.startsWith("app"); // 返回 true
 *      trie.insert("app");
 *      trie.search("app");     // 返回 true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 说明:
 *
 *      你可以假设所有的输入都是由小写字母 a-z 构成的。
 *      保证所有输入均为非空字符串。
 */
public class Trie_208 {
    private TrieNode root = new TrieNode();

    /** Initialize your data structure here. */
    public Trie_208() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode n = this.root;
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            TrieNode next = n.getNext(chs[i]);
            if(next==null)
                next = new TrieNode(chs[i]);
                n.insertNext(next);
            n = next;
        }
        n.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode n = this.root;
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            TrieNode next = n.getNext(chs[i]);
            if(next!=null){
                n = next;
            }else{
                return false;
            }
        }
        return n.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode n = this.root;
        char[] chs = prefix.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            TrieNode next = n.getNext(chs[i]);
            if(next!=null){
                n = next;
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie_208 trie = new Trie_208();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
    }
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
class TrieNode{
    private char name = ' ';
    private static int R = 26;   // 字母表长度为26
    private TrieNode[] nexts = new TrieNode[R];
    protected boolean isEnd = false;

    public TrieNode(char name) {
        this.name = name;
    }

    public TrieNode() {}

    public void insertNext(TrieNode node) {
        char nextName = node.name;
        this.nexts[nextName-'a'] = node;
    }

    public TrieNode getNext(char nextName){
        return this.nexts[nextName-'a'];
    }
}