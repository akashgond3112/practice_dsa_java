/**
 * @author agond
 * @date Jun 02, 2025
 * @time 8:39:20 PM
 */
package main.revision.meta.easy;

public class AddStrings {

    public String addStrings(String num1, String num2) {

        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int carry = 0;

        StringBuilder sb = new StringBuilder();

        while (len1 >= 0 || len2 >= 0 || carry > 0) {

            int cur1 = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int cur2 = len2 >= 0 ? num2.charAt(len2) - '0' : 0;

            int sum = cur1 + cur2 + carry;

            carry = sum / 10;

            sb.append(sum % 10);

            len1--;
            len2--;
        }

        return sb.reverse().toString();
    }

}
