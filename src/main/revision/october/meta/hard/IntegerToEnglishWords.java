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

            // Agar number zero hai to seedha "Zero" return kar sakte ho (optional)
            if (num == 0)
                return "Zero";

            StringBuilder result = new StringBuilder();
            int i = 0;

            // Jab tak number > 0, har 3 digits (thousands group) process karenge
            // Hinglish: "num ko 1000 se divide karte hue har 3-3 digits ka group nikaalte
            // jaa rahe hain"
            while (num > 0) {

                // Agar current 3-digit group zero nahi hai tabhi uska word banaenge
                // Hinglish: "agar current group (last 3 digits) zero nahi hai to uske liye
                // words banao"
                if (num % 1000 != 0) {

                    StringBuilder tmp = new StringBuilder();

                    // Helper se 0-999 range ka word banao
                    // Hinglish: "helper function se teen-akhri digits ka word ban jaayega"
                    helper(num % 1000, tmp);

                    // Uske baad appropriate thousand scale (Thousand, Million, ...) add karo
                    // Hinglish: "aur phir scale (Thousand/Million/...) append karo"
                    tmp.append(thousands[i]).append(" ");

                    // Nai group ko result ke front me insert karo taaki order sahi rahe
                    // Hinglish: "insert(0) se higher scale left me aa jaate hain, final order sahi
                    // ho jaata hai"
                    result.insert(0, tmp);
                }

                // Next 3-digit group pe jaane ke liye number ko 1000 se divide karo
                // Hinglish: "ab number ko 1000 se divide karke agla group process karenge"
                num /= 1000;
                i++;
            }

            // Extra spaces trim karke return karo
            // Hinglish: "trim se extra spaces hat jaayenge"
            return result.toString().trim();
        }

        private void helper(int num, StringBuilder sb) {

            // Agar 100 ya usse zyada hai to hundreds part handle karo
            // Hinglish: "pehle hundreds wala part agar present ho to add karo (e.g., One
            // Hundred)"
            if (num >= 100) {
                sb.append(units[num / 100]).append(" Hundred");
                num %= 100;
                // Hinglish: "hundred nikalne ke baad bacha hua 0-99 range hai"
            }

            // Agar 10-19 ke beech hai to teens array use karo
            // Hinglish: "agar number 10 se 19 ke beech hai to special teen words lagte
            // hain"
            if (num >= 10 && num <= 19) {
                sb.append(teens[num - 10]).append(" ");
            } else {
                // Agar 20 ya usse upar ka tens place hai
                // Hinglish: "20,30,...90 ke liye tens array se word jodo"
                if (num >= 20) {
                    sb.append(tens[num / 10]).append(" ");
                    num %= 10;
                }
                // Units place add karo (agar non-zero hai to word milega, otherwise empty
                // string)
                // Hinglish: "last me units add kar do (One..Nine), agar zero to kuch nahi add
                // hoga"
                sb.append(units[num]).append(" ");
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

    // Revision 12/7/2025
    class SolutionRevisionThirtyDay {
        public String numberToWords(int num) {

            StringBuilder sb = new StringBuilder();
            int i = 0;

            while (num > 0) {
                if (num % 1000 != 0) {

                    StringBuilder tmp = new StringBuilder();

                    helper(num % 1000, tmp);
                    tmp.append(thousands[i]).append(" ");
                    sb.insert(0, tmp);
                }

                num /= 1000;
                i++;
            }

            return sb.toString();
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
