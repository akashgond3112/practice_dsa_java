/**
 * @author akash
 * @date Jul 26, 2026
 * @time 7:15:30 AM
 */
package main.pattern.overlappingintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumNumberOfArrowsToBurstBalloons {

    class Solution {
        public int findMinArrowShots(int[][] points) {

            Arrays.sort(points, Comparator.comparingInt(a -> a[0]));

            List<int[]> mergedIntervals = new ArrayList<>();

            int[] currentInterval = points[0];
            mergedIntervals.add(currentInterval);

            for (int i = 1; i < points.length; i++) {
                if (points[i][0] <= currentInterval[1]) {
                    currentInterval[1] = Math.min(currentInterval[1], points[i][1]);
                } else {
                    currentInterval = points[i];
                    mergedIntervals.add(currentInterval);
                }
            }
            return mergedIntervals.size();
        }
    }
}
