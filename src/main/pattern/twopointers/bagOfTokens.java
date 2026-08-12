/**
 * @author akash
 * @date Aug 10, 2026
 * @time 6:52:01 PM
 */
package main.pattern.twopointers;

public class BagOfTokens {
    class Solution {
        public int bagOfTokensScore(int[] tokens, int power) {
            if (tokens == null || tokens.length == 0) {
                return 0;
            }

            java.util.Arrays.sort(tokens);
            int left = 0;
            int right = tokens.length - 1;
            int score = 0;
            int maxScore = 0;

            while (left <= right) {
                if (tokens[left] <= power) {
                    power -= tokens[left];
                    score++;
                    maxScore = Math.max(maxScore, score);
                    left++;
                } else if (score > 0) {
                    power += tokens[right];
                    score--;
                    right--;
                } else {
                    break;
                }
            }

            return maxScore;
        }
    }
}
