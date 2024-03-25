package org.example;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to solve a problem of matching drivers with passengers based on destinations using a greedy algorithm.
 */
public class ProblemSolver {
    private final List<Person> drivers;
    private final List<Person> passengers;

    /**
     * Constructs a ProblemSolver object with given lists of drivers, passengers, and destinations.
     *
     * @param drivers      List of drivers.
     * @param passengers   List of passengers.
     * @param destinations List of destinations.
     */
    public ProblemSolver(List<Person> drivers, List<Person> passengers, List<Destination> destinations) {
        this.drivers = drivers;
        this.passengers = passengers;
    }


    /**
     * Computes the destinations of all drivers based on the destinations they can travel to using Java Stream API.
     *
     * @return List of unique destinations of all drivers.
     */
    private List<String> computeDestinationsOfDrivers() {
        return drivers.stream()
                .flatMap(driver -> driver.getDestinations().stream()) // Converteste lista de soferi intr-un flux de destinatii
                .distinct() // Sterge duplicatele
                .collect(Collectors.toList()); // Aduna destinatiile intr o lista
    }

    /**
     * Computes a map of destinations to the list of passengers going to each destination using Java Stream API.
     *
     * @return Map where keys are destinations and values are lists of passengers traveling to each destination.
     */
    private Map<String, List<Person>> computeDestinationMap() {
        return passengers.stream()
                .flatMap(passenger -> passenger.getDestinations().stream() // Converteste lista de pasageri intr un flux de perechi destinatie-persoana
                        .map(destination -> new AbstractMap.SimpleEntry<>(destination, passenger)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, //Grupeaza dupa destinatii

                        Collectors.mapping(Map.Entry::getValue, Collectors.toList()))); // Mapeaza valorile in lista de pasageri
    }


    /**
     * Solves the problem of matching drivers with passengers using a greedy algorithm.
     * Randomly assigns passengers to drivers for each destination.
     *
     * @return Map where keys are destinations and values are lists of passengers assigned to each destination's driver.
     */
    public Map<String, List<Person>> solveGreedy() {
        List<String> destinations = computeDestinationsOfDrivers();//disponibile
        Map<String, List<Person>> destinationMap = computeDestinationMap();//map:key-ldestinatiile, valorile-listeel de pasageri
        Map<String, List<Person>> result = new HashMap<>();
        //pt fiecare locatie disponibila
        for (String destination : destinations) {
            List<Person> passengersForDestination = destinationMap.getOrDefault(destination, new ArrayList<>());//obtin lista de pasageri pt locatia curenta din mapul destinatiilor
            Collections.shuffle(passengersForDestination);//amesteca lista aleatoriu
            int passengersNeeded = drivers.size(); // Fiecare sofer poate lua un pasager
            List<Person> passengersAssigned = passengersForDestination.subList(0, Math.min(passengersNeeded, passengersForDestination.size()));
            result.put(destination, passengersAssigned);//adauga in map asocierea dintre destinatie si pasageri
        }

        return result;
    }

    /**
     * Generates a random name for a destination.
     *
     * @return Randomly generated destination name.
     */
    private String generateRandomDestinationName() {
        return RandomStringUtils.randomAlphabetic(8);
    }
}