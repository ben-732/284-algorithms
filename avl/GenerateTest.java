import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateTest {
  public static void main(String[] args) throws FileNotFoundException {
    Random rd = new Random();

    PrintWriter out = new PrintWriter("test.txt");


    for (int i = 0; i < 1000000; i++) {
      if (rd.nextBoolean()) {
        out.println("1 " + rd.nextInt(Integer.MAX_VALUE));
      } else {
        out.println("0 " + rd.nextInt(Integer.MAX_VALUE));
      }
    }

    out.close();
  }
}
