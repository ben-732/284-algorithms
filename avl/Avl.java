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

    } else {
      // if node is the left child of parent
      parent.setLeft(node.getRight());
      node.setRight(parent);
      parent.setLeft(node);
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

  public int find(int val) {

    Node next = this.root;

    while (next != null) {
      if (next.getValue() > val) {
        next = next.getLeft();
      } else if (next.getValue() < val) {
        next = next.getRight();
      } else {
        return next.getValue();
      }
    }

    return -1;
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

      sb.append(String.format("%d [label=\"%d - (H%d)\"]\n", currentVal, currentVal,
          current.getHeight()));


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
    System.out.println(sb.toString());

    BufferedWriter writer;
    try {
      writer = new BufferedWriter(new FileWriter("out.txt"));
      writer.write(sb.toString());
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }



  }
}
