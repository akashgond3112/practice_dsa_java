package main.revision.march.hard;

import java.util.*;

public class InsertDeleteGetRandomDuplicatesAllowed {

    class RandomizedCollectionOnDayFirst {

        List<Integer> values;
        Map<Integer, Set<Integer>> map;
        Random rand;

        public RandomizedCollectionOnDayFirst() {
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

    class RandomizedCollectionOnDayThird {

        List<Integer> values;
        Map<Integer, Set<Integer>> map;
        Random rnd;

        public RandomizedCollectionOnDayThird() {
            this.map = new HashMap<>();
            this.values = new ArrayList<>();
            this.rnd = new Random();
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

            int indexToRemove = map.get(val).iterator().next();
            map.get(val).remove(indexToRemove);

            int lastIndex = values.size() - 1;
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

            return values.get(rnd.nextInt(values.size()));

        }
    }

}
