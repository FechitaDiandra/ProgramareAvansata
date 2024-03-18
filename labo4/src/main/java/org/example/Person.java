package org.example;

import java.util.Objects;

/**
 * This class represents a person.
 */
public class Person {
    private String name;
    private int age;
    private boolean isDriver;

    /**
     * Constructs a person with the specified name, age, and driver status.
     * @param name The name of the person.
     * @param age The age of the person.
     * @param isDriver Indicates whether the person is a driver.
     */
    public Person(String name, int age, boolean isDriver) {
        this.name = name;
        this.age = age;
        this.isDriver = isDriver;
    }

    /**
     * Constructs a person with the specified name and age.
     * @param name The name of the person.
     * @param age The age of the person.
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Checks if the person is a driver.
     * @return true if the person is a driver, false otherwise.
     */
    public boolean isDriver() {
        return isDriver;
    }

    /**
     * Gets the name of the person.
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the person.
     * @return The age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a string representation of the person.
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // Auto-generated hashCode() and equals() for simplicity
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && Objects.equals(name, person.name);
    }
}
