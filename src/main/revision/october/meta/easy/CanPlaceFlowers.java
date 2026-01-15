package main.revision.october.meta.easy;

public class CanPlaceFlowers {

    class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {

            // Total flowers jo hum place kar sakte hain, uska count rakhenge
            int total = 0;

            // Flowerbed array ko traverse karenge
            for (int i = 0; i < flowerbed.length; i++) {

                // Agar current position pe flower nahi hai (0 hai)
                if (flowerbed[i] == 0) {

                    // Next position ka value check karenge, agar last position hai to next 0 hoga
                    int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                    // Previous position ka value check karenge, agar first position hai to prev 0
                    // hoga
                    int prev = (i == 0) ? 0 : flowerbed[i - 1];

                    // Agar next aur prev dono 0 hain, to yaha flower place kar sakte hain
                    if (next == 0 && prev == 0) {
                        flowerbed[i] = 1; // Flower place kar diya
                        total++; // Total flowers ka count badha diya
                    }
                }
            }

            // Agar total flowers jo place kiye hain wo n ke barabar ya zyada hain, to true
            // return karenge
            return total >= n;
        }
    }

    // revised on 12/2/2025
    class SolutionRevisedOnSeventhDay {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {

            int total = 0;

            for (int i = 0; i < flowerbed.length; i++) {

                if (flowerbed[i] == 0) {

                    int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                    int prev = (i == 0) ? 0 : flowerbed[i - 1];

                    if (next == 0 && prev == 0) {
                        flowerbed[i] = 1;
                        total++;
                    }
                }
            }

            return total == 1;
        }
    }

    // revised on 12/16/2025
    class SolutionRevisedOnFourteenDay {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            int total = 0;

            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 0) {

                    int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                    int prev = (i == 0) ? 0 : flowerbed[i - 1];

                    if (next == 0 && prev == 0) {
                        flowerbed[i] = 1;
                        total++;
                    }
                }
            }
            return total >= n;
        }
    }

    // revised on 1/14/2026
    class SolutionRevisedOnDayThirty {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {

            int total = 0;

            for (int i = 0; i < flowerbed.length; i++) {
                int next = (i == flowerbed.length) ? 0 : flowerbed[i + 1];
                int prev = (i == 0) ? 0 : flowerbed[i - 1];

                if (next == 0 && prev == 0) {
                    flowerbed[i] = 1;
                    total++;
                }
            }

            return total >= n;
        }
    }
}
