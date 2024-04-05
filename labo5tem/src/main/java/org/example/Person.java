package org.example;

/**
 * The Person class represents a person with an ID and a name.
 * This class is a record, which is an immutable data carrier.
 */
public record Person(int id, String name) {
    
}
