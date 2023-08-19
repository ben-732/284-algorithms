import java.util.Arrays;
import java.util.Random;

/**
 * SelectionSort
 */
public class SelectionSort {

  public static void main(String[] args) {
    // Small array
    int[] A = {5, 3, 2, 1, 23, 6, 3, 7, 8, 43};

    sort(A);

    System.out.println(Arrays.toString(A));

    // Less small array

    Random random = new Random();

    int n = 100000;
    int[] B = new int[n];

    for (int i = 0; i < n; i++) {
      B[i] = random.nextInt(3 * n);
    }


    sort(B);

    System.out.println(Arrays.toString(B));
  }

  public static void sort(int[] A) {
    // Loop through each element in the array
    for (int i = 0; i < A.length; i++) {
      int min_index = i;

      // Loop through unsorted elements and find the smallest one
      for (int j = i + 1; j < A.length; j++) {

        if (A[j] < A[min_index]) {
          min_index = j;
        }
      }

      // If no swap needed, continue
      if (min_index == i)
        continue;

      // Swap elements at min_index and i
      int temp = A[min_index];
      A[min_index] = A[i];
      A[i] = temp;
    }
  }
}
