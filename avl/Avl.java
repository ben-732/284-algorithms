import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Avl {
  Node root;

  public void add(int val) {
    // If root is null
    if (this.root == null) {
      this.root = new Node(val, null);
      return;
    }

    // Find the location
    Node parent = Node.recursiveFindAdd(val, this.root);


    // Add the node to the tree
    Node node = new Node(val, parent);

    if (val < parent.getValue()) {
      parent.setLeft(node);
    } else {
      parent.setRight(node);
    }

    // Fix heights
    balanceTree(parent);
  }

  /**
   * A function to rotate a node in the AVL tree
   * 
   * @param node the node to rotate
   */
  private void rotate(Node node) {
    // If node is root, do nothing

    if (node.getParent() == null) {
      return;
    }

    Node parent = node.getParent();
    Node grandparent = parent.getParent();


    // if node is the right child of parent
    if (parent.getRight() == node) {
      parent.setRight(node.getLeft());
      node.setLeft(parent);

      parent.setParent(node);

      // If there is a right element, update its parent
      if (parent.getRight() != null)
        parent.getRight().setParent(parent);


    } else {
      // if node is the left child of parent
      parent.setLeft(node.getRight());
      node.setRight(parent);

      parent.setParent(node);

      // if there is a left elemenet, update its parent
      if (parent.getLeft() != null)
        parent.getLeft().setParent(parent);
    }

    // Set nodes parent to grandparent node
    node.setParent(grandparent);

    if (grandparent == null) {
      // node is the new root
      root = node;
    } else {
      // Update grandparent node
      if (grandparent.getLeft() == parent) {
        grandparent.setLeft(node);
      } else {
        grandparent.setRight(node);
      }
    }



  }

  private void balanceTree(Node node) {

    Node next = node;

    // Move up through tree up from node fixing heights
    while (next != null) {
      next.setHeight();

      int heightDifference = next.getLeftHeight() - next.getRightHeight();

      // System.out.printf("Node: %d, h diff: %d\n", next.getValue(), heightDifference);

      // If needs rotation, rotate otherwise continue up the tree
      if (heightDifference < -1) {
        rotate(next.getRight());
      } else if (heightDifference > 1) {
        rotate(next.getLeft());
      } else {
        next = next.getParent();

      }


    }



  }

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

        node.getLeft().setParent(parent);
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

        node.getRight().setParent(parent);
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

  public void remove(int key) {
    System.out.println("remving: " + key + " from tree");
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



  public void print() {
    StringBuilder sb = new StringBuilder("digraph g{\n");
    Stack<Node> stack = new Stack<Node>();

    if (root == null) {
      // System.out.println("Empty");
      return;
    }

    stack.push(root);


    while (stack.size() > 0) {

      Node current = stack.pop();

      int currentVal = current.getValue();
      int parent = current.getParent() == null ? 0 : current.getParent().getValue();

      sb.append(String.format("%d [label=\"%d - (H%d, P%d)\"]\n", currentVal, currentVal,
          current.getHeight(), parent));


      if (current.getLeft() != null) {


        sb.append(String.format("%d -> %d\n", current.getValue(), current.getLeft().getValue()));
        stack.push(current.getLeft());

      }

      if (current.getRight() != null) {
        sb.append(String.format("%d -> %d\n", current.getValue(), current.getRight().getValue()));
        stack.push(current.getRight());

      }
    }

    sb.append("}");
    // System.out.println(sb.toString());

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
