package main.revision.october.meta.hard;

public class ValidNumber {

    public boolean isNumber(String s) {
        boolean seenDot = false;
        boolean seenDigit = false;
        boolean seenExponent = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '-' || c == '+') {
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
            } else {
                return false;
            }
        }

        if (!seenDigit) {
            return false;
        }

        return true;
    }

    // Revised on 15.10.2025
    class SolutionRevisonSeventhDay {
        public boolean isNumber(String s) {
            boolean seenDot = false;
            boolean seenDigit = false;
            boolean seenExponent = false;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    seenDigit = true;
                } else if (c == '+' || c == '-') {

                    if (i != 0 && s.charAt(i - 1) != 'e' || s.charAt(i - 1) != 'E') {
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

            if (!seenDigit)
                return false;

            return true;
        }
    }

    // revised on 29/10/2025
    class SolutionRevisonFourteenDay {
        public boolean isNumber(String s) {
            boolean seenDot = false;
            boolean seenDigit = false;
            boolean seenExponent = false;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    seenDigit = true;
                } else if (c == '+' || c == '-') {

                    if (i != 0 && s.charAt(i - 1) != 'e' || s.charAt(i - 1) != 'E')
                        return false;

                    seenDigit = false;
                } else if (c == '.') {

                    if (seenDot || seenExponent)
                        return false;
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

            if (!seenDigit)
                return false;

            return true;
        }
    }

    // revised on 11/27/2025
    class SolutionRevisonThirtyDay {
        public boolean isNumber(String s) {
            boolean seenDot = false;
            boolean seenDigit = false;
            boolean seenExponent = false;

            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    seenDigit = true;
                } else if (c == '+' || c == '-') {

                    if (i != 0 && s.charAt(i - 1) != 'e' || s.charAt(i - 1) != 'E')
                        return false;

                    seenDigit = false;
                } else if (c == '.') {
                    if (seenDot || seenExponent) {
                        return false;
                    }
                    seenDot = true;
                } else if (c == 'e' || c == 'E') {

                    if (seenExponent || !seenDigit)
                        return false;
                    seenExponent = true;
                    seenDigit = false;
                } else {
                    return false;
                }
            }

            if (!seenDigit)
                return false;

            return true;
        }
    }
}
