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
    Node.fixHeight(parent);
  }


  public void print() {
    StringBuilder sb = new StringBuilder("digraph g{\n");
    Stack<Node> stack = new Stack<Node>();

    if (root == null) {
      System.out.println("Empty");
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
