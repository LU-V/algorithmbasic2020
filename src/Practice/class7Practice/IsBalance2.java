package Practice.class7Practice;


public class IsBalance2 {
  static class TreeNode {

    TreeNode right;
    TreeNode left;
    int val;

    public TreeNode(int val) {
      this.val = val;
    }
  }
  public static boolean isBalanced(TreeNode root) {
    return recur(root) != -1;
  }

  private static int recur(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = recur(root.left);
    if(left == -1) {return -1;}
    int right = recur(root.right);
    if(right == -1) {return -1;}
    return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
  }

  public static void main(String[] args) {
    TreeNode head = new TreeNode(1);
    head.left = new TreeNode(2);
    head.right = new TreeNode(3);
    head.left.left = new TreeNode(4);
    //head.left.left.left = new TreeNode(5);
    //head.left.right = new TreeNode(5);
    //head.right.left = new TreeNode(6);
    //head.right.right = new TreeNode(7);
    System.out.println(isBalanced(head));
    System.out.println(isBalanced(head));

  }
}
