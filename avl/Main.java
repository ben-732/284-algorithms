public class Main {
  public static void main(String[] args) {
    Avl thing = new Avl();

    // thing.add(10);
    // thing.add(2);
    // thing.add(3);
    // thing.add(1);
    // thing.add(4);
    // thing.add(11);
    // thing.add(12);
    // // thing.add(5);

    // thing.add(1);
    // thing.add(2);
    // thing.add(3);
    // thing.add(4);
    // thing.add(5);
    // thing.add(6);
    // thing.add(7);
    // thing.add(8);
    // thing.add(9);

    // Add 1 million numbers
    for (int i = 0; i < 1000000; i++) {
      thing.add(i);
    }

    for (int j = 0; j < 1000000; j++) {
      thing.find(345353);
    }



    // thing.print();

  }
}
