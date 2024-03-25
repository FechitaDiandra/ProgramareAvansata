package org.example;

import java.util.Set;

/**
 * A class representing a person with a name and a set of destinations they can travel to.
 */
public class Person {
    private String name;
    private Set<String> destinations;

    /**
     * Constructs a Person object with the given name and destinations.
     *
     * @param name         The name of the person.
     * @param destinations The set of destinations the person can travel to.
     */
    public Person(String name, Set<String> destinations) {
        this.name = name;
        this.destinations = destinations;
    }

    /**
     * Retrieves the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the destinations the person can travel to.
     *
     * @param destinations The set of destinations.
     */
    public void setDestinations(Set<String> destinations) {
        this.destinations = destinations;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the set of destinations the person can travel to.
     *
     * @return The set of destinations.
     */
    public Set<String> getDestinations() {
        return destinations;
    }

    /**
     * Returns a string representation of the person.
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", destinations=" + destinations +
                '}';
    }
}
