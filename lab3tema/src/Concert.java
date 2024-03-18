import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a concert attraction.
 */
public abstract class Concert extends Attraction implements Payable {
    private double price; // Pretul concertului

    /**
     * Constructs a new Concert object with the given name.
     * @param name The name of the concert.
     */
    public Concert(String name, double price) {
        super(name);
        this.price = price;
    }

    /**
     * Retrieves the visiting timetable for the concert.
     * Concerts might not have specific visiting timetables, so an empty map is returned.
     * @return An empty map indicating no specific visiting timetable for the concert.
     */
    @Override
    public Map<LocalDate, TimeInterval<LocalTime>> getVisitingTimetable() {
        return new HashMap<>();
    }

    /**
     * Checks if the concert is visitable.
     * @return True, indicating the concert is always visitable.
     */
    @Override
    public boolean isVisitable() {
        return true; // Concerts are always visitable
    }

    /**
     * Retrieves the price of the concert.
     * @return The price of the concert.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the Concert object.
     *
     * @return A string representation of the Concert object.
     */
    @Override
    public String toString() {
        return "Concert{" +
                "price=" + price +
                '}';
    }
}
