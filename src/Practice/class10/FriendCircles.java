package Practice.class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class FriendCircles {

  public static class Node<V> {

    V val;

    public Node(V v) {
      val = v;
    }
  }

  public static class UnionSet<V> {

    private Map<V, Node<V>> nodes = new HashMap<>();
    private Map<Node<V>, Node<V>> parents = new HashMap<>();
    private Map<Node<V>, Integer> sizeMap = new HashMap<>();

    public UnionSet(List<V> list) {
      list.stream().forEach(l -> {
        nodes.put(l, new Node(l));
        parents.put(nodes.get(l), nodes.get(l));
        sizeMap.put(nodes.get(l), 1);
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
      if (parentA==parentB){
        return;
      }
      // 2.compared parent`s size
      // if a>b
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

  public static Integer findCircleNum(int[][] matrix) {

    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < matrix.length; i++) {
      list.add(i);
    }
    UnionSet unionSet = new UnionSet(list);
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (matrix[i][j] == 1) {
          unionSet.union(i, j);
        }
      }
    }
    return unionSet.sizeMap.size();
  }

  public static void main(String[] args) {
    //int matrix[][] = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    //System.out.println(countFriendCircles(matrix));

    //int matrix2[][] = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
    //System.out.println(countFriendCircles(matrix2));

    //int matrix3[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    //System.out.println(findCircleNum(matrix3));

    //[[1, 1, 1], [1, 1, 1], [1, 1, 1]]
    int matrix4[][] = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    System.out.println(findCircleNum(matrix4));

    //[[0]]
    //int matrix5[][] = {{0}};
    //System.out.println(findCircleNum(matrix5));
    //
    //[[1]]
    //int matrix6[][] = {{1}};
    //System.out.println(findCircleNum(matrix6));

    // [[1,1,0,0,0,0],[1,1,0,1,0,0],[0,0,1,1,0,0],[0,1,1,1,0,1],[0,0,0,0,1,0],[0,0,0,1,0,1]]
    int matrix7[][] =
        {{1, 1, 0, 0, 0, 0}, {1, 1, 0, 1, 0, 0}, {0, 0, 1, 1, 0, 0}, {0, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1}};
    System.out.println(findCircleNum(matrix7));

    int matrix8[][] =
        {{1, 1, 0, 0, 0, 0}, {1, 1, 0, 1, 0, 0}, {0, 0, 1, 1, 0, 0}, {0, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1}};
    System.out.println(findCircleNum(matrix7));
  }


}