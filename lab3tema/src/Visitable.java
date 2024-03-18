import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * Interface for attractions that can be visited.
 */
public interface Visitable {

    /**
     * Retrieves the visiting timetable for the attraction.
     * @return A map containing the visiting timetable, mapping dates to time intervals.
     */
    Map<LocalDate, TimeInterval<LocalTime>> getVisitingTimetable();

    /**
     * Gets the opening hour for the attraction on the given date.
     * If the visiting timetable contains information for the specified date,
     * it returns the start time of the interval for that date.
     * Otherwise, it returns null.
     * @param date The date for which to retrieve the opening hour.
     * @return The opening hour for the attraction on the given date, or null if not available.
     */
    default LocalTime getOpeningHoursByDate(LocalDate date) {
        // Obține orarul de vizitare al atracției
        Map<LocalDate, TimeInterval<LocalTime>> orarVizitare = getVisitingTimetable();

        // Verifică dacă orarul de vizitare conține informații pentru data specificată
        if (orarVizitare.containsKey(date)) {
            // Recuperează intervalul de timp pentru data specificată
            TimeInterval<LocalTime> interval = orarVizitare.get(date);
            // Returnează ora de început a intervalului
            return interval.getStart();
        }

        // Returnează null dacă orarul de vizitare nu conține informații pentru data specificată
        return null;
    }
}
