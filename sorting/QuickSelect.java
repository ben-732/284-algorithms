import java.util.Arrays;
import java.util.Random;

public class QuickSelect {
  public static void main(String[] args) {
    int[] A = {5, 3, 2, 1, 23, 6, 3, 7, 8, 43, 8};

    int s = find(A, 5);

    System.out.println(Arrays.toString(A));
    System.out.println(s);

    // Less small array - Random
    // int n = 10000000;
    // Random random = new Random();

    // int[] C = new int[n];

    // for (int i = 0; i < n; i++) {
    // C[i] = random.nextInt(3 * n);
    // }

    // find(C, 5);

    // System.out.println("C Sorted");

    // System.out.println(Arrays.toString(C));
  }

  // find the kth largest element, from 0 to n-1
  private static int find(int[] A, int k) {
    int left = 0;
    int right = A.length - 1;

    while (left < right) {
      int q = partition(A, left, right);

      if (q == k)
        return A[q];

      if (k < q)
        left = q;
      else
        right = q;
    }

    return A[left];
  }

  private static int partition(int[] A, int start, int end) {

    int q = end;

    // if q is greater than end,,


    return q;
  }


}
