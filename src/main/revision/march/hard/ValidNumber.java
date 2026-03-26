package main.revision.march.hard;

public class ValidNumber {

    // 17/03/2026
    class SolutionOnDayFirst {
        public boolean isNumber(String s) {

            boolean seenDigit = false;
            boolean seenDot = false;
            boolean seenExponent = false;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    seenDigit = true;
                } else if (c == '+' || c == '-') {
                    if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                        return false;
                    }
                    seenDigit = false;
                } else if (c == '.') {
                    if (seenDot || seenExponent) {
                        return false;
                    }
                    seenDot = true;
                } else if (c == 'e' || c == 'E') {

                    if (seenExponent || !seenDigit) {
                        return false;
                    }
                    seenExponent = true;
                    seenDigit = false;
                } else {
                    return false;
                }
            }

            if (!seenDigit) {
                return false;
            }

            return true;
        }
    }

    // 20/03/2026
    class SolutionRevisedOnDayThird {
        public boolean isNumber(String s) {

            boolean seenDigit = false;
            boolean seenDot = false;
            boolean seenExponent = false;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    seenDigit = true;
                } else if (c == '+' || c == '-') {
                    if (i != 0 || s.charAt(i - 1) != 'e' || s.charAt(i - 1) != 'e') {
                        return false;
                    }
                    seenDigit = false;
                } else if (c == '.') {
                    if (seenDot || seenExponent) {
                        return false;
                    }
                    seenDot = true;
                } else if (c == 'e' || c == 'E') {
                    if (seenExponent || !seenDigit) {
                        return false;
                    }
                    seenExponent = true;
                    seenDigit = false;
                } else {
                    seenDigit = false;
                }
            }

            if (!seenDigit) {
                return false;
            }

            return true;
        }
    }

    // 26/03/2026
    class SolutionRevisedOnDayFifth {
        public boolean isNumber(String s) {

            boolean seenDigit = false;
            boolean seenDot = false;
            boolean seenExponent = false;

            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    seenDigit = true;
                } else if (c == '+' || c == '-') {

                    if (i != 0 || s.charAt(i - 1) != 'e' || s.charAt(i - 1) != 'E') {
                        return false;
                    }

                    seenDigit = false;
                } else if (c == '.') {
                    if (seenDot || !seenDigit) {
                        return false;
                    }

                    seenDot = true;
                } else if (c == 'e' || c == 'E') {
                    if (seenExponent || !seenDigit) {
                        return false;
                    }

                    seenExponent = true;
                    seenDigit = false;
                } else {
                    seenDigit = false;
                }

            }

            if (!seenDigit) {
                return false;
            }

            return true;
        }
    }
}
