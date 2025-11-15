package main.revision.october.meta.hard;

import java.util.*;

public class InsertDeleteGetRandomDuplicatesAllowed {

    class RandomizedCollection {

        List<Integer> values;
        Map<Integer, Set<Integer>> map;
        Random rand;

        public RandomizedCollection() {
            this.values = new ArrayList<>();
            this.map = new HashMap<>();
            this.rand = new Random();
        }

        public boolean insert(int val) {
            boolean notPresent = !map.containsKey(val);
            map.computeIfAbsent(val, k -> new HashSet<>()).add(values.size());
            values.add(val);
            return notPresent;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val) || map.get(val).isEmpty()) {
                return false;
            }
            // Get an arbitrary index of the value to remove
            int indexToRemove = map.get(val).iterator().next();
            map.get(val).remove(indexToRemove);

            int lastIndex = values.size() - 1;
            int lastVal = values.get(lastIndex);

            // Move the last element to the place of the element to remove
            values.set(indexToRemove, lastVal);

            // Update the map for the last value
            map.get(lastVal).remove(lastIndex);
            if (indexToRemove != lastIndex) {
                map.get(lastVal).add(indexToRemove);
            }

            values.remove(lastIndex);

            // Only remove the key if its set is empty after removal
            if (map.get(val).isEmpty()) {
                map.remove(val);
            }

            return true;
        }

        public int getRandom() {
            int randomIndex = rand.nextInt(values.size());
            return values.get(randomIndex);
        }
    }

    // revised on 05/11/2025
    class RandomizedCollectionRevisionThirdDay {

        List<Integer> values;
        Map<Integer, Set<Integer>> map;
        Random rand;

        public RandomizedCollectionRevisionThirdDay() {
            this.values = new ArrayList<>();
            this.map = new HashMap<>();
            this.rand = new Random();
        }

        public boolean insert(int val) {
            boolean notPresent = !map.containsKey(val);
            map.computeIfAbsent(val, k -> new HashSet<>()).add(values.size());
            values.add(val);
            return notPresent;
        }

        public boolean remove(int val) {

            if (!map.containsKey(val) && map.get(val).isEmpty()) {
                return false;
            }

            int indexToRemove = map.get(val).iterator().next();
            map.get(val).remove(indexToRemove);

            int lastIndex = values.size();
            int lastVal = values.get(lastIndex);

            values.set(indexToRemove, lastVal);

            map.get(lastVal).remove(lastIndex);
            if (indexToRemove != lastIndex) {
                map.get(lastVal).add(indexToRemove);
            }

            values.remove(lastIndex);

            if (map.get(val).isEmpty()) {
                map.remove(val);
            }

            return true;
        }

        public int getRandom() {
            int randomIndex = rand.nextInt(values.size());
            return values.get(randomIndex);
        }
    }
}
