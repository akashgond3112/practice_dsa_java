package main.interview.wise.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WiseCurrencyConverter {

    // 1. Graph Storage: Adjacency List
    // Map<SourceCurrency, Map<TargetCurrency, Rate>>
    // Use ConcurrentHashMap for thread safety in a real service
    private final Map<String, Map<String, BigDecimal>> exchangeRates = new ConcurrentHashMap<>();

    // Add a rate to the graph (Bi-directional or Uni-directional based on
    // requirement)
    public void addRate(String from, String to, double rate) {
        addRate(from, to, BigDecimal.valueOf(rate));
    }

    public void addRate(String from, String to, BigDecimal rate) {
        exchangeRates.computeIfAbsent(from, k -> new ConcurrentHashMap<>()).put(to, rate);
        // In real FX markets, the reverse rate is NOT exactly 1/rate due to
        // spread/fees,
        // but for a basic interview, usually they ask to add the inverse:
        exchangeRates.computeIfAbsent(to, k -> new ConcurrentHashMap<>())
                .put(from, BigDecimal.ONE.divide(rate, 8, RoundingMode.HALF_UP));
    }

    // 2. The Conversion Logic (BFS to find path)
    public BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) {
        if (fromCurrency.equals(toCurrency))
            return amount;

        // BFS Queue: Stores the current currency and the rate it took to get there
        Queue<Pair<String, BigDecimal>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new Pair<>(fromCurrency, BigDecimal.ONE));
        visited.add(fromCurrency);

        while (!queue.isEmpty()) {
            Pair<String, BigDecimal> current = queue.poll();
            String currentCurr = current.currency;
            BigDecimal currentRate = current.rate;

            // Check neighbors
            Map<String, BigDecimal> neighbors = exchangeRates.getOrDefault(currentCurr, Collections.emptyMap());

            for (Map.Entry<String, BigDecimal> entry : neighbors.entrySet()) {
                String neighborCurr = entry.getKey();
                BigDecimal rateToNeighbor = entry.getValue();

                if (!visited.contains(neighborCurr)) {
                    // Calculate cumulative rate: Current Path Rate * Step Rate
                    BigDecimal newRate = currentRate.multiply(rateToNeighbor);

                    if (neighborCurr.equals(toCurrency)) {
                        // Found the target! Calculate final amount
                        // Rounding is CRITICAL in Fintech (2 decimal places for output)
                        return amount.multiply(newRate).setScale(2, RoundingMode.HALF_UP);
                    }

                    visited.add(neighborCurr);
                    queue.add(new Pair<>(neighborCurr, newRate));
                }
            }
        }

        throw new IllegalArgumentException("No conversion path found between " + fromCurrency + " and " + toCurrency);
    }

    // Helper class
    static class Pair<K, V> {
        K currency;
        V rate;

        public Pair(K currency, V rate) {
            this.currency = currency;
            this.rate = rate;
        }
    }

    public static void main(String[] args) {
        WiseCurrencyConverter converter = new WiseCurrencyConverter();

        // Setup: USD -> GBP -> EUR (No direct USD -> EUR)
        converter.addRate("USD", "GBP", 0.80);
        converter.addRate("GBP", "EUR", 1.15);

        BigDecimal amountInUSD = new BigDecimal("100.00");

        try {
            // Should route USD -> GBP -> EUR
            // 100 * 0.80 = 80 GBP
            // 80 * 1.15 = 92 EUR
            BigDecimal result = converter.convert("USD", "EUR", amountInUSD);
            System.out.println("100 USD = " + result + " EUR");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
