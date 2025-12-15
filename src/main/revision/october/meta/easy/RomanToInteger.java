package main.revision.october.meta.easy;

import java.util.*;

public class RomanToInteger {

    private static final Map<Character, Integer> ROMAN_VALUES = new HashMap<>();

    static {
        ROMAN_VALUES.put('I', 1);
        ROMAN_VALUES.put('V', 5);
        ROMAN_VALUES.put('X', 10);
        ROMAN_VALUES.put('L', 50);
        ROMAN_VALUES.put('C', 100);
        ROMAN_VALUES.put('D', 500);
        ROMAN_VALUES.put('M', 1000);
    }

    public class Solution {
        public int romanToInt(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }

            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                int currentVal = ROMAN_VALUES.get(s.charAt(i));
                // Check if there is a next character and if its value is greater
                if (i < s.length() - 1 && currentVal < ROMAN_VALUES.get(s.charAt(i + 1))) {
                    result -= currentVal;
                } else {
                    result += currentVal;
                }
            }
            return result;
        }
    }

    // revised on 12/15/2025
    public class SolutionRevisedOnThirdDay {
        public int romanToInt(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }

            int result = 0;

            for (int i = 0; i < s.length(); i++) {

                int curVal = ROMAN_VALUES.get(s.charAt(i));

                if (i < s.length() - 1 && curVal < ROMAN_VALUES.get(s.charAt(i + 1))) {
                    result -= curVal;
                } else {
                    result += curVal;
                }
            }

            return result;
        }
    }

}
