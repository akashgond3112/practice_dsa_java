/**
 * @author agond
 * @date Jul 06, 2025
 * @time 7:55:45 AM
 */
package main.revision.meta.easy;

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int maxP = 0;
        int minBuy = prices[0];

        for (int sell : prices) {
            maxP = Math.max(maxP, sell - minBuy);
            minBuy = Math.min(minBuy, sell);
        }
        return maxP;
    }

}
