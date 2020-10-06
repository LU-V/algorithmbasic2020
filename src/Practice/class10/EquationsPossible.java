package Practice.class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 *
 */
public class EquationsPossible {

  public boolean equationsPossible(String[] equations) {
    List<Character> list = new ArrayList<>();
    for (String e : equations) {
      list.add(e.toCharArray()[0]);
      list.add(e.toCharArray()[3]);
    }
    // 0 4 == 合并
    // 0 4 != 是否在一个集合 在的话false 不在继续
    // ["c==c","b==d","x!=z"]
    UnionSet<Character> unionSet = new UnionSet(list);
    int length = equations.length;
    for (int i = 0; i < length; i++) {
      char c[] = equations[i].toCharArray();
      if (c[1] == '=' && c[2] == '=') {
        unionSet.union(c[0], c[3]);
      }
    }
    boolean res = true;
    for (int i = 0; i < length; i++) {
      char c[] = equations[i].toCharArray();
      if (c[1] == '!' && c[2] == '=') {
        res = unionSet.isSameSet(c[0], c[3]);
        if (res) {
          return !res;
        }
      }
    }
    return true;
  }

  static class Node<V> {

    V val;

    public Node(V val) {
      this.val = val;
    }
  }

  static class UnionSet<V> {

    private HashMap<V, Node<V>> nodes = new HashMap<>();
    private HashMap<Node<V>, Node<V>> parent = new HashMap<>();
    private HashMap<Node<V>, Integer> sizeMap = new HashMap<>();

    public UnionSet(List<V> list) {
      list.forEach(l -> {
        if (!nodes.containsKey(l)) {
          nodes.put(l, new Node<>(l));
        }
        parent.put(nodes.get(l), nodes.get(l));
        sizeMap.put(nodes.get(l), 1);
      });
    }

    Node<V> findFather(Node<V> a) {
      Stack<Node<V>> stack = new Stack<>();
      while (a != parent.get(a)) {
        stack.push(a);
        a = parent.get(a);
      }
      while (!stack.isEmpty()) {
        parent.put(stack.pop(), a);
      }
      return a;
    }

    public void union(V a, V b) {
      Node<V> nodeA = nodes.get(a);
      Node<V> nodeB = nodes.get(b);

      //Node<V> parentA = parent.get(nodeA);
      //Node<V> parentB = parent.get(nodeB);
      Node<V> parentA = findFather(nodeA);
      Node<V> parentB = findFather(nodeB);

      if (parentA == parentB) {
        return;
      }
      int sizeA = sizeMap.get(nodeB);
      int sizeB = sizeMap.get(nodeB);

      Node<V> small = sizeA > sizeB ? parentB : parentA;
      Node<V> big = sizeA > sizeB ? parentA : parentB;

      parent.put(small, big);
      sizeMap.put(big, sizeA + sizeB);
      //sizeMap.remove(small);
    }

    boolean isSameSet(V a, V b) {
      if (!parent.containsKey(nodes.get(a)) || !parent.containsKey(nodes.get(b))) {
        return false;
      }
      return findFather(nodes.get(a)) == findFather(nodes.get(b));
    }
  }

  public static void main(String[] args) {
    EquationsPossible equationsPossible = new EquationsPossible();
    String[] ss6 = {"f==a", "a==b", "f!=e", "a==c", "b==e", "c==f"};
    System.out.println(equationsPossible.equationsPossible(ss6));

    String[] ss5 = {"e!=c", "b!=b", "b!=a", "e==d"};
    System.out.println(equationsPossible.equationsPossible(ss5));

    String[] ss = {"c==c", "b==d", "x!=z"};
    //
    //
    System.out.println(equationsPossible.equationsPossible(ss));

    String[] ss1 = {"a==b", "b!=c", "c==a"};
    // a==b union
    // b!=c isSameSet [a,b][c]
    // c==a 要求yiset
    System.out.println(equationsPossible.equationsPossible(ss1));

    String[] ss2 = {"a==b", "c==a", "b!=c"};
    // [a,b,c]
    // 满足 b!=c 要求  不一set
    System.out.println(equationsPossible.equationsPossible(ss2));

    String[] ss3 = {"c==c", "b==d", "x!=z"};
    System.out.println(equationsPossible.equationsPossible(ss3));

    String[] ss4 = {"b==a", "a==b"};
    System.out.println(equationsPossible.equationsPossible(ss4));
  }
}