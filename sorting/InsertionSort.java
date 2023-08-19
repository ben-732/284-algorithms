import java.util.Arrays;
import java.util.Random;

/**
 * InsertionSort
 */
public class InsertionSort {

  public static void main(String[] args) {
    // Small array
    int[] A = {5, 3, 2, 1, 23, 6, 3, 7, 8, 43, 8};

    sort(A);

    System.out.println(Arrays.toString(A));

    // Less small array - almost sorted
    int n = 100000;

    Random random = new Random();

    int[] B = new int[n];

    // Some random numbers
    for (int i = 0; i < 50; i++) {
      B[i] = random.nextInt(3 * n);
    }

    // A lot of sorted numbers
    for (int i = 0; i < n - 50; i++) {
      B[i] = i;
    }


    sort(B);

    System.out.println(Arrays.toString(B));

    // Less small array - Random
    int[] C = new int[n];

    for (int i = 0; i < n; i++) {
      C[i] = random.nextInt(3 * n);
    }

    sort(C);

    System.out.println(Arrays.toString(C));
  }

  public static void sort(int[] A) {
    for (int i = 1; i < A.length; i++) {

      int current = A[i];

      int newIndex = i - 1;

      // If element at new index is too large, shuffle to the right
      while (newIndex > 0 && (A[newIndex] > current)) {
        A[newIndex + 1] = A[newIndex];
        newIndex--;
      }
      // Once the new position for current has been found, insert it
      A[newIndex + 1] = current;
    }
  }
}
