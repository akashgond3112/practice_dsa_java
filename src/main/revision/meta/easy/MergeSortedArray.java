/**
 * @author agond
 * @date Jun 02, 2025
 * @time 6:23:12 PM
 */
package main.revision.meta.easy;

public class MergeSortedArray {

    private static void swap(int[] arr1, int[] arr2, int i, int j) {
        // If both arrays are the same reference, swap within that array
        if (arr1 == arr2) {
            if (arr1[i] > arr1[j]) {
                int temp = arr1[i];
                arr1[i] = arr1[j];
                arr1[j] = temp;
            }
        } else {
            // Swap between different arrays
            if (arr1[i] > arr2[j]) {
                int temp = arr1[i];
                arr1[i] = arr2[j];
                arr2[j] = temp;
            }
        }
    }

    public void merge(int[] a, int n, int[] b, int m) {
        int length = n + m;
        int gap = (length / 2) + (length % 2);

        while (gap > 0) {
            int left = 0, right = left + gap;

            while (right < length) {
                if (left < n && right >= n) {
                    // Comparing element in array 'a' with element in array 'b'
                    swap(a, b, left, right - n);
                } else if (left >= n) {
                    // Both elements are in array 'b'
                    swap(b, b, left - n, right - n);
                } else {
                    // Both elements are in array 'a'
                    swap(a, a, left, right);
                }
                left++;
                right++;
            }
            if (gap == 1)
                break;
            gap = (gap / 2) + (gap % 2);
        }
    }

}
