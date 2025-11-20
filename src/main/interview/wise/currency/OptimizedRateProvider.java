package main.interview.wise.currency;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

public class OptimizedRateProvider {

    // Simulated External HTTP Service
    interface ExternalForexService {
        BigDecimal getRate(String from, String to);

        Map<String, BigDecimal> getBulkRates(List<String> pairs);
    }

    private final ExternalForexService forexService;

    // CACHE: Valid for x seconds (TTL)
    private final Map<String, CachedRate> rateCache = new ConcurrentHashMap<>();

    static class CachedRate {
        BigDecimal rate;
        long timestamp;

        public CachedRate(BigDecimal rate) {
            this.rate = rate;
            this.timestamp = System.currentTimeMillis();
        }

        boolean isExpired() {
            return (System.currentTimeMillis() - timestamp) > 60000; // 1 min TTL
        }
    }

    public OptimizedRateProvider(ExternalForexService service) {
        this.forexService = service;
    }

    // OPTIMIZATION 1: Caching
    public BigDecimal getRate(String from, String to) {
        String key = from + ":" + to;

        if (rateCache.containsKey(key) && !rateCache.get(key).isExpired()) {
            return rateCache.get(key).rate;
        }

        // Cache miss - Call HTTP
        BigDecimal rate = forexService.getRate(from, to);
        rateCache.put(key, new CachedRate(rate));
        return rate;
    }

    // OPTIMIZATION 2: Bulk/Batch Processing (Reducing N+1 HTTP calls)
    // If we have a list of transfers to process, fetch all rates in ONE HTTP call
    public void preFetchRates(List<String> fromCurrencies, List<String> toCurrencies) {
        List<String> pairsToFetch = new ArrayList<>();

        for (int i = 0; i < fromCurrencies.size(); i++) {
            String key = fromCurrencies.get(i) + ":" + toCurrencies.get(i);
            if (!rateCache.containsKey(key) || rateCache.get(key).isExpired()) {
                pairsToFetch.add(key);
            }
        }

        if (!pairsToFetch.isEmpty()) {
            // Make ONE call for 50 rates instead of 50 calls
            Map<String, BigDecimal> bulkRates = forexService.getBulkRates(pairsToFetch);

            for (Map.Entry<String, BigDecimal> entry : bulkRates.entrySet()) {
                rateCache.put(entry.getKey(), new CachedRate(entry.getValue()));
            }
        }
    }
}
