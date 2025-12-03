package main.revision.october.meta.easy;

public class CanPlaceFlowers {

    class Solution {
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
}
