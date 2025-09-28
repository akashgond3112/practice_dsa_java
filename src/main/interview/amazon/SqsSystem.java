/**
 * @author agond
 * @date Sep 28, 2025
 * @time 10:49:07 AM
 */
package main.interview.amazon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SqsSystem {
    public static int getMaximumEvents(int[] payload) {
        Arrays.sort(payload);
        int max = 1;
        int current = 1;
        for (int i = 1; i < payload.length; i++) {
            if (payload[i] != payload[i - 1]) {
                current++;
                max = current;
            }
        }
        return max;
    }

    public static int getMaximumEventsOptimal(int[] payload) {
        Set<Integer> distinct = new HashSet<>();
        for (int p : payload) {
            distinct.add(p);
        }
        return distinct.size();
    }
}
