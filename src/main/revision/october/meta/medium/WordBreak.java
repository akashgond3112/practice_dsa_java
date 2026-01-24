package main.revision.october.meta.medium;

import java.util.*;

/*
Let's trace the execution with a simple example:

s = "leetcode"
wordDict = ["leet", "code"]

The goal is to see if dp[0] becomes true. Remember, dp[i] = true means the substring from index i to the end can be segmented.

1. Initialization:

A boolean array dp of size 9 is created: 
dp[0...8].
dp[8] is set to true. This is our base case: an empty string is always valid.
dp looks like this: [F, F, F, F, F, F, F, F, T] 
(where F=false, T=true)

2. Outer loop starts, iterating backward from i = 7 down to 0:

{
i = 7 (substring "e"):

Does "e" start with "leet"? No.
Does "e" start with "code"? No.
dp[7] remains false.
}

{
i = 6 (substring "de"): No words match. dp[6] 
remains false.
}

{
i = 5 (substring "ode"): No words match. dp[5] 
remains false.
}

{
i = 4 (substring "code"):

Does "code" start with "leet"? No.
Does "code" start with "code"? Yes.
    {
        The code executes: dp[4] = dp[4 + "code".length()] which is dp[4] = dp[8].
        Since dp[8] is true, dp[4] is now set to true.
        dp is now: [F, F, F, F, T, F, F, F, T]
        The inner loop breaks because dp[4] is now true.
    }
}

{
i = 3 (substring "tcode"): No words match. dp[3] remains false.
}

{
i = 2 (substring "etcode"): No words match. dp[2] remains false.
}

{
i = 1 (substring "eetcode"): No words match. dp[1] remains false.
}

{
i = 0 (substring "leetcode"):

    {
        Does "leetcode" start with "leet"? Yes.
            {
                The code executes: dp[0] = dp[0 + "leet".length()] which is dp[0] = dp[4].
                Since dp[4] is true, dp[0] is now set to true.
            }
        dp is now: [T, F, F, F, T, F, F, F, T]
        The inner loop breaks because dp[0] is now true.
    }
}

3. Final Result:
The loops finish.
The function returns dp[0], which is true.
*/
public class WordBreak {
    class Solution {
        /**
         * Time complexity: O(n∗m∗t) Space complexity: O(n) Where n is the length of the
         * string s
         * , m is the number of words in wordDict and t is the maximum length of any
         * word in wordDict.
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[s.length()] = true;

            for (int i = s.length() - 1; i >= 0; i--) {
                for (String w : wordDict) {
                    if ((i + w.length()) <= s.length() &&
                            s.startsWith(w, i)) {
                        dp[i] = dp[i + w.length()];
                    }
                    if (dp[i]) {
                        break;
                    }
                }
            }

            return dp[0];
        }
    }

    // revised on 12/6/2025
    class SolutionRevisonOnThirDay {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[s.length() - 1] = true;

            for (int i = s.length() - 1; i >= 0; i--) {
                for (String word : wordDict) {
                    if ((i + word.length()) <= s.length() && s.startsWith(word, i)) {
                        dp[i] = dp[i + word.length()];
                    }

                    if (dp[i])
                        break;
                }
            }

            return dp[0];
        }
    }

    // revised on 12/12/2025
    class SolutionRevisonOnDaySeventh {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[s.length() - 1] = true;

            for (int i = 0; i < s.length() - 1; i++) {
                for (String word : wordDict) {
                    if ((i + word.length()) <= s.length() && s.startsWith(word, i)) {
                        dp[i] = dp[i + word.length()];
                    }

                    if (dp[i])
                        break;
                }
            }

            return dp[0];
        }
    }

    // revised on 12/26/2025
    class SolutionRevisonOnDayFourteen {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[s.length() - 1] = true;

            for (int i = 0; i < s.length() - 1; i++) {

                for (String word : wordDict) {

                    if (i + word.length() <= s.length() && s.startsWith(word, i)) {
                        dp[i] = dp[i + word.length()];
                    }

                    if (dp[i])
                        break;
                }
            }

            return dp[0];
        }
    }

    // revised on 1/24/2026
    class SolutionRevisedOnDayThirty {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[s.length() - 1] = true;

            for (int i = 0; i < s.length() - 1; i++) {

                for (String word : wordDict) {

                    if (i + word.length() <= s.length() && s.startsWith(word, i)) {
                        dp[i] = dp[i + word.length()];
                    }

                    if (dp[i])
                        break;
                }
            }

            return dp[0];
        }
    }
}
