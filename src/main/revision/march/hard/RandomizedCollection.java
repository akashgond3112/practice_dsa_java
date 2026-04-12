package main.revision.march.hard;

import java.util.*;

public class RandomizedCollection {

    class RandomizedCollectionOnDayFirst {

        private final Map<Integer, Integer> map;
        private final List<Integer> nums;
        private final Random rnd;

        public RandomizedCollectionOnDayFirst() {
            this.map = new HashMap<>();
            this.nums = new ArrayList<>();
            this.rnd = new Random();
        }

        public boolean insert(int val) {

            if (map.containsKey(val)) {
                return true;
            }

            map.put(val, nums.size());
            return nums.add(val);
        }

        public boolean remove(int val) {

            if (!map.containsKey(val)) {
                return false;
            }

            int currentIndex = map.get(val);
            int lastElement = nums.getLast();

            nums.set(currentIndex, lastElement);
            map.put(lastElement, currentIndex);

            map.remove(val);
            nums.removeLast();

            return true;
        }

        public int getRandom() {
            return nums.get(rnd.nextInt(nums.size()));
        }
    }
}
