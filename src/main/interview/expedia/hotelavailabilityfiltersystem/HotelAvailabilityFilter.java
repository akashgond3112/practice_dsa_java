package main.interview.expedia.hotelavailabilityfiltersystem;

import java.util.*;
import java.time.LocalDate;

public class HotelAvailabilityFilter {

    public static void main(String[] args) {

        // Create test data
        List<Hotel> hotels = createHotels();
        List<Booking> bookings = createBookings();

        // Initialize checker and load bookings
        HotelAvailabilityChecker checker = new HotelAvailabilityChecker();
        checker.loadBookings(bookings);

        // Search for available hotels
        LocalDate checkIn = LocalDate.of(2025, 11, 15);
        LocalDate checkOut = LocalDate.of(2025, 11, 18);

        List<HotelResult> results = checker.filterAndPrice(hotels, checkIn, checkOut);

        // Display results
        displayResults(results, checkIn, checkOut);

        // Discuss complexity
        printComplexityAnalysis();
    }

    // ========== Test Data Setup ==========

    private static List<Hotel> createHotels() {
        return Arrays.asList(
                new Hotel("H1", "Grand Hotel", 150.0),
                new Hotel("H2", "Beach Resort", 200.0),
                new Hotel("H3", "City Inn", 100.0),
                new Hotel("H4", "Mountain Lodge", 180.0));
    }

    private static List<Booking> createBookings() {
        return Arrays.asList(
                // H1: booked Nov 16-17 (conflicts with our search)
                new Booking("H1", LocalDate.of(2025, 11, 16), LocalDate.of(2025, 11, 17)),

                // H2: booked Nov 14-15 (no conflict)
                new Booking("H2", LocalDate.of(2025, 11, 14), LocalDate.of(2025, 11, 15)),

                // H3: no bookings (available)

                // H4: booked Nov 15-16 (conflicts with our search)
                new Booking("H4", LocalDate.of(2025, 11, 15), LocalDate.of(2025, 11, 16)));
    }

    // ========== Display Results ==========

    private static void displayResults(List<HotelResult> results, LocalDate checkIn, LocalDate checkOut) {
        System.out.println("\n=== HOTEL SEARCH RESULTS ===");
        System.out.println("Check-in:  " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("\nAVAILABLE HOTELS:");
        System.out.println("--------------------");

        int count = 0;
        for (HotelResult result : results) {
            if (result.available) {
                count++;
                System.out.printf("%d. %s\n", count, result.hotel.name);
                System.out.printf("   $%.2f/night x %d nights = $%.2f total\n\n",
                        result.hotel.pricePerNight, result.nights, result.totalPrice);
            }
        }

        if (count == 0) {
            System.out.println("No hotels available for these dates.");
        }

        System.out.println("\nUNAVAILABLE HOTELS:");
        System.out.println("--------------------");
        for (HotelResult result : results) {
            if (!result.available) {
                System.out.println("✗ " + result.hotel.name + " (Booking conflict)");
            }
        }
    }

    private static void printComplexityAnalysis() {
        System.out.println("\n\n=== COMPLEXITY ANALYSIS ===");
        System.out.println("\n1. Load Bookings: O(B × D)");
        System.out.println("   - B = number of bookings");
        System.out.println("   - D = average days per booking");

        System.out.println("\n2. Check Availability: O(D)");
        System.out.println("   - D = days in search range");
        System.out.println("   - HashSet lookup is O(1)");

        System.out.println("\n3. Filter All Hotels: O(H × D)");
        System.out.println("   - H = number of hotels");

        System.out.println("\n=== OPTIMIZATIONS ===");
        System.out.println("\n1. Use Interval Tree → O(log N + K) for range queries");
        System.out.println("2. Cache popular date ranges");
        System.out.println("3. Database: Index on (hotel_id, date)");
        System.out.println("4. For large scale: Shard by location");
    }
}
