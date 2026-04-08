package main.google.leetCodeDiscussion;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PackageCompiler {

    // Mock APIs provided by the problem
    interface APIs {
        List<String> getDependencies(String pkg);

        void compile(String pkg);
    }

    private final APIs apis;
    private final int threadCount;
    private final ExecutorService executor;

    // Graph Structures
    private final ConcurrentHashMap<String, List<String>> adj = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtomicInteger> inDegree = new ConcurrentHashMap<>();
    private final Set<String> allPackages = ConcurrentHashMap.newKeySet();

    public PackageCompiler(APIs apis, int threadCount) {
        this.apis = apis;
        this.threadCount = threadCount;
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public void build(List<String> initialPackages) throws Exception {
        try {
            // Phase 1: Parallel Discovery
            discoverAllDependencies(initialPackages);

            // Phase 2: Cycle Detection (Standard DFS)
            if (hasCycle()) {
                throw new IllegalStateException("Circular dependency detected!");
            }

            // Phase 3: Parallel Compilation
            compileParallel();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Phase 1: Since getDependencies has latency, we use the thread pool
     * to crawl the dependency graph.
     */
    private void discoverAllDependencies(List<String> startNodes) throws InterruptedException {
        Queue<String> queue = new LinkedList<>(startNodes);
        allPackages.addAll(startNodes);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            CountDownLatch latch = new CountDownLatch(levelSize);

            for (int i = 0; i < levelSize; i++) {
                String pkg = queue.poll();
                executor.submit(() -> {
                    try {
                        List<String> deps = apis.getDependencies(pkg);
                        for (String dep : deps) {
                            // out-degree: dep -> pkg
                            adj.computeIfAbsent(dep, k -> new CopyOnWriteArrayList<>()).add(pkg);
                            // in-degree: pkg depends on 'dep'
                            inDegree.computeIfAbsent(pkg, k -> new AtomicInteger(0)).incrementAndGet();

                            if (allPackages.add(dep)) {
                                synchronized (queue) {
                                    queue.add(dep);
                                }
                            }
                        }
                    } finally {
                        latch.countDown();
                    }
                });
            }
            latch.await(); // Wait for this layer of discovery to finish
        }
    }

    /**
     * Phase 2: Detect cycles using DFS.
     * Essential because a cycle will cause the parallel compiler to hang forever.
     */
    private boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();
        for (String pkg : allPackages) {
            if (checkCycle(pkg, visited, recStack))
                return true;
        }
        return false;
    }

    private boolean checkCycle(String node, Set<String> visited, Set<String> recStack) {
        if (recStack.contains(node))
            return true;
        if (visited.contains(node))
            return false;

        visited.add(node);
        recStack.add(node);

        List<String> neighbors = adj.getOrDefault(node, new ArrayList<>());
        for (String neighbor : neighbors) {
            if (checkCycle(neighbor, visited, recStack))
                return true;
        }

        recStack.remove(node);
        return false;
    }

    /**
     * Phase 3: Kahn's Algorithm logic inside a Thread Pool.
     */
    private void compileParallel() throws InterruptedException {
        int totalToCompile = allPackages.size();
        CountDownLatch completionLatch = new CountDownLatch(totalToCompile);

        for (String pkg : allPackages) {
            // If no dependencies, it's ready to compile immediately
            if (inDegree.getOrDefault(pkg, new AtomicInteger(0)).get() == 0) {
                submitTask(pkg, completionLatch);
            }
        }

        completionLatch.await();
    }

    private void submitTask(String pkg, CountDownLatch latch) {
        executor.submit(() -> {
            try {
                apis.compile(pkg);

                // Once compiled, find all packages that were waiting for this one
                List<String> dependents = adj.getOrDefault(pkg, new ArrayList<>());
                for (String dep : dependents) {
                    // Decrement dependency count. If 0, it's ready!
                    if (inDegree.get(dep).decrementAndGet() == 0) {
                        submitTask(dep, latch);
                    }
                }
            } finally {
                latch.countDown();
            }
        });
    }
}