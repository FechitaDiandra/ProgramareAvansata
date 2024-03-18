import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a church attraction.
 */
public abstract class Church extends Attraction {

    /**
     * Constructs a new Church object with the given name.
     * @param name The name of the church.
     */
    public Church(String name) {
        super(name);
    }

    /**
     * Checks if the church is visitable.
     * @return True, indicating the church is always visitable.
     */
    @Override
    public boolean isVisitable() {
        return true; // Church is always visitable
    }

    /**
     * Retrieves the visiting timetable for the church.
     * @return A map containing the visiting timetable, mapping dates to time intervals.
     */
    @Override
    public Map<LocalDate, TimeInterval<LocalTime>> getVisitingTimetable() {
        Map<LocalDate, TimeInterval<LocalTime>> visitingTimetable = new HashMap<>();
        // Add time interval for each date the church is open
        visitingTimetable.put(LocalDate.of(2024, 3, 18), new TimeInterval<>(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        return visitingTimetable;
    }
}
