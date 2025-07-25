package main.dsa.two_pointers.easy;

/*
2511. Maximum Enemy Forts That Can Be Captured
Easy
Hint
You are given a 0-indexed integer array forts of length n representing the positions of several forts. forts[i] can be -1, 0, or 1 where:

-1 represents there is no fort at the ith position.
0 indicates there is an enemy fort at the ith position.
1 indicates the fort at the ith the position is under your command.
Now you have decided to move your army from one of your forts at position i to an empty position j such that:

0 <= i, j <= n - 1
The army travels over enemy forts only. Formally, for all k where min(i,j) < k < max(i,j), forts[k] == 0.
While moving the army, all the enemy forts that come in the way are captured.

Return the maximum number of enemy forts that can be captured. In case it is impossible to move your army, or you do not have any fort under your command, return 0.

Example 1:
Input: forts = [1,0,0,-1,0,0,0,0,1]
Output: 4
Explanation:
- Moving the army from position 0 to position 3 captures 2 enemy forts, at 1 and 2.
- Moving the army from position 8 to position 3 captures 4 enemy forts.
Since 4 is the maximum number of enemy forts that can be captured, we return 4.

Example 2:
Input: forts = [0,0,1,-1]
Output: 0
Explanation: Since no enemy fort can be captured, 0 is returned.


Constraints:
1 	<= forts.length	<= 1000
-1 	<= forts[i]		<= 1
* */

public class MaximumEnemyFortsThatCanBeCaptured {

	public int captureForts(int[] forts, String solution_one) {
		int ans = 0;
		for (int i = 0, ii = 0; i < forts.length; ++i)
			if (forts[i] != 0) {
				if (forts[ii] == -forts[i])
					ans = Math.max(ans, i - ii - 1);
				ii = i;
			}
		return ans;
	}

	// brute force
	public int captureForts(int[] forts, String solution_one, String solution_two) {
		int max = 0;
		int n = forts.length;

		for (int i = 0; i < n; i++) {
			if (forts[i] == 1) {
				int count = 0;
				for (int j = i + 1; j < n; j++) {
					if (forts[j] == 0)
						count++;
					else if (forts[j] == 1)
						break;
					else {
						max = Math.max(max, count);
						break;
					}
				}

				count = 0;
				for (int j = i - 1; j >= 0; j--) {
					if (forts[j] == 0)
						count++;
					else if (forts[j] == 1)
						break;
					else {
						max = Math.max(max, count);
						break;
					}
				}
			}
		}

		return max;
	}

	public static int captureForts(int[] forts) {

		int maxDistance = 0;
		int counter = 0;
		boolean ones = false;
		boolean minusOnes = false;

		for (int fort : forts) {

			if ((ones || minusOnes) && fort == 0) {
				counter++;
			}

			if (fort == -1 && ones) {
				maxDistance = Math.max(maxDistance, counter);
				counter = 0;
				ones = false;
				minusOnes = true;
			}

			if (fort == 1 && minusOnes) {
				maxDistance = Math.max(maxDistance, counter);
				counter = 0;
				ones = true;
				minusOnes = false;
			}

			if (minusOnes && fort == -1 || ones && fort == 1) {
				counter = 0;
			}
			if (fort == 1) {
				ones = true;
			}
			if (fort == -1) {
				minusOnes = true;
			}

		}

		return maxDistance;
	}

	public static void main(String[] args) {
		int[] arr;
		int result;
		int actualResult;

		arr = new int[] { 1, 0, 0, -1, 0, 0, 0, 0, 1 };
		result = 4;
		actualResult = captureForts(arr);

		arr = new int[] { 0, 0, 1, -1 };
		result = 0;
		actualResult = captureForts(arr);

		arr = new int[] { -1, -1, 1, -1, -1, 0 };
		result = 0;
		actualResult = captureForts(arr);

		arr = new int[] { 0, -1, -1, 0, -1 };
		result = 0;
		actualResult = captureForts(arr);

	}

}
