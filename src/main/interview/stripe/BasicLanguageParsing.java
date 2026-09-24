/**
 * @author akash
 * @date Sep 21, 2026
 * @time 3:49:31 PM
 */
package main.interview.stripe;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BasicLanguageParsing {

    /**
     * <b>Input:</b>
     * header = "en-US, fr-CA, fr-FR"
     * supported_languages = {"fr-FR", "en-US"}
     * 
     * <b>Output:</b> ["en-us", "fr-fr"]
     * <b>Explanation:</b> Both languages are supported, returned in the order they
     * appear, lowercased.
     * 
     */
    public static List<String> parseAcceptLanguage(String header, Set<String> supportedLanguages) {

        Set<String> supportedLangLower = new HashSet<>();

        for (String lang : supportedLanguages) {
            supportedLangLower.add(lang);
        }

        String[] prefLang = header.split(",");
        List<String> accpLangs = new ArrayList<>();
        Set<String> uniqeLangs = new HashSet<>();

        for (String lang : prefLang) {
            String curLang = lang.trim().toLowerCase();

            if (supportedLangLower.contains(curLang) && !uniqeLangs.contains(curLang)) {
                accpLangs.add(curLang);
                uniqeLangs.add(curLang);
            }
        }

        return accpLangs;

    }

    /**
     * <b>Input:</b>
     * header = "en-US;q=0.8, fr-CA;q=1.0, fr-FR;q=0.5"
     * supported_languages = {"en-US", "fr-CA", "fr-FR"}
     * 
     * <b>Output:</b> ["fr-ca", "en-us", "fr-fr"]
     * <b>Explanation:</b> Sorted by q-value: fr-CA(1.0), en-US(0.8), fr-FR(0.5)
     * 
     */
    public static List<String> parseAcceptLanguageWithQ(String header, Set<String> supportedLanguages) {

        Set<String> supportedLangLower = new HashSet<>();

        for (String lang : supportedLanguages) {
            supportedLangLower.add(lang);
        }

        Map<String, Double> langToQ = new HashMap<>();
        Map<String, Integer> order = new HashMap<>();

        String[] groups = header.split(",");

        for (int i = 0; i < groups.length; i++) {

            String[] parts = groups[i].split(";");
            String lang = parts[0].trim().toLowerCase();
            double q = 1.0;

            if (parts.length == 2 && parts[1].trim().startsWith("q")) {
                try {
                    q = Double.parseDouble(parts[1].split("=")[1]);
                } catch (NumberFormatException _) {
                    q = 0.0;
                }
            }

            if (supportedLangLower.contains(lang) && (!langToQ.containsKey(lang) || q > langToQ.get(lang))) {
                langToQ.put(lang, q);
                order.put(lang, i);
            }

        }

        List<String> result = new ArrayList<>(langToQ.keySet());

        result.sort((a, b) -> {
            int cmp = Double.compare(langToQ.get(b), langToQ.get(a));
            return cmp != 0 ? cmp : Integer.compare(order.get(a), order.get(b));
        });

        return result;
    }

    /**
     * <b>Input:</b>
     * header = "fr-FR;q=0.7, en-US;q=1.0"
     * supported_languages = {"fr-FR", "en-US", "de-DE"}
     * 
     * <b>Output:</b> ["en-us", "fr-fr"]
     * <b>Explanation:</b> No wildcard, so only explicit matches: en-US(1.0),
     * fr-FR(0.7)
     * 
     */
    public static List<String> parseAcceptLanguageWithWildcard(String header, Set<String> supportedLanguages) {

        Set<String> supportedLangLower = new HashSet<>();

        for (String lang : supportedLanguages) {
            supportedLangLower.add(lang);
        }

        Map<String, Double> langToQ = new HashMap<>();
        Map<String, Integer> order = new HashMap<>();
        Double wildcardQ = null;

        String[] groups = header.split(",");

        for (int i = 0; i < groups.length; i++) {

            String[] parts = groups[i].split(";");
            String lang = parts[0].trim().toLowerCase();
            double q = 1.0;

            if (parts.length == 2 && parts[1].trim().startsWith("q")) {
                try {
                    q = Double.parseDouble(parts[1].split("=")[1]);
                } catch (NumberFormatException _) {
                    q = 0.0;
                }
            }

            if (lang.equals("*") && (wildcardQ == null || q > wildcardQ)) {
                wildcardQ = q;
            } else if (supportedLangLower.contains(lang) && (!langToQ.containsKey(lang) || q > langToQ.get(lang))) {
                langToQ.put(lang, q);
                order.put(lang, i);
            }

        }

        if (wildcardQ != null) {

            for (String supportLang : supportedLangLower) {
                if (!langToQ.containsKey(supportLang)) {
                    langToQ.put(supportLang, wildcardQ);
                    order.put(supportLang, Integer.MAX_VALUE);
                }
            }
        }

        List<String> result = new ArrayList<>(langToQ.keySet());

        result.sort((a, b) -> {
            int cmp = Double.compare(langToQ.get(b), langToQ.get(a));
            return cmp != 0 ? cmp : Integer.compare(order.get(a), order.get(b));
        });

        return result;
    }

}
