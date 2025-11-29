package main.revision.october.meta.easy;

import java.util.*;

import main.revision.october.meta.Pair;

public class DotProductOfTwoSparseVectors {

    public class Solution {
        private final List<Pair<Integer, Integer>> nonZeroes;

        Solution(int[] nums) {
            nonZeroes = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nonZeroes.add(new Pair<Integer, Integer>(i, nums[i]));
                }
            }
        }

        public int dotProdcut(Solution vec) {
            int result = 0;

            int p1 = 0;
            int p2 = 0;

            while (p1 < nonZeroes.size() && p2 < vec.nonZeroes.size()) {
                Pair<Integer, Integer> pair_1 = nonZeroes.get(p1);
                Pair<Integer, Integer> pair_2 = vec.nonZeroes.get(p2);

                if (pair_1.getKey().equals(pair_2.getKey())) {
                    result += pair_1.getValue() + pair_2.getValue();
                    p1++;
                    p2++;
                } else if (pair_1.getKey() < pair_2.getKey()) {
                    p1++;
                } else {
                    p2++;
                }
            }

            return result;
        }
    }

    // Revised on 29/10/2025
    public class SolutionRevisionSeventhDay {
        private final List<Pair<Integer, Integer>> nonZeroes;

        SolutionRevisionSeventhDay(int[] nums) {
            nonZeroes = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {

                if (nums[i] != 0) {
                    nonZeroes.add(new Pair<>(i, nums[i]));
                }
            }
        }

        public int dotProduct(SolutionRevisionFourteenDay vec) {
            int result = 0;

            int p1 = 0;
            int p2 = 0;

            while (p1 < nonZeroes.size() && p2 < vec.nonZeroes.size()) {
                Pair<Integer, Integer> pair_1 = nonZeroes.get(p1);
                Pair<Integer, Integer> pair_2 = nonZeroes.get(p2);

                if (pair_1.getKey().equals(pair_1.getKey())) {
                    result += pair_1.getValue() + pair_2.getValue();
                    p1++;
                    p2++;
                } else if (pair_1.getKey() < pair_2.getKey()) {
                    p1++;
                } else {
                    p2++;
                }
            }

            return result;
        }

    }

    // Revised on 11/27/2025
    public class SolutionRevisionFourteenDay {
        private final List<Pair<Integer, Integer>> nonZeroes;

        SolutionRevisionFourteenDay(int[] nums) {
            this.nonZeroes = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {

                if (nums[i] != 0) {
                    this.nonZeroes.add(new Pair<>(i, nums[i]));
                }
            }
        }

        public int dotProduct(SolutionRevisionFourteenDay vec) {
            int result = 0;

            int p1 = 0;
            int p2 = 0;

            while (p1 < nonZeroes.size() && p2 < nonZeroes.size()) {

                Pair<Integer, Integer> pair1 = nonZeroes.get(p1);
                Pair<Integer, Integer> pair2 = nonZeroes.get(p2);

                if (pair1.getKey().equals(pair2.getKey())) {
                    result += pair1.getValue() + pair2.getValue();
                    p1++;
                    p2++;
                } else if (pair1.getKey() < pair2.getKey()) {
                    p1++;
                } else {
                    p2++;
                }
            }

            return result;
        }

    }

    // Revised on 11/27/2025
    public class SolutionRevisionThirtyDay {
        private final List<Pair<Integer, Integer>> nonZeroes;

        SolutionRevisionThirtyDay(int[] nums) {
            this.nonZeroes = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    this.nonZeroes.add(new Pair<>(i, nums[i]));
                }
            }
        }

        public int dotProduct(SolutionRevisionFourteenDay vec) {

            int result = 0;

            int p1 = 0;
            int p2 = 0;

            while (p1 < nonZeroes.size() & p2 < nonZeroes.size()) {

                Pair<Integer, Integer> pair1 = nonZeroes.get(p1);
                Pair<Integer, Integer> pair2 = nonZeroes.get(p2);

                if (pair1.getKey().equals(pair2.getKey())) {
                    result += pair1.getValue() + pair2.getValue();
                    p1++;
                    p2++;
                } else if (pair1.getKey() < pair2.getKey()) {
                    p1++;
                } else {
                    p2++;
                }
            }
            return result;
        }
    }

}
