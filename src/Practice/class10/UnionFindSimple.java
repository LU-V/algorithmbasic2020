package Practice.class10;

/**
 * 简化的并查集
 */

public class UnionFindSimple {

  // 定义并查集
  class UnionSearchSet {

    private int[] roots;

    // 初始化时，每个元素单独为一个集合
    UnionSearchSet(int n) {
      roots = new int[n];
      for (int i = 0; i < n; ++i) {
        roots[i] = i;
      }
    }

    // 合并
    void union(int ele1, int ele2) {
      int x = find(ele1);
      int y = find(ele2);
      if (x != y) { // 不是同一个集合就合并
        roots[x] = y; // 相当于x的父亲是y
      }
    }

    // 查找
    int find(int element) {
      while (element != roots[element]) {
        element = roots[element];
      }
      return element;
    }

    // 统计集合个数
    public int countSetNumber() {
      int count = 0;
      for (int i = 0; i < roots.length; ++i) {
        if (roots[i] == i) {
          ++count;
        }
      }
      return count;
    }
  }
  //public boolean equationsPossible(String[] equations) {
  //  UnionSearchSet unionSearchSet = new UnionSearchSet(26);
  //  for (String e : equations) {
  //    char[] c = e.toCharArray();
  //    if (c[1] == '=' && c[2] == '=') {
  //      unionSearchSet.union(c[0] - 'a', c[3] - 'a');
  //    }
  //  }
  //  for (String e : equations) {
  //    char[] c = e.toCharArray();
  //    if (c[1] == '!' && c[2] == '=' && unionSearchSet.find(c[0]-'a')==unionSearchSet.find(c[3]-'a')) {
  //      return false;
  //    }
  //  }
  //  return true;
  //}
  //public static void main(String[] args) {
  //
  //
  //  UnionFindSimple equationsPossible = new UnionFindSimple();
  //
  //  // false
  //  String[] ss7 = {"a==b","e==c","b==c","a!=e"};
  //  System.out.println(equationsPossible.equationsPossible(ss7));
  //  //
  //  String[] ss6 = {"f==a", "a==b", "f!=e", "a==c", "b==e", "c==f"};
  //  System.out.println(equationsPossible.equationsPossible(ss6));
  //
  //  String[] ss5 = {"e!=c", "b!=b", "b!=a", "e==d"};
  //  System.out.println(equationsPossible.equationsPossible(ss5));
  //
  //  String[] ss = {"c==c", "b==d", "x!=z"};
  //  //
  //  //
  //  System.out.println(equationsPossible.equationsPossible(ss));
  //
  //  String[] ss1 = {"a==b", "b!=c", "c==a"};
  //  // a==b union
  //  // b!=c isSameSet [a,b][c]
  //  // c==a 要求yiset
  //  System.out.println(equationsPossible.equationsPossible(ss1));
  //
  //  String[] ss2 = {"a==b", "c==a", "b!=c"};
  //  // [a,b,c]
  //  // 满足 b!=c 要求  不一set
  //  System.out.println(equationsPossible.equationsPossible(ss2));
  //
  //  String[] ss3 = {"c==c", "b==d", "x!=z"};
  //  System.out.println(equationsPossible.equationsPossible(ss3));
  //
  //  String[] ss4 = {"b==a", "a==b"};
  //  System.out.println(equationsPossible.equationsPossible(ss4));
  //}
}