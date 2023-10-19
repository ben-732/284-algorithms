import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GenerateTest {
  public static void main(String[] args) throws FileNotFoundException {
    Random rd = new Random();

    PrintWriter out = new PrintWriter("test.txt");
    PrintWriter res = new PrintWriter("result.txt");


    HashSet<Integer> ls = new HashSet<Integer>();
    ArrayList<Integer> ls2 = new ArrayList<Integer>();

    for (int i = 0; i < 1000000; i++) {
      if (i % 10000 == 0)
        System.out.println(i);

      if (i > 50000 && rd.nextBoolean()) {
        if (rd.nextInt(10) < 3) {
          int ss = rd.nextInt();
          out.println("1 " + ss);

          if (ls.contains(ss)) {
            res.println(1);
          } else {
            res.println(0);

          }

        } else {
          out.println("1 " + ls2.get(ls.size() - 1));

          res.println(1);

        }
      } else {
        int ss = rd.nextInt();
        ls.add(ss);
        ls2.add(ss);
        out.println("0 " + ss);
      }
    }

    out.close();
    res.close();
  }
}
