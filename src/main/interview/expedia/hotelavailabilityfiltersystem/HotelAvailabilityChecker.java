package main.interview.expedia.hotelavailabilityfiltersystem;

import java.util.*;
import java.time.LocalDate;

class HotelAvailabilityChecker {

    // Store booked dates for each hotel
    // Key: hotelId, Value: Set of booked dates
    private Map<String, Set<LocalDate>> bookedDatesMap;

    public HotelAvailabilityChecker() {
        this.bookedDatesMap = new HashMap<>();
    }

    /**
     * STEP 1: Load all bookings and create unavailable dates map
     * 
     * Time: O(B * D) where B = bookings, D = days per booking
     * Space: O(B * D)
     * 
     * Initially thought of complex interval tree, but HashSet is simpler!
     */
    public void loadBookings(List<Booking> bookings) {
        for (Booking booking : bookings) {
            bookedDatesMap.putIfAbsent(booking.hotelId, new HashSet<>());

            // Mark all dates from check-in to check-out as booked
            LocalDate date = booking.checkIn;
            while (!date.isAfter(booking.checkOut)) {
                bookedDatesMap.get(booking.hotelId).add(date);
                date = date.plusDays(1);
            }
        }
    }

    /**
     * STEP 2: Check if hotel has CONTINUOUS availability
     * 
     * Key insight: ALL dates between check-in and check-out must be free
     * 
     * Time: O(D) where D = days in range
     * Space: O(1)
     */
    public boolean isContinuouslyAvailable(String hotelId, LocalDate checkIn, LocalDate checkOut) {
        Set<LocalDate> bookedDates = bookedDatesMap.getOrDefault(hotelId, new HashSet<>());

        // Check every date in the requested range
        LocalDate date = checkIn;
        while (date.isBefore(checkOut)) { // Don't check checkout date
            if (bookedDates.contains(date)) {
                return false; // Found a booked date - no continuous availability
            }
            date = date.plusDays(1);
        }

        return true; // All dates are free
    }

    /**
     * STEP 3: Filter hotels and calculate prices
     * 
     * Time: O(H * D) where H = hotels, D = days
     */
    public List<HotelResult> filterAndPrice(List<Hotel> hotels, LocalDate checkIn, LocalDate checkOut) {
        List<HotelResult> results = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (isContinuouslyAvailable(hotel.id, checkIn, checkOut)) {
                // Calculate price
                long nights = checkIn.until(checkOut).getDays();
                double totalPrice = hotel.pricePerNight * nights;

                results.add(new HotelResult(hotel, true, totalPrice, nights));
            } else {
                results.add(new HotelResult(hotel, false, 0, 0));
            }
        }

        return results;
    }
}
