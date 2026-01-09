package main.revision.october.meta.medium;

import java.util.*;

public class InsertDeleteGetRandom {

    class RandomizedSet {

        private final Map<Integer, Integer> numMap; // Value ko uske index ke saath map karta hai
        private final List<Integer> nums; // Values ko store karne ke liye list
        private final Random rand; // Random object reuse karne ke liye

        /**
         * RandomizedSet data structure ko initialize karta hai.
         */
        public RandomizedSet() {
            numMap = new HashMap<>(); // Value aur index ka mapping store karne ke liye
            nums = new ArrayList<>(); // Values ko store karne ke liye list
            rand = new Random(); // Random object create karte hain
        }

        /**
         * Agar value present nahi hai to usse insert karta hai.
         * Agar value pehle se present hai to false return karta hai.
         *
         * @param val Insert karne wali value
         * @return true agar value insert hui, false agar pehle se present thi
         */
        public boolean insert(int val) {
            // Check karte hain agar value pehle se present hai
            if (numMap.containsKey(val)) {
                return false; // Agar present hai to false return karo
            }
            // Value ko map aur list dono mein add karo
            numMap.put(val, nums.size()); // Value ka index map mein store karo
            nums.add(val); // Value ko list mein add karo
            return true; // Insert successful
        }

        /**
         * Value ko remove karta hai agar present ho.
         * Agar value present nahi hai to false return karta hai.
         *
         * @param val Remove karne wali value
         * @return true agar value remove hui, false agar present nahi thi
         */
        public boolean remove(int val) {
            // Check karte hain agar value present nahi hai
            if (!numMap.containsKey(val)) {
                return false; // Agar present nahi hai to false return karo
            }

            // Value ka index aur last element ko retrieve karo
            int index = numMap.get(val); // Value ka index map se lo
            int lastElement = nums.getLast(); // List ka last element lo

            // Last element ko remove hone wali value ke index pe shift karo
            nums.set(index, lastElement); // Index pe last element set karo
            numMap.put(lastElement, index); // Map mein last element ka naya index update karo

            // List aur map se remove hone wali value ko delete karo
            nums.remove(nums.size() - 1); // List se last element remove karo
            numMap.remove(val); // Map se value remove karo

            return true; // Remove successful
        }

        /**
         * Randomly ek value return karta hai jo set mein present hai.
         *
         * @return Random value jo set mein present hai
         */
        public int getRandom() {
            // Check karte hain agar set empty hai
            if (nums.isEmpty()) {
                throw new IllegalStateException("RandomizedSet is empty"); // Agar empty hai to exception throw karo
            }
            // Random index generate karke value return karo
            return nums.get(rand.nextInt(nums.size())); // Random index se value return karo
        }
    }

    // revised on 12/20/2025
    class RandomizedSetRevisedOnThirdDay {

        private final Map<Integer, Integer> map;
        private final List<Integer> nums;
        private final Random rnd;

        public RandomizedSetRevisedOnThirdDay() {
            this.map = new HashMap<>();
            this.nums = new ArrayList<>();
            this.rnd = new Random();
        }

        public boolean insert(int val) {

            if (map.containsKey(val))
                return false;

            map.put(val, nums.size());
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {

            if (!map.containsKey(val))
                return false;

            int index = map.get(val);
            int lastElement = nums.getLast();

            nums.add(index, lastElement);
            map.put(lastElement, index);

            nums.removeLast();
            map.remove(val);
            return true;
        }

        public int getRandom() {

            if (nums.isEmpty()) {
                throw new IllegalStateException("RandomizedSet is empty");
            }

            return nums.get(rnd.nextInt(nums.size()));
        }
    }

    // revised on 12/26/2025
    class RandomizedCollectionRevisedOnSeventhDay {

        public Map<Integer, Integer> map;
        public List<Integer> values;
        public Random rnd;

        public RandomizedCollectionRevisedOnSeventhDay() {
            this.map = new HashMap<>();
            this.values = new ArrayList<>();
            this.rnd = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, values.size());
            values.add(val);
            return true;
        }

        public boolean remove(int val) {

            if (!map.containsKey(val)) {
                return false;
            }

            int currentIndex = map.get(val);
            int lastElement = values.getLast();

            values.add(currentIndex, lastElement);
            map.put(lastElement, currentIndex);

            map.remove(val);
            values.removeLast();
            return true;
        }

        public int getRandom() {

            if (values.isEmpty()) {
                throw new IllegalStateException("RandomizedSet is empty");
            }

            return values.get(rnd.nextInt(values.size()));
        }
    }

    // revised on 1/9/2026
    class RandomizedSetRevisedOnFourteenDay {

        public Map<Integer, Integer> map;
        public List<Integer> values;
        public Random rnd;

        public RandomizedSetRevisedOnFourteenDay() {
            map = new HashMap<>();
            values = new ArrayList<>();
            rnd = new Random();
        }

        public boolean insert(int val) {

            if (map.containsKey(val)) {
                return false;
            }

            values.add(val);
            map.put(val, values.size());
            return true;
        }

        public boolean remove(int val) {

            if (!map.containsKey(val)) {
                return false;
            }

            int index = map.get(val);
            int lastElement = values.getLast();

            values.set(index, lastElement);
            map.put(lastElement, index);

            values.removeLast();
            map.remove(val);

            return true;
        }

        public int getRandom() {

            if (values.isEmpty()) {
                throw new IllegalStateException("");
            }
            return values.get(rnd.nextInt(values.size()));
        }
    }

}
