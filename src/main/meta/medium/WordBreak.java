package main.meta.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 139. Word Break
 * Medium
 * Topics
 * Companies
 * Given a string s and a dictionary of strings wordDict, return true if s can
 * be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the
 * segmentation.
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * The best solution among the ones provided is `SolutionUsingDpBottomUp`.
 * 
 * Here's a comparison:
 * 
 * 1. **`SolutionRecursion`** and **`SolutionUsingHashSet`**: These are
 * brute-force recursive solutions. They have exponential time complexity (e.g.,
 * `O(2^n)`) because they recompute results for the same substrings multiple
 * times. They will likely time out for larger inputs.
 * 
 * 2. **`SolutionUsingDpTopDown`**: This is a significant improvement. It uses
 * memoization (a top-down dynamic programming approach) to store the results of
 * subproblems, avoiding redundant calculations. Its time complexity is `O(n * m
 * * t)`, where `n` is the string length, `m` is the dictionary size, and `t` is
 * the max word length.
 * 
 * 3. **`SolutionUsingDpBottomUp`**: This is generally considered the best and
 * most optimal solution.
 * **Performance**: It has the same time complexity as the top-down DP solution,
 * `O(n * m * t)`, but often performs slightly better in practice by avoiding
 * the overhead of recursion calls.
 * **Space**: It uses an array of size `n+1`, which is efficient.
 * **Clarity**: The iterative approach can be easier to understand and debug
 * than recursion for some developers.
 * 
 * Therefore, `SolutionUsingDpBottomUp` is the most efficient and robust choice.
 * 
 */
public class WordBreak {

	/**
	 * Time complexity: O(t∗m n ) Space complexity: O(n) Where n is the length of the string s, m is the number of
	 * words in wordDict and t is the maximum length of any word in wordDict.
	 */
	public static class SolutionRecursion {
		public boolean wordBreak(String s, List<String> wordDict) {
			return dfs(s, wordDict, 0);
		}

		private boolean dfs(String s, List<String> wordDict, int i) {
			if (i == s.length()) {
				return true;
			}

			for (String w : wordDict) {
				if (i + w.length() <= s.length() &&
						s.startsWith(w, i)) {
					if (dfs(s, wordDict, i + w.length())) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * Time complexity: O((n∗2^n )+m) Space complexity: O(n+(m∗t)) Where n is the length of the string s and m is
	 * the number of words in wordDict.
	 */
	public static class SolutionUsingHashSet {
		public boolean wordBreak(String s, List<String> wordDict) {
			HashSet<String> wordSet = new HashSet<>(wordDict);

			return dfs(s, wordSet, 0);
		}

		private boolean dfs(String s, HashSet<String> wordSet, int i) {
			if (i == s.length()) {
				return true;
			}

			for (int j = i; j < s.length(); j++) {
				if (wordSet.contains(s.substring(i, j + 1))) {
					if (dfs(s, wordSet, j + 1)) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * Time complexity: O(n∗m∗t) Space complexity: O(n) Where n is the length of the string s
	 * , m is the number of words in wordDict and t is the maximum length of any word in wordDict.
	 */
	public static class SolutionUsingDpTopDown {
		private Map<Integer, Boolean> memo;

		public boolean wordBreak(String s, List<String> wordDict) {
			memo = new HashMap<>();
			memo.put(s.length(), true);
			return dfs(s, wordDict, 0);
		}

		private boolean dfs(String s, List<String> wordDict, int i) {
			if (memo.containsKey(i)) {
				return memo.get(i);
			}

			for (String w : wordDict) {
				if (i + w.length() <= s.length() &&
						s.startsWith(w, i)) {
					if (dfs(s, wordDict, i + w.length())) {
						memo.put(i, true);
						return true;
					}
				}
			}
			memo.put(i, false);
			return false;
		}
	}

	/**
	 * Time complexity: O(n∗m∗t) Space complexity: O(n) Where n is the length of the string s
	 * , m is the number of words in wordDict and t is the maximum length of any word in wordDict.
	 */
	public static class SolutionUsingDpBottomUp {
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
}
