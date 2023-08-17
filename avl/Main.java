public class Main {
  public static void main(String[] args) {
    Avl thing = new Avl();

    int n = 1000000;

    // Add 1 million numbers
    for (int i = 0; i < n + 10; i++) {
      thing.add(i);
    }

    for (int j = 0; j < n; j++) {
      thing.find(j);
    }

    for (int k = 0; k < n; k++) {
      thing.remove(k);
    }

    thing.print();
  }
}
