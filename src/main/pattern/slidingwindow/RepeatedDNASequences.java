/**
 * @author akash
 * @date Jul 31, 2026
 * @time 7:14:51 PM
 */
package main.pattern.slidingwindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RepeatedDNASequences {
    class Solution {
        public List<String> findRepeatedDnaSequences(String s) {
            HashSet<String> seenSubStrings = new HashSet<>();
            HashSet<String> repeatedSequences = new HashSet<>();
            int endIndex = 0;

            while (endIndex <= s.length() - 10) {
                String subs = s.substring(endIndex, endIndex + 10);
                if (seenSubStrings.contains(subs)) {
                    repeatedSequences.add(subs);
                }
                seenSubStrings.add(subs);
                endIndex++;
            }

            return new ArrayList<>(repeatedSequences);
        }
    }

}
