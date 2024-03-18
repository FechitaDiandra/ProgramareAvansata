import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a statue attraction.
 */
public abstract class Statue extends Attraction {

    /**
     * Constructs a new Statue object with the given name.
     * @param name The name of the statue.
     */
    public Statue(String name) {
        super(name);
    }

    /**
     * Retrieves the visiting timetable for the statue.
     * @return A map containing the visiting timetable, mapping dates to time intervals.
     */
    @Override
    public Map<LocalDate, TimeInterval<LocalTime>> getVisitingTimetable() {
        Map<LocalDate, TimeInterval<LocalTime>> visitingTimetable = new HashMap<>();
        // Add time interval for each date the statue is open
        visitingTimetable.put(LocalDate.of(2024, 3, 17), new TimeInterval<>(LocalTime.of(8, 0), LocalTime.of(16, 0)));
        return visitingTimetable;
    }

    /**
     * Checks if the statue is visitable.
     * @return True, indicating the statue is always visitable.
     */
    @Override
    public boolean isVisitable() {
        return true; // The statue is always visitable
    }
}
