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

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    if (grid[i][j] == 1) {
                        cols.add(j);
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

    // 02/05/2026
    public class SolutionRevisedOnDayThird {

        public int minTotalDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            List<Integer> rows = new ArrayList<>();
            List<Integer> cols = new ArrayList<>();

            int m = grid.length;
            int n = grid[0].length;

            int totalDistance = 0;

            totalDistance += calculateTotalDistance(m, n, rows, grid, true);
            totalDistance += calculateTotalDistance(m, n, cols, grid, false);

            return totalDistance;
        }

        public int calculateTotalDistance(int m, int n, List<Integer> list, int[][] grid, boolean collectRow) {

            if (collectRow) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j] == 1) {
                            list.add(i);
                        }
                    }
                }
            } else {
                for (int j = 0; j < n; j++) {
                    for (int i = 0; i < m; i++) {
                        if (grid[i][j] == 1) {
                            list.add(j);
                        }
                    }
                }
            }

            // get Median
            int distance = 0;
            int a = 0;
            int b = list.size() - 1;

            while (a < b) {
                distance += list.get(b) - list.get(a);
                a++;
                b--;
            }
            return distance;
        }
    }
}
