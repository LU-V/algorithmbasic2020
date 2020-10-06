package Practice.class7Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaxBalanceTree {

  static class TreeNode {

    TreeNode right;
    TreeNode left;
    int val;

    public TreeNode(int val) {
      this.val = val;
    }
  }

  public static class Info {

    int max;
    int min;
    int bstLen;
    boolean isBst;

    public Info(int max, int min, boolean is, int node) {
      this.max = max;
      this.min = min;
      this.isBst = is;
      this.bstLen = node;
    }
  }

  public static int maxBst(TreeNode node) {
    return go(node).bstLen;
  }

  private static Info go(TreeNode node) {
    if (node == null) {
      return null;
    }
    Info leftInfo = go(node.left);
    Info rightInfo = go(node.right);
    int max = node.val;
    int min = node.val;
    int maxBSTLen = 0;
    boolean isBst = false;
    if (leftInfo != null) {
      max = Math.max(leftInfo.max, max);
      min = Math.min(leftInfo.min, min);
    }

    if (rightInfo != null) {
      max = Math.max(rightInfo.max, max);
      min = Math.min(rightInfo.min, min);
    }

    if ((leftInfo == null ? true : leftInfo.isBst)
        && (rightInfo == null ? true : rightInfo.isBst)
        && (leftInfo == null ? true : leftInfo.max < node.val)
        && (rightInfo == null ? true : rightInfo.min > node.val)) {

      maxBSTLen = (leftInfo == null ? 0 : leftInfo.bstLen) + (rightInfo == null ? 0 : rightInfo.bstLen) + 1;
      isBst = true;
    } else {
      maxBSTLen = Math.max(leftInfo == null ? 0 : leftInfo.bstLen, rightInfo == null ? 0 : rightInfo.bstLen);
      isBst = false;
    }

    return new Info(max, min, isBst, maxBSTLen);
  }

  public static void main(String[] args) {
    //TreeNode node = new TreeNode(0);
    //node.left = new TreeNode(3);
    //node.right = new TreeNode(7);
    //node.left.left = new TreeNode(2);
    //node.left.right = new TreeNode(4);
    //node.left.right.left = new TreeNode(6);
    //
    //node.right.right = new TreeNode(8);
    //node.right.right.right = new TreeNode(9);
    //System.out.println(maxBst(node));

    ArrayList<Info> list = new ArrayList<>(16);
    list.add(new Info(1,2,true,4));
    list.add(new Info(1,2,true,4));
    list.add(new Info(2,2,true,4));
    list.add(new Info(2,2,true,4));
    list.add(new Info(3,2,true,4));
    list.add(new Info(3,2,true,4));
    list.add(new Info(4,2,true,4));
    list.add(new Info(3,2,true,4));
    list.add(new Info(3,2,true,4));
    list.add(new Info(5,2,true,4));
    list.add(new Info(5,2,true,4));
    HashMap<Integer, List<Info>> map=  new HashMap<>(16);
    for (Info info : list){
      if (map.containsKey(info.max)){
        map.get(info.max).add(info);
        map.put(info.max,map.get(info.max));
      }else{
        ArrayList<Info> list2 = new ArrayList<>(16);

        list2.add(info);
        map.put(info.max,list2);
      }

    }


  }
}