package main.interview.expedia.hotelavailabilityfiltersystem;

class HotelResult {
    Hotel hotel;
    boolean available;
    double totalPrice;
    long nights;

    public HotelResult(Hotel hotel, boolean available, double totalPrice, long nights) {
        this.hotel = hotel;
        this.available = available;
        this.totalPrice = totalPrice;
        this.nights = nights;
    }
}
