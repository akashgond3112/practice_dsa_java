/**
 * @author akash
 * @date Sep 23, 2026
 * @time 10:35:55 AM
 */
package main.interview.stripe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FlightRoutingCostOptimization {

    /**
     * <b>Input:</b>
     * input_str = "UK:US:FedEx:4,UK:FR:Jet1:2,US:UK:RyanAir:8,CA:UK:CanadaAir:8"
     * source = "UK"
     * destination = "US"
     * 
     * <b>Output:</b> 4
     * 
     * <b>Explanation:</b> Direct flight from UK to US via FedEx costs 4
     * 
     */
    public static int getFlightCost(String inputStr, String source, String destination) {

        String[] flights = inputStr.split(",");

        Map<String, Integer> map = new HashMap<>();

        for (String flight : flights) {
            String[] parts = flight.split(":");
            String src = parts[0];
            String dest = parts[1];

            int cost = Integer.parseInt(parts[2]);

            map.put(src + "-" + dest, cost);
        }

        String key = source + "-" + destination;

        return map.getOrDefault(key, -1);

    }

    /**
     * <b>Input:</b>
     * input_str = "UK:FR:Jet1:2,FR:US:AmericanAir:6"
     * source = "UK"
     * destination = "US"
     * 
     * <b>Output:</b>
     * {
     * 'route': 'UK -> FR -> US',
     * 'method': 'Jet1 -> AmericanAir',
     * 'cost': 8
     * }
     * 
     * <b>Explanation:</b>
     * UK -> FR: via Jet1 costs 2
     * FR -> US: via AmericanAir costs 6
     * Total cost: 2 + 6 = 8
     * 
     */
    public static int getFlightCostWithOneStop(String inputStr, String source, String destination) {

        String[] flights = inputStr.split(",");

        Map<String, Integer> map = new HashMap<>();

        for (String flight : flights) {
            String[] parts = flight.split(":");
            String src = parts[0];
            String dest = parts[1];

            int cost = Integer.parseInt(parts[2]);

            map.put(src + "-" + dest, cost);
        }

        String key = source + "-" + destination;

        if (map.containsKey(key))
            return map.getOrDefault(key, -1);

        int min = Integer.MAX_VALUE;

        for (Entry<String, Integer> entry : map.entrySet()) {

            String[] cur = entry.getKey().split("-");
            String src = cur[0];
            String inter = cur[1];
            int curCost = entry.getValue();

            if (src.equals(source)) {

                String secondKey = inter + "-" + destination;

                if (map.containsKey(secondKey)) {
                    int totalCost = curCost + map.get(secondKey);
                    min = Math.min(min, totalCost);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }

    /**
     * <b>Input:</b>
     * input_str = "UK:FR:Jet1:2,FR:DE:Lufthansa:3,DE:US:Delta:5"
     * source = "UK"
     * destination = "US"
     * 
     * <b>Output:</b>
     * {
     * 'route': 'UK -> FR -> DE -> US',
     * 'method': 'Jet1 -> Lufthansa -> Delta',
     * 'cost': 10
     * }
     * 
     * <b>Explanation:</b>
     * UK -> FR: via Jet1 costs 2
     * FR -> DE: via Lufthansa costs 3
     * DE -> US: via Delta costs 5
     * Total cost: 2 + 3 + 5 = 10
     * 
     */

    private Map<String, List<FlightInfo>> graph;
    private int totalCost;
    private List<String> path;
    private List<String> methods;
    private Set<String> visited;

    private static class FlightInfo {
        String destination;
        String airline;
        int cost;

        FlightInfo(String destination, String airline, int cost) {
            this.destination = destination;
            this.airline = airline;
            this.cost = cost;
        }
    }

    public Map<String, Object> findAnyRoute(String inputStr, String source, String destination) {

        String[] flights = inputStr.split(",");

        for (String flight : flights) {
            String[] parts = flight.split(":");
            String src = parts[0];
            String dest = parts[1];
            String airline = parts[1];

            int cost = Integer.parseInt(parts[3]);

            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(new FlightInfo(dest, airline, cost));
        }

        totalCost = 0;
        path = new ArrayList<>();
        methods = new ArrayList<>();
        visited = new HashSet<>();

        Map<String, Object> result = new HashMap<>();

        if (!dfs(source, destination, null, 0)) {
            result.put("route", "No route found");
            result.put("method", "");
            result.put("cost", 0);
        } else {
            result.put("route", String.join(" -> ", path));
            result.put("method", String.join(" -> ", methods));
            result.put("cost", totalCost);
        }

        return result;

    }

    public boolean dfs(String src, String dest, String method, int cost) {

        visited.add(src);
        path.add(src);
        if (method != null) {
            methods.add(method);
        }

        totalCost += cost;

        if (src.equals(dest))
            return true;

        if (graph.containsKey(src)) {

            for (FlightInfo flightInfo : graph.get(src)) {

                if (!visited.contains(flightInfo.destination)
                        && dfs(flightInfo.destination, dest, flightInfo.airline, flightInfo.cost)) {
                    return true;
                }

            }
        }

        // backTrack
        visited.remove(src);
        path.removeLast();
        if (!methods.isEmpty()) {
            methods.removeLast();
        }

        totalCost += cost;

        return false;
    }
}
