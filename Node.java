class Node {
  private int height;
  private Node left;
  private Node right;
  private Node parent;
  private int value;

  public Node(int value, Node p) {
    this.parent = p;
    this.value = value;
    this.height = 0;
  }

  public int getHeight() {
    return this.height;
  }

  public void setHeight() {
    // If there is no left or right, set height to 0
    if (this.left == null && this.right == null) {
      this.height = 0;
      return;
    }

    // Set height to the max of the left or right
    if (this.left == null) {
      this.height = this.right.getHeight() + 1;
      return;
    }

    if (this.right == null) {
      this.height = this.left.getHeight() + 1;
      return;
    }

    this.height = Math.max(this.left.getHeight(), this.right.getHeight()) + 1;
  }

  public Node getLeft() {
    return this.left;
  }

  public Node getRight() {
    return this.right;
  }

  public int getValue() {
    return value;
  }

  public void setLeft(Node l) {
    this.left = l;
  }

  public void setRight(Node r) {
    this.right = r;
  }

  public void setParent(Node p) {
    this.parent = p;
  }

  public Node compareValue(int val) {
    if (val < this.value) {
      return this.left;
    }

    return this.right;
  }

  public static Node recursiveFindAdd(int val, Node current) {
    Node next = current.compareValue(val);

    if (next == null)
      return current;

    return recursiveFindAdd(val, next);

  }

}
