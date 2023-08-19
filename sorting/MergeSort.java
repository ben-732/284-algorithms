import java.util.Arrays;
import java.util.Random;

/**
 * InsertionSort
 */
public class MergeSort {

  public static void main(String[] args) {
    // Small array
    int[] A = {5, 3, 2, 1, 23, 6, 3, 7, 8, 43, 8};

    sort(A);

    System.out.println(Arrays.toString(A));

    // Less small array - Random
    int n = 10000000;
    Random random = new Random();

    int[] C = new int[n];

    for (int i = 0; i < n; i++) {
      C[i] = random.nextInt(3 * n);
    }

    sort(C);

    System.out.println("C Sorted");

    // System.out.println(Arrays.toString(C));
  }

  public static void sort(int[] A) {
    split(A, 0, A.length - 1);
  }

  private static void split(int[] A, int start, int end) {
    // Don't do anything if theres only one element
    if (start == end)
      return;

    int middle = (start + end) / 2;

    split(A, start, middle);
    split(A, middle + 1, end);

    combine(A, start, middle, end);
  }

  private static void combine(int[] A, int start, int middle, int end) {
    // Create a new array to hold sorted values (SLOW AA)
    int[] B = new int[end - start + 1];

    int leftPos = start;
    int rightPos = middle + 1;

    // Add elements to B in sorted order
    for (int i = 0; i < B.length; i++) {
      // If the left array is completed
      if (leftPos > middle)
        B[i] = A[rightPos++];

      // If the right array is completed
      else if (rightPos > end)
        B[i] = A[leftPos++];


      // If left element bigger than right, add right element
      else if (A[leftPos] > A[rightPos])
        B[i] = A[rightPos++];

      // Otherwise add left element
      else
        B[i] = A[leftPos++];
    }

    // Copy the new array back into A
    for (int j = 0; j < B.length; j++) {
      A[start + j] = B[j];
    }

  }
}
