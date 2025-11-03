package main.revision.october.meta.medium;

import java.util.*;

public class CustomSortString {
    class Solution {
        public String customSortString(String order, String s) {

            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            }

            StringBuilder sb = new StringBuilder();

            for (char c : order.toCharArray()) {

                while (map.containsKey(c) && map.get(c) > 0) {
                    sb.append(c);
                    map.put(c, map.get(c) - 1);
                }
            }

            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                char c = entry.getKey();
                int count = entry.getValue();

                while (count > 0) {
                    sb.append(c);
                    count--;
                }
            }

            return sb.toString();
        }
    }
}
