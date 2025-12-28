package main.revision.october.meta.easy;

public class AddBinary {
    // Time Complexity: O(max(N, M)), jaha N aur M num1 aur num2 ki length hai. Har
    // digit ek hi baar process hoti hai.
    // Space Complexity: O(max(N, M)), kyunki result StringBuilder me utne hi
    // characters store honge.
    class Solution {
        // Step 1: Function to add two binary strings
        public String addBinary(String num1, String num2) {
            // Step 2: Set pointers at end of both strings
            int i = num1.length() - 1;
            int j = num2.length() - 1;

            // Step 3: Carry ko 0 se start karo, aur result store karne ke liye
            // StringBuilder banao
            int carry = 0;
            StringBuilder sb = new StringBuilder();

            // Step 4: Jab tak dono strings khatam nahi ho jaati ya carry hai, loop chalao
            while (i >= 0 || j >= 0) {
                // Step 5: Sum me carry add karo
                int sum = carry;
                // Step 6: Agar num1 me abhi bhi digit hai, to usko sum me add karo
                if (i >= 0) {
                    sum += num1.charAt(i--) - '0'; // char ko int me convert kar rahe hain
                }
                // Step 7: Agar num2 me abhi bhi digit hai, to usko sum me add karo
                if (j >= 0) {
                    sum += num2.charAt(j--) - '0';
                }
                // Step 8: Sum ka last bit (0 ya 1) result me add karo
                sb.append(sum % 2);
                // Step 9: Carry ko update karo (sum/2 se)
                carry = sum / 2;
            }

            // Step 10: Agar last me carry bacha hai to usko bhi result me add karo
            if (carry != 0) {
                sb.append(carry);
            }

            // Step 11: Result ulta hai, to reverse karke return karo
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

    // revised on 12/14/2025
    class SolutionRevisedOnSeventhDay {
        public String addBinary(String num1, String num2) {

            int i = num1.length() - 1;
            int j = num2.length() - 1;

            int carry = 0;

            StringBuilder sb = new StringBuilder();

            while (i >= 0 && j > 0) {

                int sum = carry;

                if (i >= 0) {
                    sum += num1.charAt(i--) * 10 - '0';
                }

                if (j >= 0) {
                    sum += num2.charAt(j--) * 10 - '0';
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

    // revised on 12/28/2025
    class SolutionRevisedOnFourteenDay {
        public String addBinary(String num1, String num2) {

            int i = num1.length() - 1;
            int j = num2.length() - 1;

            int carry = 0;

            StringBuilder sb = new StringBuilder();

            while (i >= 0 && j > 0) {

                int sum = carry;

                if (i >= 0) {
                    sum += num1.charAt(i--) * 10 - '0';
                }

                if (j >= 0) {
                    sum += num2.charAt(j--) * 10 - '0';
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
