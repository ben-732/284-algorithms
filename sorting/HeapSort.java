import java.util.Arrays;
import java.util.Random;


public class HeapSort {

  public static void main(String[] args) {
    // Small array
    int[] A = {5, 3, 2, 1, 23, 6, 3, 7, 8, 43};

    sort(A);

    System.out.println(Arrays.toString(A));

    // Less small array

    Random random = new Random();

    int n = 10000000;
    int r = 5;
    System.out.printf("Sorting %d arrays of %d integers: \n", r, n);
    for (int j = 0; j < r; j++) {
      int[] B = new int[n];

      for (int i = 0; i < n; i++) {
        B[i] = random.nextInt(3 * n);
      }


      sort(B);

      // System.out.println(Arrays.toString(B));

      System.out.printf(isSorted(B) ? "%02d Sorted correctly    : " : "%d not sorted correctly: ",
          j);
      // System.out.print(Arrays.toString(B));
      System.out.println();
    }
  }

  public static Boolean isSorted(int[] A) {
    for (int i = 0; i < A.length - 2; i++) {
      if (A[i] > A[i + 1])
        return false;
    }
    return true;
  }

  public static void sort(int[] A) {
    // First "heapify" the array, sorting it into the correct order
    heapify(A);

    for (int i = 0; i < A.length - 1; i++) {
      // Swap the largest (first) element with the last position
      swap(A, 0, A.length - 1 - i);

      // Make sure the unsorted elements are in correct order
      fix(A, A.length - 1 - i);
    }
  }

  /**
   * Helper function to rearrange array to heap order
   * 
   * @param A Array to rearrange
   */
  private static void heapify(int[] A) {
    for (int i = 0; i < A.length; i++) {
      int j = i;

      // Parent location at index (j-1) / 2
      while (A[(j - 1) / 2] < A[j]) {

        // If the parent is smaller, switch parent and element
        swap(A, (j - 1) / 2, j);

        // Move j to parent index
        j = (j - 1) / 2;
      }
    }
  }

  private static void fix(int[] A, int end) {
    int p = 0;

    // While p in range and one of it's children is greater than p
    while (p * 2 + 1 < end) {

      // If p is the second child of p is out of bounds
      if (p * 2 + 2 >= end) {
        if (A[p * 2 + 1] > A[p]) {
          swap(A, p * 2 + 1, p);
          p = p * 2 + 1;
          continue;
        } else {
          break;
        }
      }

      // If there are no children greater than p, break
      if (!(Math.max(A[2 * p + 1], A[2 * p + 2]) > A[p]))
        break;


      // If first child is larger than second child
      if (A[2 * p + 1] > A[2 * p + 2]) {
        // Swap p with first child
        swap(A, p, 2 * p + 1);
        p = 2 * p + 1;
      } else {
        // Swap p with second child
        swap(A, p, 2 * p + 2);
        p = 2 * p + 2;
      }
    }
  }

  private static void swap(int[] A, int i1, int i2) {
    // System.out.printf("Moving %d from %d to %d\n", A[i2], i2, i1);
    int temp = A[i1];
    A[i1] = A[i2];
    A[i2] = temp;

  }
}
