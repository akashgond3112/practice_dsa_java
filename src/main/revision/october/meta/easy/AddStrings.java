package main.revision.october.meta.easy;

public class AddStrings {
    public String addStrings(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        Integer carry = null;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0) {

            int sum = 0;
            if (i >= 0) {
                sum += num1.charAt(i--) - '0';
            }

            if (j >= 0) {
                sum += num2.charAt(j--) - '0';
            }

            sum += carry;

            sb.append(sum % 10);
            carry = sum / 10;
        }

        if (carry != null) {
            sb.append(carry);
        }

        return sb.reverse().toString();

    }

    // Revised on 18.10.2025
    public String addStrings_rev_1(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        StringBuilder sb = new StringBuilder();
        Integer carry = null;

        while (i >= 0 || j >= 0) {

            int sum = 0;

            if (i >= 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }

            if (j >= 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }

            sum += carry;
            sb.append(sum % 10);

            carry = sum / 10;
        }

        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();

    }

    // Revised on 24.10.2025
    public String addStringsRevisionSeventhDay(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        while (i >= 0 && j >= 0) {

            int sum = 0;

            if (i > 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }

            if (j > 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }

            sum += carry;

            sb.append(sum % 10);

            carry = sum / 10;
        }

        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();

    }

    // Revised on 11/7/2025
    public String addStringsRevisionFourteenDay(String num1, String num2) {

        int i = num1.length();
        int j = num2.length();

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        while (i >= 0 && j >= 0) {
            int sum = 0;

            if (i > 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }

            if (j > 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }

            sum += carry;
            sb.append(sum % 10);
            carry = sum / 10;

        }

        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();

    }

    // Revised on 12/6/2025
    public String addStringsRevisionThirtyDay(String num1, String num2) {

        int i = num1.length();
        int j = num2.length();

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        while (i >= 0 && j >= 0) {

            int sum = 0;

            if (i > 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }

            if (j > 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }

            sum += carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
}
