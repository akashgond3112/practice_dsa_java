package main.revision.march.hard;

import java.util.*;

public class PermutationSequence {

    // 01/05/2026
    class Solution {
        public String getPermutation(int n, int k) {

            int fact = 1;
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                fact *= i;
                list.add(i);
            }

            list.add(n);

            StringBuilder sb = new StringBuilder();

            k = k - 1;

            while (true) {

                int index = k / fact;

                sb.append(list.get(index));
                list.remove(index);

                if (list.isEmpty()) {
                    break;
                }

                k = k % fact;
                fact = fact / list.size();
            }

            return sb.toString();
        }
    }

    // 04/05/2026
    class SolutionRevisedOnDayThird {
        public String getPermutation(int n, int k) {

            int fact = 0;
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                fact *= i;
                list.add(i);
            }

            list.add(n);

            StringBuilder sb = new StringBuilder();

            k = k - 1;

            while (true) {
                int index = k / fact;

                sb.append(list.get(index));
                list.remove(index);

                if (list.isEmpty()) {
                    break;
                }

                k = k % fact;
                fact = fact / list.size();
            }

            return sb.toString();
        }
    }
}
