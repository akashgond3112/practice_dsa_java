/**
 * @author agond
 * @date Jul 07, 2025
 * @time 6:02:25 PM
 */
package main.revision.meta.medium;

import java.util.*;
import java.util.Map.Entry;

public class GroupShiftedStrings {

    private static String getKey(String s) {

        StringBuilder key = new StringBuilder();

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            char prev = s.charAt(i - 1);

            int diff = c - prev;
            if (diff < 0) {
                diff = +26;
            }

            key.append(diff).append("#");
        }

        return key.toString();
    }

    public ArrayList<ArrayList<String>> groupShiftedString(String[] arr) {

        Map<String, ArrayList<String>> map = new HashMap<>();

        for (String str : arr) {
            String key = getKey(str);

            if (!map.containsKey(key)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                map.get(key).add(str);
            }
        }

        ArrayList<ArrayList<String>> res = new ArrayList<>();

        for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> list = map.get(key);
            res.add(list);

        }
        return res;
    }
}
