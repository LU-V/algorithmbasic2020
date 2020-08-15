package Practice.class7Practice;

public class maxDistance {

  public static class Node {

    Node left;
    Node right;
    int val;

    public Node(int val) {
      this.val = val;
    }
  }

  private static class Info {

    int height;
    int maxDistance;

    public Info(int h, int maxD) {
      this.height = h;
      this.maxDistance = maxD;
    }
  }
  public static int maxDistance(Node node){
    return  go(node).maxDistance;
  }

  public static Info go(Node head) {
    if (head == null) {
      return new Info(0, 0);
    }
    Info leftInfo = go(head.left);
    Info rightInfo = go(head.right);
    int high = Math.max(leftInfo.height, rightInfo.height) + 1;
    int maxD = Math.max(leftInfo.height + rightInfo.height + 1, Math.max(leftInfo.maxDistance, rightInfo.maxDistance));
    return new Info(high, maxD);
  }

  public static void main(String[] args) {
    Node node = new Node(1);
    node.left = new Node(2);
    node.right = new Node(3);
    //node.left.left = new Node(4);
    //node.left.right = new Node(5);
    //node.left.left.left = new Node(6);
    //node.left.right.right = new Node(7);
    //node.left.left.left.left = new Node(8);
    //node.left.right.right.right = new Node(9);
    //node.left.left.left.left.left = new Node(10);
    //node.left.right.right.right.right = new Node(11);
    //node.left.right.right.right.right.right = new Node(12);
    System.out.println(maxDistance(node));
  }
}