import java.util.Arrays;
import java.util.Random;

public class RadixSort {
  public static void main(String[] args) {

    int[] A = {5, 3, 2, 1, 23, 6, 3, 7, 8, 43, 2, 5, 7, 8, 2, 3, 4, 10};

    sort(A);

    System.out.println(Arrays.toString(A));

    // Less small array

    Random random = new Random();

    int n = 10000000;
    int[] B = new int[n];

    for (int i = 0; i < n; i++) {
      B[i] = random.nextInt(3 * n);
    }


    sort(B);

    // System.out.println(Arrays.toString(B));
    System.out.println(isSorted(B) ? "B Sorted correctly" : "B not sorted correctly");
  }

  public static void sort(int[] A) {
    int place = 1;

    int max = findMax(A);



    while (max / place > 1) {
      // Temp array to hold new order
      int B[] = new int[A.length];

      int bp = 0;


      for (int digit = 0; digit < 10; digit++) {

        for (int i = 0; i < A.length; i++) {
          if ((A[i] / place) % 10 == digit) {
            B[bp++] = A[i];
          }

        }
      }

      if (bp < 1) {
        break;
      }

      // Copy B back into A
      for (int i = 0; i < B.length; i++)
        A[i] = B[i];

      place = place * 10;

    }
  }

  public static Boolean isSorted(int[] A) {
    for (int i = 0; i < A.length - 2; i++) {
      if (A[i] > A[i + 1])
        return false;
    }
    return true;
  }

  private static int findMax(int[] A) {
    int maxIndex = 0;
    for (int i = 1; i < A.length; i++)
      if (A[i] > A[maxIndex])
        maxIndex = i;

    return A[maxIndex];
  }

}
