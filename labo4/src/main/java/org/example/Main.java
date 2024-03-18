package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Generăm o listă de persoane aleatorii
        List<Person> persons = generateRandomPersons(10);

        // Separăm persoanele în șoferi și pasageri
        List<Person> drivers = persons.stream()
                .filter(Person::isDriver)
                .collect(Collectors.toList());
        List<Person> passengers = persons.stream()
                .filter(person -> !person.isDriver())
                .collect(Collectors.toList());

        // Sortăm șoferii după vârstă
        drivers.sort(Comparator.comparingInt(Person::getAge));

        // Punem pasagerii într-un TreeSet sortat după nume
        Set<Person> passengersTreeSet = new TreeSet<>(Comparator.comparing(Person::getName));
        passengersTreeSet.addAll(passengers);

        // Convertim șoferii într-o LinkedList
        List<Person> driversLinkedList = new LinkedList<>(drivers);

        // Afișăm șoferii sortați după vârstă
        System.out.println("Șoferii sortați după vârstă:");
        driversLinkedList.forEach(System.out::println);

        // Afișăm pasagerii sortați după nume
        System.out.println("\nPasagerii sortați după nume:");
        passengersTreeSet.forEach(System.out::println);
    }

    // Lista de nume aleatorii
    private static List<String> names = Arrays.asList("Alice", "Bobi", "Carla", "David", "Ema", "Fanuri", "Gratiela", "Henry", "Isabela", "Ion");

    // Generează o listă de persoane aleatorii
    private static List<Person> generateRandomPersons(int count) {
        Random random = new Random();
        return Stream.generate(() ->
                        new Person(names.get(random.nextInt(names.size())), random.nextInt(50) + 18, random.nextBoolean()))
                .limit(count)
                .collect(Collectors.toList());
    }
}
