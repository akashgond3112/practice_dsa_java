package main.revision.october.meta.easy;

import java.util.*;

public class LongestCommonPrefix {

    // Time Complexity: O(N * log(N)) -> Sorting array takes O(N * log(N)) time.
    // Space Complexity: O(1) -> No extra space used apart from input and output.
    class Solution {
        public String longestCommonPrefix(String[] strs) {

            // Step 1: Sort the array of strings lexicographically
            // Yeh step ensure karta hai ki sabse chhoti aur sabse badi strings array ke
            // first aur last position par ho.
            Arrays.sort(strs);

            // Step 2: Convert first and last strings into character arrays
            // Yeh karne se hum character by character comparison kar sakte hain.
            char[] first = strs[0].toCharArray();
            char[] last = strs[strs.length - 1].toCharArray();

            // Step 3: Initialize a StringBuilder to store the common prefix
            StringBuilder sb = new StringBuilder();

            // Step 4: Compare characters of first and last strings
            // Yeh loop tab tak chalega jab tak characters match karte hain.
            for (int i = 0; i < first.length; i++) {

                // Agar characters match nahi karte, toh loop break kar do
                if (first[i] != last[i]) {
                    break;
                }
                // Agar match karte hain, toh last string ka character append karo
                sb.append(last[i]);
            }

            // Step 5: Return the common prefix as a string
            return sb.toString();
        }
    }

    // Revision on 26/10/2025
    class SolutionRevisionThirdDay {
        public String longestCommonPrefix(String[] strs) {

            Arrays.sort(strs);

            char[] first = strs[0].toCharArray();
            char[] last = strs[strs.length - 1].toCharArray();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < first.length; i++) {

                if (first[i] != last[i]) {
                    break;
                }
                sb.append(first[i]);
            }

            return sb.toString();
        }
    }

    // Revision on 11/15/2025
    class SolutionRevisionFourteenDay {
        public String longestCommonPrefix(String[] strs) {

            Arrays.sort(strs);

            char[] first = strs[0].toCharArray();
            char[] last = strs[strs.length - 1].toCharArray();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < first.length; i++) {

                if (first[i] == last[i]) {
                    sb.append(first[i]);
                } else {
                    break;
                }
            }

            return sb.toString();
        }
    }

    // Revision on 12/14/2025
    class SolutionRevisionThirtyDay {
        public String longestCommonPrefix(String[] strs) {

            Arrays.sort(strs);

            char[] first = strs[0].toCharArray();
            char[] last = strs[strs.length - 1].toCharArray();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < first.length; i++) {

                if (first[i] == last[i]) {
                    sb.append(first[i]);
                } else {
                    break;
                }
            }

            return sb.toString();
        }
    }
}
