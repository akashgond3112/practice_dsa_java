package main.revision.october.meta.easy;

import java.util.HashMap;

public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');

        if (s.length() == 1) {
            return "0".equals(s) || "1".equals(s) || "8".equals(s);
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (map.containsKey(s.charAt(left)) && map.get(s.charAt(left)) != (s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
