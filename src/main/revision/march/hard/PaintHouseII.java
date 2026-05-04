package main.revision.march.hard;

import java.util.Arrays;

public class PaintHouseII {

    public class Solution {

        public int minCostII(int[][] costs) {

            if (costs == null || costs.length == 0 || costs[0].length == 0) {
                return 0;
            }

            int numHouses = costs.length;
            int numColors = costs[0].length;

            int[] previousRows = costs[0].clone();

            for (int houseIndex = 1; houseIndex < numHouses; houseIndex++) {

                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;

                for (int cost : previousRows) {
                    if (cost < min1) {
                        min2 = min1;
                        min1 = cost;
                    } else {
                        min2 = cost;
                    }
                }

                int[] currentRow = costs[houseIndex].clone();

                for (int curColor = 0; curColor < numColors; curColor++) {

                    if (previousRows[curColor] == min1) {
                        currentRow[curColor] += min2;
                    } else {
                        currentRow[curColor] += min1;
                    }
                }

                previousRows = currentRow;
            }

            return Arrays.stream(previousRows).min().getAsInt();
        }
    }
}
