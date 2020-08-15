package Practice.class7Practice;

/**
 * @author tiger
 */
public class IsBalance {

  static class TreeNode {

    TreeNode right;
    TreeNode left;
    int val;

    public TreeNode(int val) {
      this.val = val;
    }
  }

  private static class Info {

    boolean isBalance;
    int high;

    public Info(boolean isBalance, int high) {
      this.high = high;
      this.isBalance = isBalance;
    }
  }

  public static boolean isBalanced(TreeNode root) {
    return is(root).isBalance;
  }

  private static Info is(TreeNode root) {
    if (root == null) {
      return new Info(true, 0);
    }
    Info leftInfo = is(root.left);
    Info rightInfo = is(root.right);
    int height = Math.max(leftInfo.high, rightInfo.high) + 1;
    Boolean isBalance = true;
    if (!leftInfo.isBalance || !rightInfo.isBalance || Math.abs(leftInfo.high - rightInfo.high) > 1) {
      isBalance = false;
    }
    return new Info(isBalance, height);
  }

  public static boolean isBalanced2(TreeNode head){
    return is2(head).isBalance;
  }

  public static Info is2(TreeNode head){
    if (head == null){
      return  new Info(true,0);
    }
    Info leftInfo = is2(head.left);
    Info rightInfo = is2(head.right);
    int height =Math.max(leftInfo.high,rightInfo.high)+1;

    Boolean isBalance = false;
    if (leftInfo.isBalance&&rightInfo.isBalance&&Math.abs(leftInfo.high-rightInfo.high)<=1){
      isBalance = true;
    }
    return new Info(isBalance,height);
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
    System.out.println(isBalanced2(head));

  }
}