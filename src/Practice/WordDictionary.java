package Practice;

import java.util.HashSet;
import java.util.Set;

public class WordDictionary {

  class TrieNode {

    TrieNode[] nexts;
    int pass;
    int end;
    char c;

    public TrieNode() {
      nexts = new TrieNode[26];
      pass = 0;
      end = 0;
      c = '*';
    }
  }

  TrieNode root = new TrieNode();

  /**
   * Adds a word into the data structure.
   */
  public void addWord(String word) {
    if (word == null) {
      return;
    }
    char[] ch = word.toCharArray();
    int path = 0;
    TrieNode node = root;
    for (int i = 0; i < ch.length; i++) {
      path = ch[i] - 'a';
      if (node.nexts[path] == null) {
        node.nexts[path] = new TrieNode();
      }
      node = node.nexts[path];
      node.pass++;
      node.c=ch[i];
    }
    node.end++;
  }

  /**
   * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one
   * letter.
   */
  public boolean search(String word) {
    if (word == null) {
      return false;
    }
    return search(word, root);
  }

  public boolean search(String word, TrieNode node) {
    if (word == null) {
      return false;
    }
    int path = 0;

    char[] ch = word.toCharArray();
    for (int i = 0; i < ch.length; i++) {
      if (ch[i]!='.'){
        path = ch[i] - 'a';
        if (node.nexts[path] == null) {
          return false;
        }
        node = node.nexts[path];
      }else if (ch[i] == '.') {
        for (int j = 0;j<26;j++ ){
          if (node.nexts[j]!=null){
            if(search(word.substring(i+1),node.nexts[j])){
              return true;
            }
          }
        }
        return false;

      }
    }
    return node.end>0;
  }


  public static void main(String[] args) {
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("abcde");
    wordDictionary.addWord("acc");
    wordDictionary.addWord("cbc");
    System.out.println(wordDictionary.search("ab..."));
    System.out.println(wordDictionary.search("a..."));
    System.out.println(wordDictionary.search("cbc"));
    wordDictionary.addWord("a");
    ;
    System.out.println(wordDictionary.search("a."));

  }
}

