package org.example;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        // Generare nume aleatorii pentru persoane
        String personName1 = faker.name().fullName();
        String personName2 = faker.name().fullName();

        // Generare nume aleatorii pentru destinații
        String destinationName1 = faker.address().country();
        String destinationName2 = faker.address().country();

        // Afișare nume generate
        System.out.println("Nume persoana 1: " + personName1);
        System.out.println("Nume persoana 2: " + personName2);
        System.out.println("Nume destinatie 1: " + destinationName1);
        System.out.println("Nume destinatie 2: " + destinationName2);
    }
}
