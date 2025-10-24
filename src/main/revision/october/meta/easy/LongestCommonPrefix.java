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
}
