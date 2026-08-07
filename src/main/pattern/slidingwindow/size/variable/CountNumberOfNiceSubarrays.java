/**
 * @author akash
 * @date Aug 07, 2026
 * @time 6:29:19 PM
 */
package main.pattern.slidingwindow.size.variable;

public class CountNumberOfNiceSubarrays {
    class Solution {
        public int numberOfSubarrays(int[] nums, int k) {
            int startIndex = 0;
            int endIndex = 0;
            int max = 0;
            int count = 0;
            int temp = 0;

            while (endIndex < nums.length) {
                if (nums[endIndex] % 2 != 0) {
                    count++;
                    temp = 0;
                }

                while (count == k) {
                    temp++;
                    if (nums[startIndex] % 2 == 1) {
                        count--;
                    }
                    startIndex++;
                }
                max += temp;
                endIndex++;
            }

            return max;
        }
    }
}
