package org.example;

/**
 * A class representing a destination with a name and a count of passengers.
 */
public class Destination {
    private String name;
    private int passengerCount;

    /**
     * Constructs a Destination object with the given name and passenger count.
     *
     * @param name           The name of the destination.
     * @param passengerCount The count of passengers for the destination.
     */
    public Destination(String name, int passengerCount) {
        this.name = name;
        this.passengerCount = passengerCount;
    }

    /**
     * Retrieves the name of the destination.
     *
     * @return The name of the destination.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the count of passengers for the destination.
     *
     * @return The count of passengers.
     */
    public int getPassengerCount() {
        return passengerCount;
    }
}
