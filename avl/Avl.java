import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Avl {
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
   * A helper function to balance the tree by moving up from a node fixing heights and rotating when
   * needed
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

  /**
   * Helper function to remove a node from tree when it has less than 2 children
   * 
   * Balances the tree from the parent of the removed node
   * 
   * @param node the node to remove
   */
  private void simpleRemove(Node node) {
    // Get parent of node
    Node parent = node.getParent();

    // If the node has no children remove parent's reference
    if (node.getHeight() == 0) {
      // If parent is root
      if (parent == null) {
        root = null;
        // If not, handle left or right
      } else if (parent.getLeft() == node) {
        parent.setLeft(null);
      } else {
        parent.setRight(null);
      }

      // If node has 1 child on the left
    } else if (node.getRight() == null) {
      // if node is root
      if (node.getParent() == null) {
        root = node.getLeft();
        root.setParent(null);
      } else {
        // Handle on the left or right of parent
        if (parent.getLeft() == node) {
          parent.setLeft(node.getLeft());
        } else {
          parent.setRight(node.getLeft());
        }

      }
      // Do the same thing but for on the right
    } else if (node.getLeft() == null) {
      // if node is root
      if (node.getParent() == null) {
        root = node.getRight();
        root.setParent(null);
      } else {
        // Handle on the left or right of parent
        if (parent.getLeft() == node) {
          parent.setLeft(node.getRight());
        } else {
          parent.setRight(node.getRight());
        }

      }

      // If there are two children
    }

    // balance the tree from the parent up
    balanceTree(parent);
  }

  public Node findMin(Node node) {
    Node next = node;

    while (next.getLeft() != null) {
      next = next.getLeft();
    }

    return next;
  }

  /**
   * Remove a node from the tree
   * 
   * Handles the cases where there are 0, 1, or 2 children
   * 
   * If two children, Balances the tree from the parent of the node that is "moved"
   * 
   * @param key the value of the node to remove
   */
  public void remove(int key) {
    Node node = find(key);

    // If node not found, do nothing
    if (node == null)
      return;

    // If the node has 0 or 1 children
    if (node.getLeft() == null || node.getRight() == null) {
      simpleRemove(node);
      // If the node has two children
    } else {
      // Find the next biggest node
      Node newNode = findMin(node.getRight());

      // Remove it
      simpleRemove(newNode);

      // Replace this nodes key with the old key
      node.setValue(newNode.getValue());

      // if the removed node had a parent, balance the tree from that node
      if (newNode.getParent() != null)
        balanceTree(newNode.getParent());

    }

  }


  /**
   * Function to create an out.dot file to visualize the tree
   * 
   * http://www.webgraphviz.com/
   */
  public void print() {
    StringBuilder sb = new StringBuilder("digraph g{\n");
    Stack<Node> stack = new Stack<Node>();

    // If there is no root, don't render any nodes
    if (root != null) {

      // use a DFS to traverse the tree adding nodes to the output.
      stack.push(root);

      while (stack.size() > 0) {

        Node current = stack.pop();

        String parentVal =
            current.getParent() == null ? "-NULL" : "" + current.getParent().getValue();

        // Add the node to the output
        sb.append(String.format("%d [label=\"%d - (H%d, P%s)\"]\n", current.getValue(),
            current.getValue(), current.getHeight(), parentVal));

        // Add the edges and push the children to the stack
        if (current.getLeft() != null) {
          sb.append(String.format("%d -> %d\n", current.getValue(), current.getLeft().getValue()));
          stack.push(current.getLeft());
        }

        if (current.getRight() != null) {
          sb.append(String.format("%d -> %d\n", current.getValue(), current.getRight().getValue()));
          stack.push(current.getRight());
        }
      }
    }

    sb.append("}");

    // Write the output to a file [out.dot]
    BufferedWriter writer;
    try {
      writer = new BufferedWriter(new FileWriter("out.dot"));
      writer.write(sb.toString());
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
