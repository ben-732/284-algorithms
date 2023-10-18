import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) {

    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    Avl tree = new Avl();

    for (int i = 0; i < 1000000; i++) {


      // if (i % 10 == 0)

      // if 1, contains int. if 0, add int

      if (in.nextInt() == 0) {
        int s = in.nextInt();



        System.out.println("Add: " + i + " - " + s);

        if (!tree.contains(s))
          tree.add(s);

        System.out.println("added");



      } else {
        int s = in.nextInt();

        System.out.println("Find: " + i + " - " + s + " - " + tree.find(s));
        out.println(tree.contains(s) ? "0" : "1");

      }


    }



    out.close();
  }


  static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

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
