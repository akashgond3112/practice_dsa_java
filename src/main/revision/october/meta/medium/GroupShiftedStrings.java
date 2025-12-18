package main.revision.october.meta.medium;

import java.util.*;

/*
Problem Description
You are given an array of strings and need to group strings that belong to the same "shifting sequence".

A shifting sequence is formed by repeatedly shifting letters in a string:

Right shift: Replace each letter with the next letter in the alphabet (a→b, b→c, ..., z→a)
Left shift: Replace each letter with the previous letter in the alphabet (z←a, b←a, ..., a←z)
For example, the strings "abc", "bcd", "xyz", "yza", and "zab" all belong to the same shifting sequence because:

"abc" can be right-shifted to "bcd"
"bcd" can be right-shifted multiple times to eventually get "xyz"
"xyz" can be right-shifted to "yza"
"yza" can be right-shifted to "zab"
"zab" can be right-shifted to "abc" (completing the cycle)
The key insight is that two strings belong to the same shifting sequence if and only if they have the same pattern of 
differences between consecutive characters. For instance, "abc" and "xyz" both have the pattern where 
each character is exactly 1 position after the previous character in the alphabet.

The solution works by normalizing each string to start with the letter 'a' while preserving the relative 
differences between characters. All strings that normalize to the same pattern belong to the same group. The algorithm:

For each string, calculate how many positions to shift so the first character becomes 'a'
Apply this same shift to all characters in the string (wrapping around if needed)
Use the resulting normalized string as a key to group the original strings
Return the groups in any order.
*/
public class GroupShiftedStrings {

    class Solution {

        private static String getKey(String s) {

            StringBuilder key = new StringBuilder();

            for (int i = 1; i < s.length(); i++) {

                char c = s.charAt(i);
                char prev = s.charAt(i - 1);

                int diff = c - prev;

                if (diff < 0) {
                    diff += 26;
                }

                key.append(diff).append("#");
            }

            return key.toString();
        }

        public ArrayList<ArrayList<String>> groupShiftedString(String[] arr) {
            Map<String, List<String>> map = new HashMap<>();

            for (String str : arr) {

                String key = getKey(str);

                if (!map.containsKey(key)) {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    map.put(key, list);
                } else {
                    map.get(key).add(str);
                }
            }

            ArrayList<ArrayList<String>> res = new ArrayList<>();
            for (List<String> group : map.values()) {
                res.add(new ArrayList<>(group));
            }
            return res;
        }
    }

    // revised on 11/23/2025
    class SolutionRevisedOnThirdDay {

        public ArrayList<ArrayList<String>> groupShiftedString(String[] arr) {

            Map<String, List<String>> map = new HashMap<>();

            for (String str : arr) {

                String key = getKey(str);

                if (!map.containsKey(key)) {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    map.put(key, list);
                } else {
                    map.get(key).add(str);
                }
            }

            ArrayList<ArrayList<String>> res = new ArrayList<>();

            for (List<String> group : map.values()) {
                res.add(new ArrayList<>(group));
            }

            return res;
        }

        private static String getKey(String s) {
            StringBuilder key = new StringBuilder();

            for (int i = 1; i < s.length(); i++) {

                char c = s.charAt(i);
                char prev = s.charAt(i - 1);

                int diff = c - prev;

                if (diff < 0) {
                    diff += 26;
                }

                key.append(diff).append('#');
            }

            return key.toString();
        }
    }

    // revised on 11/23/2025
    class SolutionRevisedOnSeventhDay {

        public ArrayList<ArrayList<String>> groupShiftedString(String[] arr) {

            Map<String, List<String>> map = new HashMap<>();

            for (String str : arr) {

                String key = getKey(str);

                if (!map.containsKey(key)) {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(key);
                    map.put(key, tmp);
                } else {
                    map.get(key).add(str);
                }
            }

            ArrayList<ArrayList<String>> result = new ArrayList<>();

            for (List<String> list : map.values()) {
                result.add(new ArrayList<>(list));
            }

            return result;
        }

        private static String getKey(String s) {

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < s.length(); i++) {

                char cur = s.charAt(i);
                char prev = s.charAt(i - 1);

                int diff = cur - prev;

                if (diff < 0) {
                    diff += 26;
                }

                sb.append(diff).append('#');
            }
            return sb.toString();
        }
    }

    // revised on 12/13/2025
    class SolutionRevisedOnFourteenDay {

        public ArrayList<ArrayList<String>> groupShiftedString(String[] arr) {

            Map<String, List<String>> map = new HashMap<>();

            for (String str : arr) {

                String key = getKey(str);

                if (!map.containsKey(key)) {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(str);
                    map.put(str, tmp);
                } else {
                    map.get(key).add(str);
                }
            }

            ArrayList<ArrayList<String>> result = new ArrayList<>();

            for (List<String> list : map.values()) {
                result.add(new ArrayList<>(list));
            }

            return result;

        }

        private static String getKey(String s) {

            StringBuilder key = new StringBuilder();

            for (int i = 1; i < s.length(); i++) {

                char c = s.charAt(i);
                int pc = s.charAt(i - 1);
                int diff = c - pc;

                if (diff < 0) {
                    diff += 26;
                }

                key.append(diff).append('#');
            }

            return key.toString();
        }
    }
}
