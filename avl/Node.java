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
    if (this.left == null && this.right == null)
      this.height = 0;
    else
      this.height = Math.max(this.getLeftHeight(), this.getRightHeight()) + 1;
  }

  public int getLeftHeight() {
    if (this.left == null)
      return 0;
    return this.left.getHeight();
  }

  public int getRightHeight() {
    if (this.right == null)
      return 0;
    return this.right.getHeight();
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

  public Node getParent() {
    return this.parent;
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
