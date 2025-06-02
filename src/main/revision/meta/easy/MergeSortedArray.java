/**
 * @author agond
 * @date Jun 02, 2025
 * @time 6:23:12 PM
 */
package main.revision.meta.easy;

public class MergeSortedArray {

    private static void swap(int[] a, int[] b, int i, int j) {
        if (a[i] > a[j]) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public void merge(int[] a, int n, int[] b, int m) {

        int length = n + m;
        int gap = (length / 2) + (length % 2);

        while (gap > 0) {

            int left = 0;
            int right = left + gap;

            while (right < length) {

                if (left < n && right >= n) {
                    swap(a, b, left, right - n);
                } else if (left >= n) {
                    swap(b, b, left - n, right - n);
                } else {
                    swap(a, a, left, right);
                }
            }
        }
    }

}
