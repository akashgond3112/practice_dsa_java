/**
 * @author agond
 * @date Jun 12, 2025
 * @time 9:27:02 PM
 */
package main.revision.meta.easy;

import java.util.*;

public class LongestCommonPrefix {

	public String longestCommonPrefix(String[] strs) {

		Arrays.sort(strs);

		char[] first = strs[0].toCharArray();
		char[] last = strs[strs.length - 1].toCharArray();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < first.length; i++) {

			if (first[i] != last[i])
				break;

			sb.append(first[i]);
		}
		return sb.toString();
	}

}
