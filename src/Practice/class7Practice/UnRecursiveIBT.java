package Practice.class7Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class UnRecursiveIBT {

  static class TreeNode {

    TreeNode right;
    TreeNode left;
    int val;

    public TreeNode(int val) {
      this.val = val;
    }
  }

  /**
   * @author luwei 中序
   */
  public List<Integer> inorderTraversal(TreeNode node) {
    List<Integer> list = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    if (node != null) {
      while (!stack.isEmpty() || node != null) {
        if (node != null) {
          stack.add(node);
          node = node.left;
        } else {
          node = stack.pop();
          System.out.println(node.val);
          list.add(node.val);
          node = node.right;
        }
      }
    }
    return list;
  }

  /**
   * @author luwei 后序
   */
  public static List<Integer> postorderTraversal(TreeNode h) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> list = new ArrayList<>();
    stack.push(h);
    TreeNode c = null;
    while (!stack.isEmpty()) {
      c = stack.peek();
      if (c.left != null && h != c.left && h != c.right) {
        stack.push(c.left);
      } else if (c.right != null && h != c.right) {
        stack.push(c.right);
      } else {
        list.add(c.val);
        h = c;
      }
    }
    return list;
  }

  public static void levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      List<Integer> l = new LinkedList<>();
      TreeNode h = queue.poll();
      System.out.println(h.val);
      if (h.left != null) {
        queue.add(h.left);
      }
      if (h.right != null) {
        queue.add(h.right);
      }
    }
  }

  /**
   * @author luwei 层级遍历，一层一个数组
   */
  public static List<List<Integer>> levelOrderToList(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> l = new ArrayList<>();
    int level = 0;
    int levelNow = 0;
    HashMap<TreeNode, Integer> map = new HashMap();
    map.put(root, 1);
    l.add(queue.peek().val);
    while (!queue.isEmpty()) {
      TreeNode h = queue.poll();
      level = map.get(h);
      if (h.left != null) {
        map.put(h.left, level + 1);
        queue.add(h.left);
      }
      if (h.right != null) {
        map.put(h.right, level + 1);
        queue.add(h.right);
      }
      if (queue.size() > 0) {
        levelNow = map.get(queue.peek());
        if (levelNow == level) {
          l.add(queue.peek().val);
        } else {
          res.add(l);
          l = new ArrayList<>();
          l.add(queue.peek().val);
        }
      } else {
        res.add(l);
      }
    }
    return res;
  }

  /**
   * @author luwei 最大宽度 不算null
   */
  public static int maxWidth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int level = 0;
    int levelNow = 0;
    int t = 0;
    int max = 1;
    HashMap<TreeNode, Integer> map = new HashMap();
    map.put(root, 1);
    while (!queue.isEmpty()) {
      TreeNode h = queue.poll();
      level = map.get(h);
      if (h.left != null) {
        t += 1;
        map.put(h.left, level + 1);
        queue.add(h.left);
      }
      if (h.right != null) {
        t += 1;
        map.put(h.right, level + 1);
        queue.add(h.right);
      }

      if (queue.peek() != null) {
        levelNow = map.get(queue.peek());
        if (levelNow != level) {
          max = Math.max(t, max);
          t = 0;
        }
      } else {
        max = Math.max(t, max);
      }
    }
    return max;
  }

  /**
   * @author luwei 序列化 层级
   * @date 2020/8/10 1:14 下午
   */
  public static String serialize(TreeNode root) {
    Queue<String> ans = new LinkedList<>();
    Queue<TreeNode> q = new LinkedList<>();
    ans.add(String.valueOf(root.val));
    if (root == null) {
      return root.toString();
    }
    q.add(root);
    TreeNode node = root;
    if (root != null) {

      while (!q.isEmpty()) {
        node = q.poll();
        if (node.left != null) {
          q.add(node.left);
          ans.add(String.valueOf(node.left.val));
        } else {
          ans.add(null);
        }

        if (node.right != null) {
          q.add(node.right);
          ans.add(String.valueOf(node.right.val));
        } else {
          ans.add(null);
        }
      }
    }
    return ans.toString();
  }

  /**
  * @author      luwei
  * 层级遍历
  * @date        2020/8/12 8:06 上午
  */
  public static Queue<String> levelSerial(TreeNode head) {
    Queue<String> ans = new LinkedList<>();
    if (head == null) {
      ans.add(null);
    } else {
      ans.add(String.valueOf(head.val));
      Queue<TreeNode> queue = new LinkedList<TreeNode>();
      queue.add(head);
      while (!queue.isEmpty()) {
        head = queue.poll();
        if (head.left != null) {
          ans.add(String.valueOf(head.left.val));
          queue.add(head.left);
        } else {
          ans.add(null);
        }
        if (head.right != null) {
          ans.add(String.valueOf(head.right.val));
          queue.add(head.right);
        } else {
          ans.add(null);
        }
      }
    }
    return ans;
  }

  /**
   * @author luwei
   * 反序列化树
   */
  public static TreeNode deserialize(String data) {
    data = data.substring(1, data.length() - 1);

    if (data.length() == 0 || data == null) {
      return null;
    }
    String str[] = data.split(",");
    Queue<String> q = new LinkedList<>();
    for (String s : str) {
      q.add(s);
    }
    TreeNode head = new TreeNode(Integer.parseInt(q.poll()));

    Queue<TreeNode> tree = new LinkedList<>();
    tree.add(head);

    // 辅助
    TreeNode treeNode = null;

    while (!q.isEmpty()) {
      treeNode = tree.poll();
      if (!"null".equals(q.peek())) {
        treeNode.left = new TreeNode(Integer.parseInt(q.poll()));
        tree.add(treeNode.left);
      } else {
        treeNode.left = null;
        q.poll();
      }

      if (!"null".equals(q.peek())) {
        treeNode.right = new TreeNode(Integer.parseInt(q.poll()));
        tree.add(treeNode.right);
      } else {
        treeNode.right = null;
        q.poll();
      }
    }
    return head;
  }

  public static void main(String[] args) {
    // tree0
    TreeNode head = new TreeNode(1);
    head.left = new TreeNode(2);
    head.right = new TreeNode(3);
    head.left.left = new TreeNode(4);
    head.left.right = new TreeNode(5);
    head.right.left = new TreeNode(6);
    head.right.right = new TreeNode(7);
    //head.left.left.left = new TreeNode(8);
    //head.left.left.right = new TreeNode(8);
    //head.left.right.left = new TreeNode(8);
    //
    //head.right.left.right = new TreeNode(8);
    //head.right.left.left = new TreeNode(8);
    //head.right.right.right = new TreeNode(8);
    //
    //head.right.left.left.left = new TreeNode(8);

    // tree1
    TreeNode head1 = new TreeNode(1);
    head1.right = new TreeNode(2);
    head1.right.left = new TreeNode(3);

    // tree 3
    TreeNode head3 = null;

    // tree 4
    TreeNode head4 = new TreeNode(1);
    //System.out.println(serialize(head));

    String ss = "[1,2,3,4,5,6,7,null,null,null,null,null,null,null,null]";

    System.out.println(deserialize(ss));

    String ss1 = "1";
    System.out.println(deserialize(ss1));

    //System.out.println(levelSerial(head));

    //System.out.println(maxWidth(head));
    //System.out.println(maxWidth(head3));
    //System.out.println(maxWidth(head4));

    //postorderTraversal(head1);
    //  levelOrder1(head);
    //System.out.println(levelOrderToList(head).toString());
    //System.out.println(levelOrderToList(head3).toString());
    //pre(head);
    //System.out.println("========");
    //in(head);
    //pos1(head);
    //System.out.println("========");
    //pos2(head);
    //System.out.println("========");
  }
}