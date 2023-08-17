public class Main {
  public static void main(String[] args) {
    Avl thing = new Avl();



    for (int i = 1; i <= 1000000; i++) {
      thing.add(i);
    }

    thing.remove(6);
    thing.remove(7);
    thing.remove(13);
    thing.remove(33);
    thing.remove(124);
    thing.remove(22);
    thing.remove(93);

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
