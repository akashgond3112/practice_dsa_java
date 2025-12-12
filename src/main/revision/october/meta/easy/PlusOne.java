package main.revision.october.meta.easy;

public class PlusOne {
    class Solution {
        /**
         * Adds one to the number represented by the input array of digits.
         *
         * @param digits the array of digits representing a non-negative integer
         * @return an array of digits representing the integer after adding one
         */
        public int[] plusOne(int[] digits) {
            int carry = 1;

            for (int i = digits.length - 1; i >= 0; i--) {

                int cur = digits[i] + carry;

                if (cur >= 10) {
                    digits[i] = cur % 10;
                    carry = cur / 10;
                } else {
                    digits[i] = cur;
                    carry = 0;
                }
            }

            if (carry > 0) {
                int[] result = new int[digits.length + 1];
                result[0] = carry;
                System.arraycopy(digits, 0, result, 1, digits.length);
                return result;
            }

            return digits;
        }
    }

    // revised on 12/6/2025
    class SolutionRevisionOnThirdDay {

        public int[] plusOne(int[] digits) {

            int carry = 1;

            for (int i = digits.length - 1; i > 0; i--) {

                int cur = digits[i] + carry;

                if (cur >= 10) {
                    digits[i] = cur % 10;
                    carry = cur / 10;
                } else {
                    digits[i] = cur;
                    carry = 0;
                }
            }

            if (carry > 0) {
                int[] result = new int[digits.length + 1];
                result[0] = carry;
                System.arraycopy(digits, 0, result, 1, digits.length);
                return result;
            }

            return digits;
        }
    }

    // revised on 12/12/2025
    class SolutionRevisionOnSeventhDay {

        public int[] plusOne(int[] digits) {

            int carry = 1;

            for (int i = digits.length - 1; i > 0; i--) {

                int cur = digits[i] + carry;

                if (cur >= 10) {
                    digits[i] = cur % 10;
                    carry = cur / 10;
                } else {
                    digits[i] = cur;
                    carry = 0;
                }
            }

            if (carry > 0) {
                int[] result = new int[digits.length + 1];
                result[0] = carry;
                System.arraycopy(digits, 0, result, 1, digits.length);
                return result;
            }

            return digits;
        }
    }
}
