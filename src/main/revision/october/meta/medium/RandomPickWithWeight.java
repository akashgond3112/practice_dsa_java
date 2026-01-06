package main.revision.october.meta.medium;

import java.util.*;

public class RandomPickWithWeight {

    public class Solution {
        private final int[] prefixSum;
        private final int totalWeight;
        private final Random rnd;

        public Solution(int[] w) {

            prefixSum = new int[w.length];
            prefixSum[0] = w[0];

            for (int i = 1; i < w.length; i++) {

                prefixSum[i] = prefixSum[i - 1] + w[i];
            }

            totalWeight = prefixSum[w.length - 1];
            rnd = new Random();
        }

        public int pickIndex() {

            int target = rnd.nextInt(totalWeight);

            int left = 0;
            int right = prefixSum.length - 1;

            while (left < right) {

                int mid = (left + right) / 2;

                if (prefixSum[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }

    // revised on 21/10/2025
    public class SolutionRevisionSeventhDay {
        private final int[] prefixSum;
        private final int totalWeight;
        private final Random rnd;

        public SolutionRevisionSeventhDay(int[] w) {

            this.prefixSum = new int[w.length];
            prefixSum[0] = w[0];

            for (int i = 1; i < w.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + w[i];
            }

            this.totalWeight = prefixSum[w.length - 1];
            this.rnd = new Random();
        }

        public int pickIndex() {

            int target = rnd.nextInt(totalWeight);

            int left = 0;
            int right = prefixSum.length - 1;

            while (left < right) {
                int mid = (left + right) / 2;

                if (prefixSum[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

    }

    // revised on 04/11/2025
    public class SolutionRevisionFourteenDay {
        private final int[] prefixSum;
        private final int totalWeight;
        private final Random rnd;

        public SolutionRevisionFourteenDay(int[] w) {

            this.prefixSum = new int[w.length];
            prefixSum[0] = w[0];

            for (int i = 0; i < w.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + w[i];
            }

            this.totalWeight = prefixSum[w.length - 1];
            rnd = new Random();
        }

        public int pickIndex() {

            int target = rnd.nextInt(totalWeight);

            int left = 0;
            int right = prefixSum.length - 1;

            while (left < right) {

                int mid = (left + right) / 2;

                if (prefixSum[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }
    }

    // revised on 04/11/2025
    public class SolutionRevisionThirtyDay {
        private final int[] prefixSum;
        private final int totalWeight;
        private final Random rnd;

        public SolutionRevisionThirtyDay(int[] w) {
            this.prefixSum = new int[w.length];
            prefixSum[0] = 0;

            for (int i = 0; i < w.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + w[i];
            }

            this.totalWeight = prefixSum[w.length - 1];
            rnd = new Random();
        }

        public int pickIndex() {

            int target = rnd.nextInt(totalWeight);

            int left = 0;
            int right = prefixSum.length - 1;

            while (left < right) {
                int mid = (left + right) / 2;

                if (prefixSum[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }
    }
}
