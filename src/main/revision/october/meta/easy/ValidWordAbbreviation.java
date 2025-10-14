package main.revision.october.meta.easy;

public class ValidWordAbbreviation {
    public boolean isValid(String word, String abbr) {

        int a = 0;
        int w = 0;

        while (w < word.length() && a < abbr.length()) {

            if (word.charAt(w) == abbr.charAt(a)) {
                a++;
                w++;
            }

            if (!Character.isDigit(abbr.charAt(a)) && word.charAt(w) != abbr.charAt(a)) {
                return false;
            } 

            if (abbr.charAt(a) == '0') {
                return false;
            }

            int skip = 0;
            while (a < abbr.length() && Character.isDigit(abbr.charAt(a))) {
                skip = skip * 10 + (abbr.charAt(a) - '0');
                a++;
            }

            w += skip;
        }

        return w == word.length() && a == abbr.length();

    }
}
