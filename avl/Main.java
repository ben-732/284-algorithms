import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {

    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    Avl tree = new Avl();

    int size = 0;

    for (int i = 0; i < 1000000; i++) {


      // if 1, contains int. if 0, add int

      if (in.nextInt() == 0) {
        int s = in.nextInt();

        // system.out.println("Add: " + i + " - " + s);
        tree.add(s);

        // int newSize = tree.size();

        // if (newSize < size) {
        // System.out.println("AAAA " + i);
        // System.out.println(size);
        // System.out.println(newSize);

        // tree.print();
        // throw new Exception();
        // system.out.println("Size: " + newSize);
        // } else {
        // size = newSize;

        // }

        // system.out.println("added");



      } else {
        int s = in.nextInt();

        // system.out.println("Find: " + i + " - " + s + " - " + tree.find(s));
        out.println(tree.contains(s) ? "1" : "0");

      }
    }


    tree.print();
    out.close();
  }


  static class FastScanner {
    BufferedReader br = new BufferedReader(new FileReader("test.txt"));
    StringTokenizer st = new StringTokenizer("");

    public FastScanner() throws FileNotFoundException {

    }

    String next() {
      while (!st.hasMoreTokens())
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
        }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }


}
