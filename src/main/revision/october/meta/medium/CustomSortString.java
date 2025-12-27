package main.revision.october.meta.medium;

import java.util.*;

public class CustomSortString {
    class Solution {
        public String customSortString(String order, String s) {

            // Step 1: Har character ka frequency count karne ke liye ek map banate hain
            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            }

            // Step 2: Ek StringBuilder banate hain jo result store karega
            StringBuilder sb = new StringBuilder();

            // Step 3: Order ke characters ko map ke according process karte hain
            for (char c : order.toCharArray()) {

                // Jab tak map me character ka count > 0 hai, usse result me add karte hain
                while (map.containsKey(c) && map.get(c) > 0) {
                    sb.append(c);
                    map.put(c, map.get(c) - 1); // Count reduce karte hain
                }
            }

            // Step 4: Jo characters order me nahi hain, unhe map se uthake result me add
            // karte hain
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                char c = entry.getKey();
                int count = entry.getValue();

                // Jab tak count > 0 hai, character ko result me add karte hain
                while (count > 0) {
                    sb.append(c);
                    count--;
                }
            }

            // Step 5: Final result ko string me convert karke return karte hain
            return sb.toString();
        }
    }

    // revised on 03/11/2025
    class SolutionRevisionThirdDay {
        public String customSortString(String order, String s) {

            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
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

    // revised on 11/9/2025
    class SolutionRevisionSeventhDay {
        public String customSortString(String order, String s) {

            Map<Character, Integer> map = new HashMap<>();

            for (char c : s.toCharArray()) {

                map.put(c, map.getOrDefault(c, 0) + 1);
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

    // revised on 11/23/2025
    class SolutionRevisionFourteenDay {
        public String customSortString(String order, String s) {

            Map<Character, Integer> map = new HashMap<>();

            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
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

    // revised on 12/22/2025
    class SolutionRevisionOnDayThirty {
        public String customSortString(String order, String s) {

            Map<Character, Integer> map = new HashMap<>();

            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            StringBuilder sb = new StringBuilder();

            for (char c : order.toCharArray()) {

                if (map.get(c) > 0) {
                    sb.append(c);
                    map.put(c, map.getOrDefault(c, 0) - 1);
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
