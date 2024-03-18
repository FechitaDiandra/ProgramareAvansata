import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class representing an attraction that can be visited.
 * It implements the Visitable and Payable interfaces.
 */
public abstract class Attraction implements Visitable, Payable {
    private final String name;
    private final Map<LocalDate, TimeInterval<LocalTime>> visitingTimetable;
    private final boolean visitable;

    /**
     * Constructs a new Attraction object with the given name.
     * @param name The name of the attraction.
     */
    public Attraction(String name) {
        this.name = name;
        this.visitingTimetable = new HashMap<>();
        this.visitable = true; // Implicit, all attractions are visitable
    }

    /**
     * Checks if the attraction is visitable.
     * @return True, indicating the attraction is visitable.
     */
    public boolean isVisitable() {
        return visitable;
    }

    /**
     * Checks if the attraction is payable.
     * @return True if the attraction's name starts with "Paid", indicating it is payable.
     */
    public boolean isPayable() {
        return name.startsWith("Paid"); // Check if the name starts with "Paid"
    }

    /**
     * Gets the description of the attraction.
     * @return A description of the attraction.
     */
    public String getDescription() {
        return "This attraction is a museum dedicated to history.";
    }

    /**
     * Adds visiting timetable for the attraction on a specific date.
     * @param date The date for which to add visiting timetable.
     * @param timeInterval The time interval for the visiting timetable.
     */
    public void addVisitingTimetable(LocalDate date, TimeInterval<LocalTime> timeInterval) {
        visitingTimetable.put(date, timeInterval);
    }

    /**
     * Gets the visiting timetable for the attraction on a specific date.
     * @param date The date for which to retrieve the visiting timetable.
     * @return The time interval representing the visiting timetable for the given date.
     */
    public TimeInterval<LocalTime> getVisitingTimetable(LocalDate date) {
        return visitingTimetable.get(date);
    }

    /**
     * Gets the name of the attraction.
     * @return The name of the attraction.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the Attraction object.
     * @return A string representation of the Attraction object.
     */
    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                ", visitingTimetable=" + visitingTimetable +
                '}';
    }

    public abstract <U> U getOpeningHour(java.time.LocalDate now);
}
