package main.interview.jp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import java.time.LocalDateTime;

public class RestaurantBookingSystem {

    // Represents a reserved time window
    record Reservation(String bookingId, LocalDateTime start, LocalDateTime end) {
    }

    private static class Table {
        final int id;
        final int capacity;
        // List of existing reservations for this table
        private final List<Reservation> reservations = new ArrayList<>();

        Table(int id, int capacity) {
            this.id = id;
            this.capacity = capacity;
        }

        // Thread-safe check and add
        synchronized boolean tryBook(String bookingId, LocalDateTime start, int durationMinutes) {
            LocalDateTime end = start.plusMinutes(durationMinutes);

            // Check for overlaps
            for (Reservation r : reservations) {
                if (start.isBefore(r.end) && end.isAfter(r.start)) {
                    return false; // Overlap found
                }
            }
            reservations.add(new Reservation(bookingId, start, end));
            return true;
        }

        synchronized void cancel(String bookingId) {
            reservations.removeIf(r -> r.bookingId().equals(bookingId));
        }
    }

    private final List<Table> allTables = new ArrayList<>();
    private final TreeMap<Integer, List<Table>> capacityMap = new TreeMap<>();
    private final Map<String, Table> bookingsToTableMap = new ConcurrentHashMap<>();

    public RestaurantBookingSystem(Map<Integer, Integer> tableConfigs) {
        int idCounter = 1;
        for (Map.Entry<Integer, Integer> entry : tableConfigs.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Table t = new Table(idCounter++, entry.getKey());
                allTables.add(t); // Track all tables here
                capacityMap.computeIfAbsent(t.capacity, k -> new ArrayList<>()).add(t);
            }
        }
    }

    public synchronized void book(String bookingId, int partySize, LocalDateTime time, int duration) {
        // tailMap finds tables with capacity >= partySize (Best-Fit)
        for (List<Table> tablesOfSize : capacityMap.tailMap(partySize).values()) {
            for (Table table : tablesOfSize) {
                if (table.tryBook(bookingId, time, duration)) {
                    bookingsToTableMap.put(bookingId, table);
                    System.out.println("Booked " + bookingId + " at Table #" + table.id);
                    return;
                }
            }
        }
        System.out.println("Rejected: " + bookingId + " - No suitable table.");
    }

    public void cancel(String bookingId) {
        Table table = bookingsToTableMap.remove(bookingId);
        if (table != null)
            table.cancel(bookingId);
    }

    public void cleanupExpiredReservations() {
        LocalDateTime now = LocalDateTime.now();
        for (Table t : allTables) {
            synchronized (t) {
                t.reservations.removeIf(r -> r.end().isBefore(now));
            }
        }
    }

    public void processBookingRequest(String csvLine) {
        String[] parts = csvLine.split(",");
        String id = parts[0];
        int size = Integer.parseInt(parts[1]);
        LocalDateTime time = LocalDateTime.parse(parts[2]);
        int duration = Integer.parseInt(parts[3]);
        book(id, size, time, duration);
    }

    public static void main(String[] args) {
        // Setup: 2 tables of size 2, 2 tables of size 4
        Map<Integer, Integer> tableConfigs = Map.of(2, 2, 4, 2);
        RestaurantBookingSystem system = new RestaurantBookingSystem(tableConfigs);

        LocalDateTime baseTime = LocalDateTime.of(2024, 1, 15, 19, 0);

        System.out.println("--- Test 1: Normal Bookings ---");
        system.book("booking-001", 4, baseTime, 90); // Should succeed
        system.book("booking-002", 2, baseTime.plusMinutes(30), 60); // Should succeed

        System.out.println("\n--- Test 2: Overlapping Booking (Conflict) ---");
        // booking-001 is 19:00 - 20:30.
        // This request is 19:15 - 20:45 (Overlaps!)
        system.book("booking-003", 4, baseTime.plusMinutes(15), 90);

        System.out.println("\n--- Test 3: Non-Overlapping Booking (Success) ---");
        // booking-001 ends at 20:30.
        // This request starts at 20:30 (No overlap!)
        system.book("booking-004", 4, baseTime.plusMinutes(90), 90);

        System.out.println("\n--- Test 4: Cancellation ---");
        system.cancel("booking-002");
        // Now this should succeed because booking-002 was cancelled
        system.book("booking-005", 2, baseTime.plusMinutes(30), 60);

        System.out.println("\n--- Test 5: Multi-threaded Stress Test ---");
        Runnable task = () -> {
            system.book("thread-booking-" + Thread.currentThread().getId(), 4, baseTime, 60);
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}