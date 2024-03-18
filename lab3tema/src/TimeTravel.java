import java.time.LocalTime;

/**
 * Represents a time interval.
 * @param <T> The type of the time interval.
 */
class TimeInterval<T> {
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructs a new TimeInterval object with the given start and end time.
     * @param startTime The start time of the interval.
     * @param endTime The end time of the interval.
     */
    public TimeInterval(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the start time of the interval.
     * @return The start time of the interval.
     */
    public LocalTime getStart() {
        return startTime;
    }

    /**
     * Gets the end time of the interval.
     * @return The end time of the interval.
     */
    public LocalTime getEnd() {
        return endTime;
    }

    /**
     * Returns a string representation of the TimeInterval object.
     * @return A string representation of the TimeInterval object.
     */
    @Override
    public String toString() {
        return startTime + " - " + endTime;
    }
}
