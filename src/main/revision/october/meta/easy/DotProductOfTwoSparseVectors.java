package main.revision.october.meta.easy;

import java.util.*;

import main.revision.october.meta.Pair;

public class DotProductOfTwoSparseVectors {

    private final List<Pair<Integer, Integer>> nonZeroes;

    DotProductOfTwoSparseVectors(int[] nums) {
        nonZeroes = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                nonZeroes.add(new Pair(i, nums[i]));
            }
        }
    }

    public int dotProdcut(DotProductOfTwoSparseVectors vec) {
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
