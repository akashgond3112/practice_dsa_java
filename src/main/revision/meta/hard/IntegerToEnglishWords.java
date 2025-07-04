/**
 * @author agond
 * @date Jul 04, 2025
 * @time 7:51:21 PM
 */
package main.revision.meta.hard;

public class IntegerToEnglishWords {

    public String numberToWords(int num) {
        // Special case handling for zero
        if (num == 0)
            return "Zero";

        // Define word representations for different number parts
        String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
        String[] teens = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
                "Eighteen", "Nineteen" };
        String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
        String[] thousands = { "", "Thousand", "Million", "Billion" };

        StringBuilder result = new StringBuilder();
        int i = 0; // Tracks which 'thousands' group we're processing (0=ones, 1=thousands,
                   // 2=millions, etc.)

        // Process the number in groups of three digits
        while (num > 0) {
            // Only add words for non-zero groups
            if (num % 1000 != 0) {
                StringBuilder temp = new StringBuilder();
                helper(num % 1000, temp, units, teens, tens);
                temp.append(thousands[i]).append(" ");
                result.insert(0, temp); // Insert at beginning since we're processing right-to-left
            }
            num /= 1000; // Move to next group of three digits
            i++;
        }

        return result.toString().trim(); // Remove trailing space
    }

    private void helper(int num, StringBuilder sb, String[] units, String[] teens, String[] tens) {
        // Handle hundreds place
        if (num >= 100) {
            sb.append(units[num / 100]).append(" Hundred ");
            num %= 100; // Get remaining digits
        }

        // Handle tens place with special case for teens (11-19)
        if (num >= 10 && num <= 19) {
            sb.append(teens[num - 10]).append(" ");
        } else {
            if (num >= 20) {
                sb.append(tens[num / 10]).append(" ");
                num %= 10; // Get remaining digit
            }

            // Handle units place
            if (num > 0) {
                sb.append(units[num]).append(" ");
            }
        }
    }

}
