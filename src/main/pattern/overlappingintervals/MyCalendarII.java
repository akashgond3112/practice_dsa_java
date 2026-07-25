/**
 * @author akash
 * @date Jul 25, 2026
 * @time 8:52:49 AM
 */
package main.pattern.overlappingintervals;

import java.util.SortedMap;
import java.util.TreeMap;

public class MyCalendarII {

    class MyCalendarTwo {

        SortedMap<Integer, Integer> map;

        public MyCalendarTwo() {
            this.map = new TreeMap<>();
        }

        public boolean book(int startTime, int endTime) {
            // add the event to the sweep-line map
            map.put(startTime, map.getOrDefault(startTime, 0) + 1);
            map.put(endTime, map.getOrDefault(endTime, 0) - 1);

            // check for triple booking
            int ongoing = 0;
            for (int delta : map.values()) {
                ongoing += delta;
                if (ongoing > 2) {
                    // rollback the tentative booking
                    map.put(startTime, map.get(startTime) - 1);
                    if (map.get(startTime) == 0)
                        map.remove(startTime);

                    map.put(endTime, map.get(endTime) + 1);
                    if (map.get(endTime) == 0)
                        map.remove(endTime);

                    return false;
                }
            }

            return true;
        }
    }

    /**
     * Your MyCalendarTwo object will be instantiated and called as such:
     * MyCalendarTwo obj = new MyCalendarTwo();
     * boolean param_1 = obj.book(startTime,endTime);
     */
}
