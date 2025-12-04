package main.revision.october.meta.easy;

import java.util.*;

public class MergeSortedArray {

    public void mergeBruteforce(int[] a, int m, int[] b, int n) {

        int[] arr = new int[m + n];

        int left = 0;
        int right = 0;
        int index = 0;

        while (left < m && right < n) {

            if (a[left] <= b[right]) {
                arr[index] = a[left];
                left++;
            } else {
                arr[index] = a[right];
                right++;
            }
            index++;
        }

        while (left < m) {
            arr[index] = a[left];
            left++;
            index++;
        }

        while (right < m) {
            arr[index] = a[right];
            right++;
            index++;
        }

        // put back
        for (int i = 0; i < m + n; i++) {

            if (i < m) {
                a[i] = arr[i];
            } else {
                a[i - n] = arr[i];
            }
        }
    }

    public void mergeBruteforceOptimal(int[] a, int m, int[] b, int n) {

        int aIdx = m - 1;
        int bIdx = 0;

        while (aIdx >= 0 && bIdx < n) {

            if (a[aIdx] > b[bIdx]) {
                swap(a, b, aIdx, bIdx);
                aIdx--;
                bIdx++;
            } else {
                break;
            }
        }

        Arrays.sort(a);
        Arrays.sort(b);

        Arrays.sort(a, 0, m + n); // Sort only the merged array if needed

    }

    /**
     * Swaps elements between two arrays or within the same array.
     * If both arrays are the same, swaps elements at indices i and j within the
     * array.
     * If arrays are different, swaps a[i] with b[j] if a[i] > b[j].
     *
     * @param a the first array
     * @param b the second array
     * @param i the index in the first array
     * @param j the index in the second array
     */
    private static void swap(int[] a, int[] b, int i, int j) {
        if (a == b) {
            if (a[i] > a[j]) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        } else {
            if (a[i] > b[j]) {
                int temp = a[i];
                a[i] = b[j];
                b[j] = temp;
            }
        }
    }

    public class Solution {
        public void mergeBest(int[] a, int m, int[] b, int n) {
            // 'length' is the total number of elements to merge.
            // 'gap' is initialized to half of the total length (rounded up), following
            // Shell sort's gap sequence.
            int length = n + m;
            int gap = (length / 2) + (length % 2);

            while (gap > 0) {

                int left = 0, right = left + gap;

                while (right < length) {

                    // Map left and right indices to the correct array and position
                    if (left < m && right < m) {
                        swap(a, a, left, right);
                    } else if (left < m && right >= m) {
                        swap(a, b, left, right - m);
                    } else if (left >= m && right >= m) {
                        swap(b, b, left - m, right - m);
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

    // revised on 04/11/2025
    public class SolutionRevisedOnFourteenDay {
        public void mergeWithGap(int[] nums1, int m, int[] nums2, int n) {
            int len = m + n;
            int gap = (len / 2) + (len % 2);

            while (gap > 0) {
                int left = 0;
                int right = left + gap;
                while (right < len) {
                    // case 1: left in nums1, right in nums1
                    if (left < m && right < m) {
                        swap(nums1, nums1, left, right);
                    }
                    // case 2: left in nums1, right in nums2
                    else if (left < m && right >= m) {
                        swap(nums1, nums2, left, right - m);
                    }
                    // case 3: left in nums2, right in nums2
                    else if (left >= m && right >= m) {
                        swap(nums2, nums2, left - m, right - m);
                    }
                    left++;
                    right++;
                }
                if (gap == 1) {
                    break;
                }
                gap = (gap / 2) + (gap % 2);
            }

            // copy elements from nums2 to nums1
            for (int i = 0; i < n; i++) {
                nums1[m + i] = nums2[i];
            }
        }
    }

    // revised on 12/3/2025
    public class SolutionRevisedOnThirtyDay {
        public void mergeWithGap(int[] nums1, int m, int[] nums2, int n) {

            int len = m + n;
            int gap = (len / 2) + (len % 2);

            while (gap > 0) {
                int left = 0;
                int right = left + gap;

                while (right < left) {

                    if (left < m && right < m) {
                        swap(nums1, nums1, left, right);
                    } else if (left < m && right >= m) {
                        swap(nums1, nums2, left, right - m);
                    } else {
                        swap(nums2, nums2, left - m, right - m);
                    }
                    left++;
                    right++;
                }

                if (gap == 1) {
                    break;
                }

                gap = (gap / 2) + (gap % 2);

            }

            // copy elements from nums2 to nums1
            for (int i = 0; i < n; i++) {
                nums1[m + i] = nums2[i];
            }
        }
    }
}
