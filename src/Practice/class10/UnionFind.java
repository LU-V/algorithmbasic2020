package Practice.class10;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author tiger
 */
public class UnionFind {

  public static class Node<V> {

    V val;

    public Node(V v) {
      val = v;
    }
  }

  public static class UnionSet<V> {

    private Map<V, Node<V>> nodes;
    private Map<Node<V>, Node<V>> parents;
    private Map<Node<V>, Integer> sizeMap;

    public void initialize(List<V> list) {
      list.stream().forEach(l -> {
        nodes.put(l, new Node(l));
        parents.put(new Node<>(l), new Node<>(l));
        sizeMap.put(new Node<>(l), 1);
      });
    }

    // union
    public void union(V a, V b) {
      // same parent
      if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
        return;
      }
      // different parent
      //1. get parent
      Node<V> parentA = getParent(nodes.get(a));
      Node<V> parentB = getParent(nodes.get(b));
      // 2.compared parent`s size
      // if a>b
      if (sizeMap.get(parentA).compareTo(sizeMap.get(parentB)) > 0) {
        Integer sizeA = sizeMap.get(parentA);
        Integer sizeB = sizeMap.get(parentB);
        Node<V> small = sizeA > sizeB ? parentB : parentA;
        Node<V> big = sizeA > sizeB ? parentA : parentB;
        // 3.union
        // change parent
        parents.put(small, big);
        // modify the big one
        sizeMap.put(big, sizeA + sizeB);
        // remove small node
        sizeMap.remove(small);
      }
    }

    public boolean isSameSet(V a, V b) {
      if (parents.containsKey(new Node<>(a)) || parents.containsKey(new Node<>(b))) {
        return false;
      }
      return getParent(nodes.get(a)) == getParent(nodes.get(b));
    }

    public Node<V> getParent(Node<V> n) {
      Stack<Node<V>> stack = new Stack<>();

      while (n != parents.get(n)) {
        stack.push(n);
        n = parents.get(n);
      }

      // optimize
      while (!stack.isEmpty()) {
        parents.put(stack.pop(), n);
      }

      return n;
    }
  }
}