/**
 * @author akash
 * @date Sep 21, 2026
 * @time 11:12:47 AM
 */
package main.interview.stripe;

/**
 * 
 * Problem 1 :
 * <b>Input:</b>
 * input_str = "USD:CAD:1.3,EUR:USD:1.1,GBP:EUR:1.15"
 * from_currency = "USD"
 * to_currency = "CAD"
 * amount = 100.0
 * 
 * <b>Output:</b> 130.0
 * 
 * <b>Explanation:</b> 1 USD = 1.3 CAD, so 100 USD = 130 CAD
 */

import java.util.*;

public class CurrencyConversion {

    /**
     * 
     * Problem 1 :
     * <b>Input:</b>
     * input_str = "USD:CAD:1.3,EUR:USD:1.1,GBP:EUR:1.15"
     * from_currency = "USD"
     * to_currency = "CAD"
     * amount = 100.0
     * 
     * <b>Output:</b> 130.0
     * 
     * <b>Explanation:</b> 1 USD = 1.3 CAD, so 100 USD = 130 CAD
     */
    public static double convertCurrency(String inputStr, String fromCurrency, String toCurrency, double amount) {

        String[] currencyExchanges = inputStr.split(",");

        Map<String, Double> excahngeRate = new HashMap<>();

        for (String exchange : currencyExchanges) {
            String[] cur = exchange.split(":");
            String currency1 = cur[0];
            String currency2 = cur[1];
            double rate = Double.parseDouble(cur[3]);

            excahngeRate.put(currency1 + "-" + currency2, rate);
            excahngeRate.put(currency2 + "-" + currency1, 1.0 / rate);
        }

        String key = fromCurrency + "-" + toCurrency;

        if (!excahngeRate.containsKey(key))
            return -1;

        return Math.round(amount * excahngeRate.get(key) * 100.0) / 100.0;
    }

    /**
     * <b>Input:</b>
     * input_str = "USD:CAD:1.3,AUD:CAD:0.74"
     * from_currency = "USD"
     * to_currency = "AUD"
     * amount = 100.0
     * 
     * <b>Output:</b>
     * {
     * 'route': 'USD -> CAD -> AUD',
     * 'rate': 1.76,
     * 'converted_value': 175.68
     * }
     * 
     * <b>Explanation:</b>
     * USD -> CAD: multiply by 1.3
     * CAD -> AUD: multiply by 1/0.74 ≈ 1.35
     * Total rate: 1.3 x 1.35 ≈ 1.76
     * 
     */

    private static class Pair {
        String currency;
        double rate;

        Pair(String currency, double rate) {
            this.currency = currency;
            this.rate = rate;
        }
    }

    private Map<String, List<Pair>> graph;
    private List<String> currencyPath;
    private double rate;
    private List<String> visited;

    public Map<String, Object> findConversionPath(String inputStr, String fromCurrency, String toCurrency,
            double amount) {

        String[] currencyExchanges = inputStr.split(",");

        graph = new HashMap<>();

        for (String exchange : currencyExchanges) {
            String[] cur = exchange.split(":");
            String currency1 = cur[0];
            String currency2 = cur[1];
            double rate = Double.parseDouble(cur[3]);

            graph.computeIfAbsent(currency1, k -> new ArrayList<>()).add(new Pair(currency2, rate));
            graph.computeIfAbsent(currency2, k -> new ArrayList<>()).add(new Pair(currency1, 1.0 / rate));
        }

        currencyPath = new ArrayList<>();
        rate = 1.0;
        visited = new ArrayList<>();

        Map<String, Object> result = new HashMap<>();

        if (!dfs(fromCurrency, toCurrency, 1.0)) {

            result.put("route", "No routed found");
            result.put("rate", -1);
            result.put("converted_value", -1);
        } else {
            result.put("route", String.join("->", currencyPath));
            result.put("rate", Math.round(rate * 100) / 100.0);
            result.put("converted_value", Math.round(amount * rate * 100) / 100.0);
        }

        return result;
    }

