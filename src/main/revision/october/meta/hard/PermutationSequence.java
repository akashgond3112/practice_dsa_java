package main.revision.october.meta.hard;

import java.util.*;

public class PermutationSequence {

    class Solution {
        public String getPermutation(int n, int k) {
            // Factorial calculation aur list initialization
            int fact = 1;
            List<Integer> list = new ArrayList<>();

            // List ko 1 se n tak ke numbers se populate karte hain aur (n-1)! calculate
            // karte hain
            for (int i = 1; i < n; i++) {
                fact *= i;
                list.add(i);
            }
            list.add(n); // List ke end mein 'n' add karte hain

            // StringBuilder k-th permutation ko store karne ke liye
            StringBuilder sb = new StringBuilder();

            // k ko 0-based index mein adjust karte hain
            k = k - 1;

            while (true) {

                // Current digit ka index find karte hain jo permutation mein place hoga
                int index = k / fact;

                sb.append(list.get(index)); // Result mein digit ko append karte hain
                list.remove(index); // Used digit ko list se remove karte hain

                // Agar saare digits use ho gaye hain to loop break karte hain
                if (list.isEmpty()) {
                    break;
                }

                // Remaining digits ke liye k aur factorial ko recalculate karte hain
                k = k % fact;
                fact = fact / list.size();
            }

            // k-th permutation ko string ke form mein return karte hain
            return sb.toString();
        }
    }

    // revised on 12/16/2025
    class SolutionRevisedOnThirdDay {
        public String getPermutation(int n, int k) {
            int fact = 1;
            List<Integer> list = new ArrayList<>();

            for (int i = 1; i < n; i++) {
                fact *= i;
                list.add(i);
            }

            list.add(n);

            StringBuilder sb = new StringBuilder();

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

    // revised on 12/16/2025
    class SolutionRevisedOnSeventhDay {
        public String getPermutation(int n, int k) {
            int fact = 1;
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                fact *= i;
                list.add(i);
            }

            list.add(n);

            StringBuilder sb = new StringBuilder();

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

    // revised on 1/5/2026
    class SolutionRevisedOnDayFourteen {
        public String getPermutation(int n, int k) {

            int fact = 1;
            List<Integer> list = new ArrayList<>();

            for (int i = 1; i < n; i++) {
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
