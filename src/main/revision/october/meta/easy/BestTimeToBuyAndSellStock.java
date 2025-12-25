package main.revision.october.meta.easy;

public class BestTimeToBuyAndSellStock {
    class Solution {
        public int maxProfit(int[] prices) {

            // Step 1: Sabse pehle minimum price ko initialize karte hain first element ke
            // saath
            int min = prices[0];

            // Step 2: Maximum profit ko 0 se initialize karte hain
            int maxProfit = 0;

            // Step 3: Array ke length ko n variable mein store karte hain
            int n = prices.length;

            // Step 4: Loop start karte hain second element se (index 1)
            for (int i = 1; i < n; i++) {

                // Step 5: Current price aur minimum price ka difference calculate karte hain
                int cost = prices[i] - min;

                // Step 6: Maximum profit ko update karte hain agar current cost zyada ho
                maxProfit = Math.max(maxProfit, cost);

                // Step 7: Minimum price ko update karte hain agar current price kam ho
                min = Math.min(min, prices[i]);
            }

            // Step 8: Maximum profit return karte hain
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

    // revised on 11/12/2025
    class SolutionrevisionOnSeventhDay {
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

    // revised on 11/26/2025
    class SolutionrevisionOnFourteenDay {
        public int maxProfit(int[] prices) {
            int min = prices[0];
            int max = 0;

            for (int i = 1; i < prices.length; i++) {

                int cost = prices[i] - min;

                max = Math.max(max, cost);
                min = Math.min(min, prices[i]);
            }

            return max;
        }
    }

    // revised on 12/25/2025
    class SolutionrevisionOnDayThirty {
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