    private boolean dfs(String fromCurrency, String toCurrency,
            double amount) {

        visited.add(fromCurrency);
        currencyPath.add(fromCurrency);
        rate *= amount;

        if (fromCurrency.equals(toCurrency)) {
            return true;
        }

        if (graph.containsKey(fromCurrency)) {

            for (Pair nei : graph.get(fromCurrency)) {
                if (!visited.contains(nei.currency) && dfs(nei.currency, toCurrency, nei.rate)) {
                    return true;
                }

            }
        }

        visited.remove(fromCurrency);
        currencyPath.removeLast();

        rate /= amount;
        return false;
    }

    /**
     * <b>Input:</b>
     * input_str =
     * "USD:EUR:0.85,EUR:GBP:0.87,USD:CAD:1.3,CAD:GBP:0.58,USD:JPY:110,JPY:GBP:0.0065"
     * from_currency = "USD"
     * to_currency = "GBP"
     * amount = 100.0
     * 
     * <b>Available Paths:</b>
     * 
     * Path 1: USD → EUR → GBP (2 hops, rate ≈ 0.74)
     * Path 2: USD → CAD → GBP (2 hops, rate ≈ 0.75)
     * Path 3: USD → JPY → GBP (2 hops, rate ≈ 0.72)
     * 
     * <b>BFS Output (shortest in terms of hops):</b>
     * {
     * 'route': 'USD -> EUR -> GBP',
     * 'rate': 0.74,
     * 'converted_value': 73.95
     * }
     * 
     * <b>Explanation:</b> All paths have 2 hops, but BFS finds the first valid
     * shortest path.
     * 
     */

    private static class CurrencyRate {
        String currency;
        double rate;

        CurrencyRate(String currency, double rate) {
            this.currency = currency;
            this.rate = rate;
        }
    }

    private static class ParentRate {
        String currency;
        double rate;

        ParentRate(String currency, double rate) {
            this.currency = currency;
            this.rate = rate;
        }
    }

    public Map<String, Object> findShortestConversionPath(String inputStr, String fromCurrency, String toCurrency,
            double amount) {

        Map<String, List<Pair>> graph = new HashMap<>();

        String[] currencyExchanges = inputStr.split(",");

        for (String exchange : currencyExchanges) {
            String[] cur = exchange.split(":");
            String currency1 = cur[0];
            String currency2 = cur[1];
            double rate = Double.parseDouble(cur[3]);

            graph.computeIfAbsent(currency1, k -> new ArrayList<>()).add(new Pair(currency2, rate));
            graph.computeIfAbsent(currency2, k -> new ArrayList<>()).add(new Pair(currency1, 1.0 / rate));
        }

        Queue<CurrencyRate> queue = new LinkedList<>();
        queue.offer(new CurrencyRate(fromCurrency, 1.0));

        Map<String, ParentRate> discoveredFrom = new HashMap<>();
        discoveredFrom.put(fromCurrency, new ParentRate(null, 1.0));

        while (!queue.isEmpty()) {

            CurrencyRate cur = queue.poll();
            String curCurrency = cur.currency;
            double curRate = cur.rate;

            if (curCurrency.equals(toCurrency)) {
                break;
            }

            if (graph.containsKey(toCurrency)) {

                for (Pair nei : graph.get(curCurrency)) {
                    if (!discoveredFrom.containsKey(nei.currency)) {
                        discoveredFrom.put(nei.currency, new ParentRate(curCurrency, curRate * nei.rate));
                        queue.offer(new CurrencyRate(nei.currency, rate * nei.rate));
                    }

                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        if (!discoveredFrom.containsKey(toCurrency)) {
            result.put("route", "No routed found");
            result.put("rate", -1);
            result.put("converted_value", -1);
            return result;
        }

        List<String> path = new ArrayList<>();
        String currency = toCurrency;
        while (currency != null) {
            path.add(currency);
            currency = discoveredFrom.get(currency).currency;
        }

        Collections.reverse(path);

        result.put("route", String.join("->", path));
        result.put("rate", Math.round(discoveredFrom.get(toCurrency).rate * 100.0) / 100.0);
        result.put("converted_value", Math.round(discoveredFrom.get(toCurrency).rate * amount * 100) / 100.0);
        return result;
    }
}
