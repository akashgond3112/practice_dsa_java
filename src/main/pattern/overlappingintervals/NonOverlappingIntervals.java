/**
 * @author akash
 * @date Jul 26, 2026
 * @time 8:26:44 AM
 */
package main.pattern.overlappingintervals;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {

            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

            int removal = 0;

            int[] currentInterval = intervals[0];

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < currentInterval[1]) {
                    removal++;
                } else {
                    currentInterval = intervals[i];
                }
            }
            return removal;
        }
    }
}
