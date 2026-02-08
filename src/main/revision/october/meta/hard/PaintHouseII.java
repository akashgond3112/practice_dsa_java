package main.revision.october.meta.hard;

import java.util.*;

/*
Problem Description
You have n houses arranged in a row that need to be painted. Each house can be painted with one of k different colors, 
and the cost to paint each house with each color varies.

The problem provides a 2D cost matrix costs where:

costs[i][j] represents the cost of painting house i with color j
The matrix has dimensions n x k (n houses, k colors)
Constraint: No two adjacent houses can have the same color.

Goal: Find the minimum total cost to paint all houses while satisfying the color constraint.
For example, if you have 3 houses and 3 colors with costs: 
House 0: Color 0 costs 1 , C o l o r 1 c o s t s 1,Color1costs5, Color 2 costs $3 
House 1: Color 0 costs 2 , C o l o r 1 c o s t s 2,Color1costs9, Color 2 costs $4 
House 2: Color 0 costs 5 , C o l o r 1 c o s t s 5,Color1costs1, Color 2 costs $2
For example, if you have 3 houses and 3 colors with costs:

You need to choose one color for each house such that consecutive houses have different colors and the sum of all chosen 
costs is minimized.

The solution uses dynamic programming where f[j] represents the minimum cost to paint houses up to the current house 
with the current house painted in color j. For each house, it calculates the minimum cost by considering all valid 
color combinations from the previous house (excluding the same color to avoid adjacent houses having the same color). 
*/

public class PaintHouseII {

    public class Solution {

        public int minCostII(int[][] costs) {
            // Edge case check: Agar costs array null ya empty hai, to 0 return karo.
            if (costs == null || costs.length == 0 || costs[0].length == 0) {
                return 0;
            }

            // Gharon (houses) aur rangon (colors) ka count nikal lo.
            int numHouses = costs.length;
            int numColors = costs[0].length;

            // `previousRow` array ko pehle ghar ke costs se initialize karo.
            // Yeh array har step pe pichle ghar tak ka minimum cost store karegi.
            int[] previousRow = costs[0].clone();

            // Dusre ghar se aakhri ghar tak loop chalao.
            for (int houseIndex = 1; houseIndex < numHouses; houseIndex++) {
                // Pichle row ke do sabse kam costs (min1 aur min2) ko track karne ke liye
                // variables.
                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;

                // `previousRow` mein se do sabse chote costs dhoondo.
                for (int cost : previousRow) {
                    if (cost < min1) {
                        min2 = min1;
                        min1 = cost;
                    } else if (cost < min2) {
                        min2 = cost;
                    }
                }

                // Current ghar ke liye ek nayi `currentRow` array banake uske original costs se
                // initialize karo.
                int[] currentRow = costs[houseIndex].clone();
                // Current ghar ke har rang ke liye minimum cost calculate karo.
                for (int curColor = 0; curColor < numColors; curColor++) {
                    // Agar pichle ghar ka rang `min1` wala tha, to hum `min2` use karenge.
                    // Aisa isliye taaki adjacent gharon ka rang same na ho.
                    if (previousRow[curColor] == min1) {
                        currentRow[curColor] += min2;
                    } else {
                        // Nahi to `min1` use karenge.
                        currentRow[curColor] += min1;
                    }
                }
                // Agle iteration ke liye `previousRow` ko `currentRow` se update kar do.
                previousRow = currentRow;
            }

            // Aakhri main`previousRow` mein se sabse kam cost dhoondh kar return karo.
            return Arrays.stream(previousRow).min().getAsInt();
        }
    }

    // revised on 12/21/2025
    public class SolutionRevisedOnThirdDay {

        public int minCostII(int[][] costs) {
            // Edge case check: Agar costs array null ya empty hai, to 0 return karo.
            if (costs == null || costs.length == 0 || costs[0].length == 0) {
                return 0;
            }

            // Gharon (houses) aur rangon (colors) ka count nikal lo.
            int numHouses = costs.length;
            int numColors = costs[0].length;

            // `previousRow` array ko pehle ghar ke costs se initialize karo.
            // Yeh array har step pe pichle ghar tak ka minimum cost store karegi.
            int[] previousRow = costs[0].clone();

            // Dusre ghar se aakhri ghar tak loop chalao.
            for (int houseIndex = 1; houseIndex < numHouses; houseIndex++) {
                // Pichle row ke do sabse kam costs (min1 aur min2) ko track karne ke liye
                // variables.
                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;

                // `previousRow` mein se do sabse chote costs dhoondo.
                for (int cost : previousRow) {
                    if (cost < min1) {
                        min2 = min1;
                        min1 = cost;
                    } else if (cost < min2) {
                        min2 = cost;
                    }
                }

                // Current ghar ke liye ek nayi `currentRow` array banake uske original costs se
                // initialize karo.
                int[] currentRow = costs[houseIndex].clone();
                // Current ghar ke har rang ke liye minimum cost calculate karo.
                for (int curColor = 0; curColor < numColors; curColor++) {
                    // Agar pichle ghar ka rang `min1` wala tha, to hum `min2` use karenge.
                    // Aisa isliye taaki adjacent gharon ka rang same na ho.
                    if (previousRow[curColor] == min1) {
                        currentRow[curColor] += min2;
                    } else {
                        // Nahi to `min1` use karenge.
                        currentRow[curColor] += min1;
                    }
                }
                // Agle iteration ke liye `previousRow` ko `currentRow` se update kar do.
                previousRow = currentRow;
            }

            // Aakhri main`previousRow` mein se sabse kam cost dhoondh kar return karo.
            return Arrays.stream(previousRow).min().getAsInt();
        }
    }

