/**
 * @author agond
 * @date Sep 28, 2025
 * @time 9:05:22 AM
 */
package main.interview.amazon;

import java.util.*;

public class MinDaysToDeliverParcels {

    public int minDaysToDeliverParcels(List<Integer> parcels) {
        // Edge case: empty list or null
        if (parcels == null || parcels.isEmpty()) {
            return 0;
        }

        int days = 0;
        List<Integer> remainingParcels = new ArrayList<>(parcels); // Create a mutable copy
        boolean hasRemainingParcels = true;

        while (hasRemainingParcels) {
            // Find the minimum number of parcels that can be delivered from centers with
            // parcels
            int minParcels = Integer.MAX_VALUE;
            int centersWithParcels = 0;

            // Count centers with parcels and find minimum parcels available
            for (int parcelsAtCenter : remainingParcels) {
                if (parcelsAtCenter > 0) {
                    minParcels = Math.min(minParcels, parcelsAtCenter);
                    centersWithParcels++;
                }
            }

            // If no centers have parcels, we're done
            if (centersWithParcels == 0) {
                break;
            }

            // Deliver parcels from each center that has parcels
            for (int i = 0; i < remainingParcels.size(); i++) {
                if (remainingParcels.get(i) > 0) {
                    remainingParcels.set(i, remainingParcels.get(i) - minParcels);
                }
            }

            // Add the number of days needed for this batch
            days += minParcels;

            // Check if any parcels remain
            hasRemainingParcels = false;
            for (int parcel : remainingParcels) {
                if (parcel > 0) {
                    hasRemainingParcels = true;
                    break;
                }
            }
        }

        return days;
    }

}
