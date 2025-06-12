/**
 * @author agond
 * @date Jun 12, 2025
 * @time 7:06:20 PM
 */
package main.revision.meta.easy;

public class KthMissingPositiveNumber {

    public int findKthPositive(int[] arr, int k) {
        for (int j : arr) {
            if (j <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }
}
