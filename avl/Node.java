class Node {
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


