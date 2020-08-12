//public class Trie {
//
//  private int end;
//
//  private int pass;
//
//  private Tire[] nexts;
//  /** Initialize your data structure here. */
//
//  public Tire(){
//    end = 0;
//    pass = 0;
//    nexts = new Tire[26];
//  }
//
//
//  private static Tire root = new Tire();
//
//  /** Inserts a word into the trie. */
//  public void insert(String word) {
//    if (word == null){
//      return;
//    }
//    char ch[] = word.toCharArray();
//    Tire node = root;
//    int path = 0;
//    for (int i = 0; i < ch.length; i++) {
//      path = ch[i] - 'a';
//      if (node.nexts[path] == null) {
//        node.nexts[path] = new Tire();
//      }
//      node = node.nexts[path];
//      node.pass++;
//    }
//    node.end++;
//  }
//
//  /** Returns if the word is in the trie. */
//  public boolean search(String word) {
//    if (word == null){
//      return false;
//    }
//    Tire node = root;
//    char ch[] = word.toCharArray();
//    int path = 0;
//    for (int i = 0;i<ch.length;i++){
//      path = ch[i]-'a';
//      if (node.nexts[path]==null){
//        return false;
//      }
//      node = node.nexts[path];
//    }
//
//    return node.end>0;
//  }
//
//  /** Returns if there is any word in the trie that starts with the given prefix. */
//  public boolean startsWith(String prefix) {
//    if (prefix == null) {
//      return false;
//    }
//    Tire node = root;
//    char ch[] = prefix.toCharArray();
//    int path = 0;
//    for (int i = 0; i < ch.length; i++) {
//      path = ch[i] - 'a';
//      if (node.nexts[path] == null ) {
//        return false;
//      }
//      node = node.nexts[path];
//    }
//    return true;
//  }
//}
//
///**
// * Your Trie object will be instantiated and called as such:
// * Trie obj = new Trie();
// * obj.insert(word);
// * boolean param_2 = obj.search(word);
// * boolean param_3 = obj.startsWith(prefix);
// */