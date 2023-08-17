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

    for (int i = 0; i < 20; i++) {
      thing.add(i);
    }

    // thing.remove(2);

    thing.print();


    // Add 1 million numbers
    // for (int i = 0; i < 1000000; i++) {
    // thing.add(i);
    // }

    // for (int j = 0; j < 1000000; j++) {
    // thing.find(345353);
    // }



  }
}
