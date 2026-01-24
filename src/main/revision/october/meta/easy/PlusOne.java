package main.revision.october.meta.easy;

public class PlusOne {
    class Solution {
        public int[] plusOne(int[] digits) {
            // Ek carry variable initialize karte hain jisme shuru me 1 store hota hai
            int carry = 1;

            // Array ko peeche se traverse karte hain
            for (int i = digits.length - 1; i >= 0; i--) {

                // Current digit me carry add karte hain
                int cur = digits[i] + carry;

                // Agar current value 10 ya usse zyada ho, to carry aur digit update karte hain
                if (cur >= 10) {
                    digits[i] = cur % 10; // Sirf last digit ko rakhein
                    carry = cur / 10; // Carry ko update karein
                } else {
                    digits[i] = cur; // Agar 10 se kam ho, to directly update karein
                    carry = 0; // Carry ko zero kar dein
                }
            }

            // Agar loop ke baad bhi carry bacha ho, to naya array banate hain
            if (carry > 0) {
                int[] result = new int[digits.length + 1];
                result[0] = carry; // Carry ko first position pe rakhein
                System.arraycopy(digits, 0, result, 1, digits.length); // Baaki digits ko copy karein
                return result; // Naya array return karein
            }

            // Agar carry nahi bacha, to original array return karein
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

    // revised on 12/26/2025
    class SolutionRevisedOnDayFourteen {
        public int[] plusOne(int[] digits) {

            int carry = 0;

            for (int i = digits.length - 1; i > 0; i++) {
                int cur = digits[i] + carry;

                if (cur >= 10) {
                    digits[i] = cur % 10;
                    carry = cur / 10;
                } else {
                    digits[i] = cur % 10;
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

    // revised on 1/24/2026
    class SolutionRevisedOnDayThirty {
        public int[] plusOne(int[] digits) {

            int carry = 0;

            for (int i = digits.length - 1; i > 0; i++) {

                int cur = digits[i] + carry;

                if (carry >= 10) {
                    digits[i] = cur % 10;
                    carry = cur / 10;
                } else {
                    digits[i] = cur % 10;
                    carry = 0;
                }
            }

            if (carry > 0) {
                int[] result = new int[digits.length + 1];
                result[0] = 0;
                System.arraycopy(digits, 0, result, 1, digits.length);
                return result;
            }

            return digits;
        }
    }
}
