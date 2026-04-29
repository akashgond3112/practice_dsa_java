package main.revision.march.hard;

import java.util.*;

public class BestMeetingPoint {

    public class Solution {

        public int minTotalDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            List<Integer> rows = new ArrayList<>();
            List<Integer> cols = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }

            int totalDistance = 0;

            totalDistance += getMedianDistance(rows);
            totalDistance += getMedianDistance(cols);

            return totalDistance;
        }

        private int getMedianDistance(List<Integer> points) {
            int distance = 0;
            int i = 0;
            int j = points.size() - 1;

            while (i < j) {
                distance += points.get(j) - points.get(i);
                i++;
                j--;
            }
            return distance;
        }
    }
}
