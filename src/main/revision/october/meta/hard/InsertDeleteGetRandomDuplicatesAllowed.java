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
            // Step 1: Check if val already exists in map
            // Agar val map mein nahi hai, to notPresent true hoga
            boolean notPresent = !map.containsKey(val);

            // Step 2: Add the index of val in the map
            // Agar val pehle se nahi hai, to ek nayi set banake usme index add karenge
            map.computeIfAbsent(val, k -> new HashSet<>()).add(values.size());

            // Step 3: Add val to the values list
            // values list mein val ko add karenge
            values.add(val);

            return notPresent;
        }

        /**
         * Removes one occurrence of val from the collection.
         * Example walkthrough for values = [10, 20, 10, 30], map = {10:[0,2], 20:[1],
         * 30:[3]}, remove(10):
         */
        public boolean remove(int val) {
            // Step 1: Check if val exists and has indices
            // Agar val map mein nahi hai ya uska set empty hai, to false return karenge
            if (!map.containsKey(val) || map.get(val).isEmpty()) {
                return false;
            }

            // Step 2: Get an arbitrary index of the value to remove
            // val ke kisi ek index ko remove karne ke liye nikalenge
            int indexToRemove = map.get(val).iterator().next();
            map.get(val).remove(indexToRemove);

            // Step 3: Get last element's index and value
            // List ke last element ka index aur value nikalenge
            int lastIndex = values.size() - 1;
            int lastVal = values.get(lastIndex);

            // Step 4: Move the last element to the place of the element to remove
            // Last element ko indexToRemove ki jagah shift karenge
            values.set(indexToRemove, lastVal);

            // Step 5: Update the map for the last value
            // Last value ke map ko update karenge (remove lastIndex, add indexToRemove agar
            // zarurat ho)
            map.get(lastVal).remove(lastIndex);
            if (indexToRemove != lastIndex) {
                map.get(lastVal).add(indexToRemove);
            }

            // Step 6: Remove the last element from the list
            // List ke last element ko remove karenge
            values.remove(lastIndex);

            // Step 7: Remove the key if its set is empty after removal
            // Agar val ka set empty ho gaya hai, to map se val ko remove karenge
            if (map.get(val).isEmpty()) {
                map.remove(val);
            }

            return true;
        }

        public int getRandom() {
            // Random index generate karke us index ka value return karenge
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

    // revised on 11/11/2025
    class RandomizedCollectionRevisionSeventhDay {

        List<Integer> values;
        Map<Integer, Set<Integer>> map;
        Random rnd;

        public RandomizedCollectionRevisionSeventhDay() {
            this.values = new ArrayList<>();
            this.map = new HashMap<>();
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

            int removeIndex = map.get(val).iterator().next();
            map.get(val).remove(removeIndex);

            int lastIndex = map.get(val).size() - 1;
            int lastVal = values.get(lastIndex);

            values.set(removeIndex, lastVal);

            map.get(lastVal).remove(lastIndex);
            if (removeIndex != lastIndex) {
                map.get(lastVal).add(removeIndex);
            }

            values.remove(lastIndex);

            if (map.get(val).isEmpty()) {
                map.remove(val);
            }

            return true;
        }

        public int getRandom() {
            int rndInx = rnd.nextInt(values.size());
            return values.get(rndInx);
        }
    }

    // revised on 11/25/2025
    class RandomizedCollectionRevisionFourteenDay {

        List<Integer> values;
        Map<Integer, Set<Integer>> map;
        Random rnd;

        public RandomizedCollectionRevisionFourteenDay() {
            this.values = new ArrayList<>();
            this.map = new HashMap<>();
            this.rnd = new Random();
        }

        public boolean insert(int val) {
            boolean notPresent = !map.containsKey(val);
            map.computeIfAbsent(val, k -> new HashSet<>()).add(values.size());
            values.add(val);
            return notPresent;
        }

        public int getRandom() {
            int index = rnd.nextInt(values.size());
            return values.get(index);
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
    }

    // revised on 12/24/2025
    class RandomizedCollectionRevisionThirtyDay {

        List<Integer> values;
        Map<Integer, Set<Integer>> map;
        Random rnd;

        public RandomizedCollectionRevisionThirtyDay() {
            this.values = new ArrayList<>();
            this.map = new HashMap<>();
            this.rnd = new Random();
        }

        public boolean insert(int val) {
            boolean isPresent = !map.containsKey(val);
            map.computeIfAbsent(val, k -> new HashSet<>()).add(values.size());
            values.add(val);
            return isPresent;
        }

        public int getRadom() {
            int index = rnd.nextInt(values.size());
            return values.get(index);
        }

        public boolean remove(int val) {
            if (!map.containsKey(val) && map.get(val).isEmpty()) {
                return false;
            }

            int indexToRemove = map.get(val).iterator().next();
            map.get(val).remove(indexToRemove);

            int lastIndex = values.getLast();
            int lastVal = values.get(lastIndex);

            values.set(lastIndex, lastVal);

            map.get(lastVal).remove(lastIndex);
            if (indexToRemove != lastIndex) {
                return false;
            }

            values.remove(lastIndex);

            if (map.get(val).isEmpty()) {
                return false;
            }

            return true;
        }
    }
}
