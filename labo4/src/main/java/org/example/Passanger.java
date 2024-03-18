package org.example;

/**
 * This class represents a passenger, which is a type of person.
 */
class Passenger extends Person {

    /**
     * Constructs a passenger with the specified name and age.
     * @param name The name of the passenger.
     * @param age The age of the passenger.
     */
    public Passenger(String name, int age) {
        super(name, age);
    }

    /**
     * Returns a string representation of the passenger.
     * @return A string representation of the passenger.
     */
    @Override
    public String toString() {
        return "Passenger: " + getName();
    }
}
