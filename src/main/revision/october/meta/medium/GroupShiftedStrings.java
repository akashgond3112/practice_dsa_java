package main.revision.october.meta.medium;

import java.util.*;

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
}
