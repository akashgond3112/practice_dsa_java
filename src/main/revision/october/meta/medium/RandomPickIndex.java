package main.revision.october.meta.medium;

import java.util.Random;

public class RandomPickIndex {

    class Solution {

        private final int[] nums;
        private final Random rand;

        public Solution(int[] nums) {
            this.nums = nums;
            this.rand = new Random();
        }

        /**
         * Picks a random index of target value using reservoir sampling.
         *
         * Time Complexity: O(n) where n is the length of nums array Space Complexity:
         * O(1) as we only store a single
         * result
         *
         * @param target the value to randomly pick
         * @return random index of target, or -1 if target is not found
         */
        public int pick(int target) {
            int result = -1;
            int count = 0;

            for (int i = 0; i < nums.length; i++) {

                if (nums[i] == target) {
                    count++;
                }

                if (rand.nextInt(count) == 0) {
                    result = i;
                }
            }

            return result;
        }
    }

    // revised on 12/24/2025
    class SolutionRevisedOnThirdDay {
        private final int[] nums;
        private final Random rand;

        public SolutionRevisedOnThirdDay(int[] nums) {
            this.nums = nums;
            this.rand = new Random();
        }

        public int pick(int target) {
            int result = -1;
            int count = 0;
            int i = 0;

            for (int num : nums) {
                if (num == target) {
                    count++;
                }
                if (rand.nextInt(count) == 0) {
                    result = i;
                }
                i++;
            }

            return result;
        }
    }

    // revised on 12/30/2025
    class SolutionRevisedOnSeventhDay {
        private final int[] nums;
        private final Random rand;

        public SolutionRevisedOnSeventhDay(int[] nums) {
            this.nums = nums;
            this.rand = new Random();
        }

        public int pick(int target) {
            int result = -1;
            int count = 0;
            int i = 0;

            for (int num : nums) {
                if (num == target)
                    count++;

                if (rand.nextInt(count) == 0) {
                    result = i;
                }
                i++;
            }

            return result;
        }
    }

    // revised on 1/13/2026
    class SolutionRevisedOnDayFourteen {
        private final int[] nums;
        private final Random rnd;

        public SolutionRevisedOnDayFourteen(int[] nums) {
            this.nums = nums;
            this.rnd = new Random();
        }

        public int pick(int target) {
            int result = -1;
            int count = 0;
            int i = 0;

            for (int num : nums) {
                if (num == target) {
                    count++;
                }

                if (rnd.nextInt(count) == 0) {
                    result = i;
                }
                i++;
            }

            return result;
        }
    }

    // revised on 2/11/2026
    class SolutionRevisedOnDayThirty {

        private final int[] nums;
        private final Random rnd;

        public SolutionRevisedOnDayThirty(int[] nums) {
            this.nums = nums;
            this.rnd = new Random();
        }

        public int pick(int target) {
            int result = -1;
            int count = 0;

            for (int i = 0; i < nums.length; i++) {

                if (nums[i] == target)
                    count++;

                if (rnd.nextInt(count) == 0) {
                    result = i;
                }
            }

            return result;
        }
    }

}
