/**
 * @author akash
 * @date Aug 06, 2026
 * @time 7:01:07 PM
 */
package main.pattern.slidingwindow.size.variable;

import java.util.*;

public class FruitIntoBaskets {
    class Solution {
        public int totalFruit(int[] fruits) {
            int max = 0;
            int left = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int right = 0; right < fruits.length; right++) {

                map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

                while (map.size() > 2) {
                    map.put(fruits[left], map.getOrDefault(fruits[left], 0) - 1);
                    if (map.get(fruits[left]) == 0) {
                        map.remove(fruits[left]);
                    }
                    left++;
                }

                max = Math.max(max, right - left + 1);
            }

            return max;
        }
    }
}
