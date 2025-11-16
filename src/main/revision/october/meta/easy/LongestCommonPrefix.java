package main.revision.october.meta.easy;

import java.util.*;

public class LongestCommonPrefix {

    class Solution {
        public String longestCommonPrefix(String[] strs) {

            Arrays.sort(strs);

            char[] first = strs[0].toCharArray();
            char[] last = strs[strs.length - 1].toCharArray();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < first.length; i++) {

                if (first[i] != last[i]) {
                    break;
                }
                sb.append(last[i]);

            }

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
}
