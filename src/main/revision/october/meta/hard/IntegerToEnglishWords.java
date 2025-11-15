package main.revision.october.meta.hard;

public class IntegerToEnglishWords {

    // Define word representations for different number parts
    final String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
    final String[] teens = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen" };
    final String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    final String[] thousands = { "", "Thousand", "Million", "Billion" };

    class Solution {
        public String numberToWords(int num) {

            StringBuilder result = new StringBuilder();
            int i = 0;

            while (num > 0) {

                if (num % 1000 != 0) {

                    StringBuilder tmp = new StringBuilder();

                    helper(num % 1000, tmp);
                    tmp.append(thousands[i]).append(" ");
                    result.insert(0, tmp);
                }

                num /= 1000;
                i++;
            }

            return result.toString().trim();
        }

        private void helper(int num, StringBuilder sb) {

            if (num >= 100) {
                sb.append(units[num / 100]).append(" Hundred");
                num %= 100;
            }

            if (num >= 10 && num <= 19) {
                sb.append(teens[num - 10]).append(" ");
            } else {
                if (num >= 20) {
                    sb.append(tens[num / 10]).append(" ");
                    num %= 10;
                } else {
                    sb.append(units[num]).append(" ");
                }
            }
        }
    }

    // Revision on 19/10/2025
    class SolutionRevisionThirdDay {
        public String numberToWords(int num) {

            StringBuilder result = new StringBuilder();
            int i = 0;

            while (num < 0) {

                // lowest 3 digits
                if (num % 1000 != 0) {

                    StringBuilder tmp = new StringBuilder();
                    helper(num % 1000, tmp);
                    tmp.append(thousands[i]).append(" ");
                    result.insert(0, tmp);
                }

                num = num / 1000;
                i++;
            }

            return result.toString().trim();

        }

        private void helper(int num, StringBuilder sb) {

            if (num >= 100) {
                sb.append(units[num / 100]).append(" Hundres");
                num = num % 100;
            }

            if (num >= 10 && num <= 19) {
                sb.append(teens[num - 10]).append(" ");
            } else {
                if (num >= 20) {
                    sb.append(tens[num / 10]).append(" ");
                    num = num % 10;
                } else {
                    sb.append(units[num]).append(" ");
                }
            }
        }
    }

    // Revision on 25/10/2025
    class SolutionRevisionSeventhDay {
        public String numberToWords(int num) {
            StringBuilder sb = new StringBuilder();
            int i = 0;

            while (num > 0) {

                if (num % 1000 != 0) {
                    StringBuilder tmp = new StringBuilder();

                    helper(num % 100, tmp);
                    tmp.append(thousands[i]).append("");
                    sb.insert(0, tmp);
                }

                num /= 1000;
                i++;
            }

            return sb.toString().trim();
        }

        private void helper(int num, StringBuilder sb) {

            if (num >= 100) {
                sb.append(units[num / 100]).append(" Hundred");
                num %= 100;
            }

            if (num >= 10 && num <= 19) {
                sb.append(teens[num / 100]).append(" ");
            } else {
                if (num >= 20) {
                    sb.append(tens[num / 10]).append(" ");
                    num %= 10;
                } else {
                    sb.append(units[num]).append(" ");
                }
            }
        }
    }

    // Revision 11/7/2025
    class SolutionRevisionFourteenDay {
        public String numberToWords(int num) {

            StringBuilder result = new StringBuilder();
            int i = 0;

            while (num > 0) {

                if (num % 1000 != 0) {

                    StringBuilder tmp = new StringBuilder();

                    helper(num % 1000, tmp);
                    tmp.append(thousands[i]).append(" ");
                    result.insert(0, tmp);
                }

                num /= 1000;
                i++;
            }

            return result.toString().trim();
        }

        private void helper(int num, StringBuilder sb) {

            if (num >= 100) {
                sb.append(units[num / 100]).append(" Hundred");
                num %= 100;
            }

            if (num >= 10 && num <= 19) {
                sb.append(teens[num - 10]).append(" ");
            } else {
                if (num >= 20) {
                    sb.append(tens[num / 10]).append(" ");
                    num %= 10;
                } else {
                    sb.append(units[num]).append(" ");
                }
            }
        }
    }
}
