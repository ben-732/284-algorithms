import java.util.Arrays;
import java.util.Random;

/**
 * SelectionSort
 */
public class QuickSort {

  public static void main(String[] args) {
    // Small array
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
    quickSort(A, 0, A.length - 1);
  }

  public static Boolean isSorted(int[] A) {
    for (int i = 0; i < A.length - 2; i++) {
      if (A[i] > A[i + 1])
        return false;
    }
    return true;
  }

  private static void quickSort(int[] A, int start, int end) {
    if (start >= end)
      return;

    int q = partition(A, start, end);

    quickSort(A, start, q - 1);
    quickSort(A, q + 1, end);
  }

  private static int partition(int[] A, int start, int end) {
    Random rd = new Random();

    // Choose a random pivot and place at the end
    swap(A, end, start + rd.nextInt(end - start + 1));


    int q = end;

    for (int i = end - 1; i >= start; i--) {
      if (A[q] < A[i]) {
        // If pointer is not directly to the left of pivot
        if (i != q - 1) {
          swap(A, i, q - 1);
        }
        swap(A, q - 1, q);

        q--;
      }
    }


    return q;
  }

  private static void swap(int[] A, int i1, int i2) {
    int temp = A[i1];
    A[i1] = A[i2];
    A[i2] = temp;

  }
}
