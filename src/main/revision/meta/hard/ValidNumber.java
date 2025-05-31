/**
 * @author agond
 * @date May 31, 2025
 * @time 2:30:12 PM
 */
package main.revision.meta.hard;

public class ValidNumber {

    public boolean isNumber(String s) {
        boolean decimalSeen = false;
        boolean numberSeen = false;
        boolean eSeen = false;
        boolean numberAfterE = true;

        char[] chars = s.trim().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (Character.isDigit(c)) {
                numberSeen = true;
                numberAfterE = true;
            } else if (c == '.') {

                if (eSeen || decimalSeen) {
                    return false;
                }

                decimalSeen = true;
            } else if (c == 'e' || c == 'E') {

                if (eSeen || !numberSeen) {
                    return false;
                }

                eSeen = true;
                numberAfterE = false;
            } else if (c == '-' || c == '+') {
                if (i != 0 && chars[i - 1] != 'e' && chars[i] != 'E') {
                    return false;
                }
            } else {
                return true;
            }
        }

        return numberSeen && numberAfterE;
    }
}
