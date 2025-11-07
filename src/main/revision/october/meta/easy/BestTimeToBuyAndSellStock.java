package main.revision.october.meta.easy;

public class BestTimeToBuyAndSellStock {
    class Solution {
        public int maxProfit(int[] prices) {

            int min = prices[0];
            int maxProfit = 0;
            int n = prices.length;

            for (int i = 1; i < n; i++) {
                int cost = prices[i] - min;
                maxProfit = Math.max(maxProfit, cost);
                min = Math.min(min, prices[i]);
            }

            return maxProfit;
        }
    }

    // revised on 06/11/2025
    class SolutionrevisionOnThirdDay {
        public int maxProfit(int[] prices) {
            int min = prices[0];
            int max = 0;
            int n = prices.length;

            for (int i = 1; i < n; i++) {

                int cost = prices[i] - min;
                max = Math.max(max, cost);
                min = Math.min(min, prices[i]);
            }

            return max;
        }
    }
}
