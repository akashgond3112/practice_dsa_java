/**
 * @author akash
 * @date Sep 22, 2026
 * @time 7:23:52 PM
 */
package main.interview.stripe;

public class TieredPricingProblem {

    /**
     * <b>Input:</b> tiers = [[10, 50], [20, 40], [-1, 30]], quantity = 8
     * 
     * <b>Output:</b> 400
     * <b>Explanation:</b> 8 is within the first tier (up to 10), so all 8 units
     * cost 50 cents each.
     * 8 * 50 = 400 cents.
     * 
     * <b>Input:</b> tiers = [[10, 50], [20, 40], [-1, 30]], quantity = 25
     * 
     * <b>Output:</b> 750
     * <b>Explanation:</b> 25 is past the last finite bound (20), so it falls in the
     * unbounded tier at
     * 30 cents. 25 * 30 = 750 cents.
     * 
     */
    public static int volumePrice(int[][] tiers, int quantity) {
        if (quantity <= 0 || tiers == null)
            return 0;

        for (int[] tier : tiers) {
            if (tier == null || tier.length < 2)
                continue;

            int upTo = tier[0];
            int unitAmount = tier[1];

            // Case 1: this is the catch-all/unbounded tier — always matches
            if (upTo == -1) {
                return quantity * unitAmount;
            }

            // Case 2: quantity fits within this tier's finite cap
            if (quantity <= upTo) {
                return quantity * unitAmount;
            }
        }
        return 0;
    }

    /**
     * <b>Input:</b> tiers = [[10, 50], [20, 40], [-1, 30]], quantity = 15
     * 
     * <b>Output:</b> 700
     * <b>Explanation:</b> The first 10 units fill tier 1 at 50 cents (500), the
     * next 5 fall in tier 2 at
     * 40 cents (200). 500 + 200 = 700 cents. Compare with volume pricing, which
     * charged 600 for the
     * same 15 units: graduated always costs at least as much, because the early
     * units keep their higher
     * rate.
     * 
     */
    public static int volumePricePartTwo(int[][] tiers, int quantity) {
        if (quantity <= 0 || tiers == null)
            return 0;

        int total = 0;
        int covered = 0;
        int remaining = quantity;

        for (int[] tier : tiers) {
            if (remaining > 0 && tier != null && tier.length >= 2) {
                int upTo = tier[0];
                int unitAmount = tier[1];

                int tierUnits = (upTo == -1) ? remaining : Math.min(remaining, upTo - covered);

                total += unitAmount * tierUnits;
                remaining -= tierUnits;

                if (upTo != -1) {
                    covered = upTo;
                }
            }
        }
        return total;
    }

    /**
     * <b>Input:</b> tiers = [[10, 50], [20, 40], [-1, 30]], budget = 745
     * 
     * <b>Output:</b> 16
     * <b>Explanation:</b> 16 units cost 500 + 6*40 = 740, which fits in 745. A 17th
     * unit would cost 40
     * more (780 total), over budget. The 5 leftover cents are not enough for
     * another unit.
     * 
     */
    public static int maxUnits(int[][] tiers, int budget) {

        if (budget <= 0 || tiers == null)
            return 0;

        int units = 0;
        int covered = 0;
        int remainingBudget = budget;

        for (int[] tier : tiers) {
            if (tier != null && tier.length >= 2) {
                int upTo = tier[0];
                int unitAmount = tier[1];

                int affordable = remainingBudget / unitAmount;

                if (upTo == -1 || affordable < upTo - covered) {
                    return units + affordable;
                }

                int tierCapacity = upTo - covered;
                units += tierCapacity;
                remainingBudget -= tierCapacity * unitAmount;
                covered = upTo;
            }
        }
        return units;
    }

}
