package main.interview.expedia.hotelavailabilityfiltersystem;

import java.time.LocalDate;

public class Booking {
    String hotelId;
    LocalDate checkIn;
    LocalDate checkOut;

    public Booking(String hotelId, LocalDate checkIn, LocalDate checkOut) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
