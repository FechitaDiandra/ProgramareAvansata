package org.example;

/**
 * This class represents a driver, which is a type of person.
 */
public class Driver extends Person {

    /**
     * Constructs a driver with the specified name and age.
     * @param name The name of the driver.
     * @param age The age of the driver.
     */
    public Driver(String name, int age) {
        super(name, age);
    }

    /**
     * Returns a string representation of the driver.
     * @return A string representation of the driver.
     */
    @Override
    public String toString() {
        return "Driver: " + getName();
    }
}
