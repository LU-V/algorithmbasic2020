//public class TrieTree {
//
//
//  private class Node{
//    private int end;
//
//    private int pass;
//
//    private Node[] nexts;
//
//    public Node(){
//      end = 0;
//      pass = 0;
//      nexts = new Node[26];
//    }
//  }
//
//
//
//
//  private Node root = new Node();
//
//  public void insert(String word){
//    if (word == null){
//      return;
//    }
//    char ch[] = word.toCharArray();
//    Node node = root;
//    int path = 0;
//    for (int i = 0; i < ch.length; i++) {
//      path = ch[i] - 'a';
//      if (node.nexts[path] == null) {
//        node.nexts[path] = new Node();
//      }
//      node = node.nexts[path];
//      node.pass++;
//    }
//      node.end++;
//  }
//
//  public boolean search(String word){
//    if (word == null){
//      return false;
//    }
//    Node node = root;
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
//  public boolean startWith(String prefix) {
//    if (prefix == null) {
//      return false;
//    }
//    Node node = root;
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
//
//
//
//}