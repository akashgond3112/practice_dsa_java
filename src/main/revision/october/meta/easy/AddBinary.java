package main.revision.october.meta.easy;

public class AddBinary {
    class Solution {
        public String addBinary(String num1, String num2) {
            int i = num1.length() - 1;
            int j = num2.length() - 1;

            int carry = 0;
            StringBuilder sb = new StringBuilder();

            while (i >= 0 || j >= 0) {

                int sum = carry;
                if (i >= 0) {
                    sum += num1.charAt(i--) - '0';
                }

                if (j >= 0) {
                    sum += num2.charAt(j--) - '0';
                }

                sb.append(sum % 2);
                carry = sum / 2;
            }

            if (carry != 0) {
                sb.append(carry);
            }

            return sb.reverse().toString();
        }
    }

    // revised on 12/8/2025
    class SolutionRevisedOnThirdDay {
        public String addBinary(String num1, String num2) {
            int i = num1.length() - 1;
            int j = num2.length() - 1;

            int carry = 0;
            StringBuilder sb = new StringBuilder();

            while (i >= 0 && j >= 0) {
                int sum = carry;

                if (i > 0) {
                    sum += num1.charAt(i--) - '0';
                }

                if (j > 0) {
                    sum += num2.charAt(j--) - '0';
                }

                sb.append(sum % 10);
                carry = sum / 10;
            }

            if (carry != 0) {
                sb.append(carry);
            }

            return sb.reverse().toString();
        }
    }
}
