/**
 * Represents a drone vehicle with a maximum flight duration.
 */
public class Drone extends Vehicle {
    private int maxFlightDuration;

    /**
     * Constructs a Drone object with the given ID and maximum flight duration.
     *
     * @param id               The ID of the drone.
     * @param maxFlightDuration The maximum flight duration of the drone.
     */
    public Drone(String id, int maxFlightDuration) {
        super(id);
        this.maxFlightDuration = maxFlightDuration;
    }

    /**
     * Retrieves the maximum flight duration of the drone.
     *
     * @return The maximum flight duration of the drone.
     */
    public int getMaxFlightDuration() {
        return this.maxFlightDuration;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "maxFlightDuration=" + maxFlightDuration +
                '}';
    }
}