    // revised on 12/27/2025
    public class SolutionRevisedOnSeventhDay {

        public int minCostII(int[][] costs) {
            // Edge case check: Agar costs array null ya empty hai, to 0 return karo.
            if (costs == null || costs.length == 0 || costs[0].length == 0) {
                return 0;
            }

            // Gharon (houses) aur rangon (colors) ka count nikal lo.
            int numHouses = costs.length;
            int numColors = costs[0].length;

            // `previousRow` array ko pehle ghar ke costs se initialize karo.
            // Yeh array har step pe pichle ghar tak ka minimum cost store karegi.
            int[] previousRow = costs[0].clone();

            // Dusre ghar se aakhri ghar tak loop chalao.
            for (int houseIndex = 1; houseIndex < numHouses; houseIndex++) {
                // Pichle row ke do sabse kam costs (min1 aur min2) ko track karne ke liye
                // variables.
                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;

                // `previousRow` mein se do sabse chote costs dhoondo.
                for (int cost : previousRow) {
                    if (cost < min1) {
                        min2 = min1;
                        min1 = cost;
                    } else if (cost < min2) {
                        min2 = cost;
                    }
                }

                // Current ghar ke liye ek nayi `currentRow` array banake uske original costs se
                // initialize karo.
                int[] currentRow = costs[houseIndex].clone();
                // Current ghar ke har rang ke liye minimum cost calculate karo.
                for (int curColor = 0; curColor < numColors; curColor++) {
                    // Agar pichle ghar ka rang `min1` wala tha, to hum `min2` use karenge.
                    // Aisa isliye taaki adjacent gharon ka rang same na ho.
                    if (previousRow[curColor] == min1) {
                        currentRow[curColor] += min2;
                    } else {
                        // Nahi to `min1` use karenge.
                        currentRow[curColor] += min1;
                    }
                }
                // Agle iteration ke liye `previousRow` ko `currentRow` se update kar do.
                previousRow = currentRow;
            }

            // Aakhri main`previousRow` mein se sabse kam cost dhoondh kar return karo.
            return Arrays.stream(previousRow).min().getAsInt();
        }
    }

    // revised on 1/10/2026
    public class SolutionRevisedOnFourteenDay {

        public int minCostII(int[][] costs) {
            // Edge case check: Agar costs array null ya empty hai, to 0 return karo.
            if (costs == null || costs.length == 0 || costs[0].length == 0) {
                return 0;
            }

            // Gharon (houses) aur rangon (colors) ka count nikal lo.
            int numHouses = costs.length;
            int numColors = costs[0].length;

            // `previousRow` array ko pehle ghar ke costs se initialize karo.
            // Yeh array har step pe pichle ghar tak ka minimum cost store karegi.
            int[] previousRow = costs[0].clone();

            // Dusre ghar se aakhri ghar tak loop chalao.
            for (int houseIndex = 1; houseIndex < numHouses; houseIndex++) {
                // Pichle row ke do sabse kam costs (min1 aur min2) ko track karne ke liye
                // variables.
                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;

                // `previousRow` mein se do sabse chote costs dhoondo.
                for (int cost : previousRow) {
                    if (cost < min1) {
                        min2 = min1;
                        min1 = cost;
                    } else if (cost < min2) {
                        min2 = cost;
                    }
                }

                // Current ghar ke liye ek nayi `currentRow` array banake uske original costs se
                // initialize karo.
                int[] currentRow = costs[houseIndex].clone();
                // Current ghar ke har rang ke liye minimum cost calculate karo.
                for (int curColor = 0; curColor < numColors; curColor++) {
                    // Agar pichle ghar ka rang `min1` wala tha, to hum `min2` use karenge.
                    // Aisa isliye taaki adjacent gharon ka rang same na ho.
                    if (previousRow[curColor] == min1) {
                        currentRow[curColor] += min2;
                    } else {
                        // Nahi to `min1` use karenge.
                        currentRow[curColor] += min1;
                    }
                }
                // Agle iteration ke liye `previousRow` ko `currentRow` se update kar do.
                previousRow = currentRow;
            }

            // Aakhri main`previousRow` mein se sabse kam cost dhoondh kar return karo.
            return Arrays.stream(previousRow).min().getAsInt();
        }
    }

    // revised on 2/8/2026
    class SolutionRevisedOnDayThirty {
        public int minCostII(int[][] costs) {

            if (costs == null || costs.length == 0 || costs[0].length == 0) {
                return 0;
            }

            int numOfHouses = costs.length;
            int numOfColors = costs[0].length;

            int[] previousRow = costs[0].clone();

            for (int houseIndex = 0; houseIndex < numOfHouses; houseIndex++) {

                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;

                for (int cost : previousRow) {

                    if (cost < min1) {
                        min2 = min1;
                        min1 = cost;
                    } else {
                        min2 = cost;
                    }
                }

                int[] currentRow = costs[houseIndex].clone();

                for (int curColor = 0; curColor < numOfColors; curColor++) {

                    if (previousRow[curColor] == min1) {
                        currentRow[curColor] += min2;
                    } else {
                        currentRow[curColor] += min1;
                    }
                }

                previousRow = currentRow;
            }

            return Arrays.stream(previousRow).min().getAsInt();
        }
    }
}
