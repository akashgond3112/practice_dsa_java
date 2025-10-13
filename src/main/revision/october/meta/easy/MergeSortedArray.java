package main.revision.october.meta.easy;

public class MergeSortedArray {

    private static void swap(int[] a, int[] b, int i, int j) {
        if (a[i] > a[j]) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public void merge(int[] a, int m, int[] b, int n) {
        int length = n + m;
        int gap = (length / 2) + (length % 2);

        while (gap > 0) {

            int left = 0, right = left + gap;

            while (right < length) {

                if (left < n && right >= n) {
                    swap(a, b, left, right - n);
                } else if (left >= n) {
                    swap(b, b, left - n, right - n);
                } else {
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
