package Practice.class10;

public class Solution {

  public int findCircleNum(int[][] M) {
    int N = M.length;
    UF uf = new UF(N);
    int res = 1;
    for (int i = 0; i < M.length; i++) {
      for (int j = i + 1; j < M[0].length; j++) {
        if (M[i][j] == 1) {
          uf.union(i, j);
        }
      }
    }
    res = uf.getCount();
    return res;
  }
}

class UF {

  private int[] id;
  private int count;
  private int[] size;

  public UF(int N) {
    count = N;
    id = new int[N];
    size = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      size[i] = 1;
    }
  }

  public int getCount() {
    return count;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public int find(int p) {
    if (p != id[p]) {
      id[p] = find(id[p]);
    }
    return id[p];
  }

  public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    if (pRoot == qRoot) {
      return;
    }
    if (size[pRoot] < size[qRoot]) {
      id[pRoot] = qRoot;
      size[qRoot] += size[pRoot];
    } else {
      id[qRoot] = pRoot;
      size[pRoot] += size[qRoot];
    }
    count--;
  }

  public static void main(String[] args) {
    Solution solution =new Solution();
    int matrix4[][] = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};

    System.out.println(solution.findCircleNum(matrix4));
  }
}