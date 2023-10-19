import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Submission {

  public static void main(String[] args) {

    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    Avl tree = new Avl();

    for (int i = 0; i < 1000000; i++) {


      // if 1, contains int. if 0, add int

      if (in.nextInt() == 0) {

        tree.add(in.nextInt());

      } else {

        out.println(tree.contains(in.nextInt()) ? "1" : "0");

      }


    }



    out.close();
  }

  static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens())
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
        }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }


  static class Node {
    private int height;
    private Node left;
    private Node right;
    private Node parent;
    private int value;

    public Node(int value) {
      this.value = value;
      this.height = 0;
    }

    /**
     * Get the height of the node
     * 
     * @return height of node
     */
    public int getHeight() {
      return this.height;
    }

    /**
     * Set the height of the node from the heights of its children
     */
    public void setHeight() {
      // If there is no left or right, set height to 0
      if (this.left == null && this.right == null)
        this.height = 0;
      else
        this.height = Math.max(this.getLeftHeight(), this.getRightHeight()) + 1;
    }

    /**
     * Get the height of the left child
     * 
     * @return height of left child
     */
    public int getLeftHeight() {
      if (this.left == null)
        return 0;
      return this.left.getHeight();
    }

    /**
     * Get the height of the right child
     * 
     * @return height of right child
     */
    public int getRightHeight() {
      if (this.right == null)
        return 0;
      return this.right.getHeight();
    }

    public int getBalance() {

      return this.getLeftHeight() - this.getRightHeight();
    }

    /**
     * Get the left child
     * 
     * @return left child
     */
    public Node getLeft() {
      return this.left;
    }

    /**
     * Get the right child
     * 
     * @return right child
     */
    public Node getRight() {
      return this.right;
    }

    /**
     * Get the value of the node
     * 
     * @return value of node
     */
    public int getValue() {
      return value;
    }

    /**
     * Set the value of the node
     * 
     * @param val value to set node to
     */
    public void setValue(int val) {
      this.value = val;
    }

    /**
     * Get the parent of the node
     * 
     * @return parent of node
     */
    public Node getParent() {
      return this.parent;
    }

    /**
     * Set the left child of the ?
     * 
     * @param l node to set as left child
     */
    public void setLeft(Node l) {
      if (this.left != null) {
        this.left.parent = null;
      }



      if (l != null) {

        l.setParent(this);;
      }

      this.left = l;
    }

    /**
     * Set the right child of the node
     * 
     * @param r node to set as right child
     */
    public void setRight(Node r) {
      if (this.right != null) {
        this.right.parent = null;
      }

      if (r != null) {
        r.setParent(this);;
      }

      this.right = r;

    }

    /**
     * Set the parent of the node
     * 
     * @param p node to set as parent
     */
    public void setParent(Node p) {

      if (this.parent != null) {
        if (this.parent.left == this) {
          this.parent.left = null;
        } else {
          this.parent.right = null;
        }
      }


      this.parent = p;


    }


    /**
     * Function to compare the value of the node to a given value and return an appropriate child
     * 
     * @param val value to compare to
     * @return left child if val is less than node value, right child if val is greater than node
     *         value
     */
    public Node compareValue(int val) {
      if (val < this.value) {
        return this.left;
      }

      return this.right;
    }
  }



  static class Avl {
    Node root;

    /**
     * Add a new node to the tree
     * 
     * @param val
     */
    public void add(int val) {
      // If root is null
      if (this.root == null) {
        this.root = new Node(val);
        return;
      }

      // Find the parent of the new node
      Node parent = root;

      while (parent.compareValue(val) != null) {
        parent = parent.compareValue(val);
      }


      // Add the node to the tree
      Node node = new Node(val);

      if (val < parent.getValue()) {
        parent.setLeft(node);
      } else {
        parent.setRight(node);
      }
      // Fix heights of the tree starting from new node
      balanceTree(parent);
    }


    private void rotateLeft(Node node) {
      Node parent = node.getParent();
      Node child = node.getRight();

      Boolean isParentLeft = parent != null && parent.getLeft() == node;

      if (child == null) {
        return;
      }

      // Left rotate
      node.setRight(child.getLeft());
      child.setLeft(node);

      if (parent == null) {
        this.root = child;
      } else if (isParentLeft) {
        parent.setLeft(child);
      } else {
        parent.setRight(child);
      }

      node.setHeight();
      child.setHeight();

    }


    private void rotateRight(Node node) {

      Node parent = node.getParent();
      Node child = node.getLeft();

      Boolean isParentLeft = parent != null && parent.getLeft() == node;


      if (child == null) {
        return;
      }

      node.setLeft(child.getRight());
      child.setRight(node);

      if (parent == null) {
        this.root = child;
      } else if (isParentLeft) {
        parent.setLeft(child);
      } else {
        parent.setRight(child);
      }

      node.setHeight();
      child.setHeight();

    }


    /**
     * A helper function to balance the tree by moving up from a node fixing heights and rotating
     * when needed
     * 
     * @param node node to traverse up from
     */
    private void balanceTree(Node node) {

      Node next = node;


      // Move up through tree up from node fixing heights
      while (next != null) {

        next.setHeight();

        int heightDifference = next.getBalance();


        // preform appropriate rotation based on balance
        if (heightDifference < -1) {
          if (next.getRight().getBalance() > 0) {

            rotateRight(next.getRight());
          }

          rotateLeft(next);

          next = node;
        } else if (heightDifference > 1) {

          if (next.getLeft().getBalance() < 0) {

            rotateLeft(next.getLeft());
          }

          rotateRight(next);

          next = node;
        } else {
          next = next.getParent();

        }



      }
    }

    /**
     * Find a node in the tree with a given value
     * 
     * @param key value to find
     * 
     * @return the node with the given value or null if not found
     */
    public Node find(int key) {

      Node next = this.root;

      while (next != null) {

        if (next.getValue() > key) {
          next = next.getLeft();
        } else if (next.getValue() < key) {
          next = next.getRight();
        } else {
          return next;
        }
      }

      return null;
    }

    public boolean contains(int key) {
      return find(key) != null;
    }
  }
}
