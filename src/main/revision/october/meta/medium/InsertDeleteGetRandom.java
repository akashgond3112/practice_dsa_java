package main.revision.october.meta.medium;

import java.util.*;

public class InsertDeleteGetRandom {

    class RandomizedSet {

        private final Map<Integer, Integer> numMap; // Maps value to its index in the list
        private final List<Integer> nums; // Stores the actual values
        private final Random rand; // Reusable Random object

        /**
         * Initializes an empty RandomizedSet data structure.
         */
        public RandomizedSet() {
            numMap = new HashMap<>();
            nums = new ArrayList<>();
            rand = new Random();
        }

        /**
         * Inserts an item val into the set if not present.
         * Returns true if the item was not present, false otherwise.
         *
         * @param val The value to insert
         * @return true if the item was not already present, false otherwise
         */
        public boolean insert(int val) {

            if (numMap.containsKey(val)) {
                return false;
            }
            numMap.put(val, nums.size());
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!numMap.containsKey(val)) {
                return false;
            }

            int index = numMap.get(val);
            int lastElement = nums.getLast();

            nums.set(index, lastElement);
            numMap.put(lastElement, index);

            nums.removeLast();
            numMap.remove(val);

            return true;
        }

        public int getRandom() {
            if (nums.isEmpty()) {
                throw new IllegalStateException("RandomizedSet is empty");
            }
            return nums.get(rand.nextInt(nums.size()));
        }
    }
}
