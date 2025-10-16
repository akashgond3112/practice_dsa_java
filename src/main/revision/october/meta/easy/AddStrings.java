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
                sum += num1.charAt(j--) - '0';
            }

            sum += carry;

            sb.append(sum % 10 + '0');
            carry = sum / 10;
        }

        if (carry != null) {
            sb.append(carry + '0');
        }

        return sb.reverse().toString();

    }
}
