package org.example;

import org.example.model.Book;
import org.example.model.PublishingHouse;
import org.example.repository.BookRepository;
import org.example.repository.PublishingHouseRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crearea unei noi cărți și edituri
        BookRepository bookRepository = new BookRepository();
        PublishingHouseRepository publishingHouseRepository = new PublishingHouseRepository();

        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("Celestial Ink"); // Numele editurii
        publishingHouseRepository.create(publishingHouse);

        Book book = new Book();
        book.setTitle("Sample Book");
        book.setPublishingHouse(publishingHouse); // Setarea editurii pentru carte
        bookRepository.create(book);

        // Interogarea cărților după un anumit titlu
        List<Book> booksByName = bookRepository.findByName("Sample");
        System.out.println("Books by name pattern:");
        for (Book b : booksByName) {
            System.out.println(b.getId() + ". " + b.getTitle());
        }

        // Interogarea editurilor
        List<PublishingHouse> publishingHouses = publishingHouseRepository.findAll();
        System.out.println("Publishing Houses:");
        for (PublishingHouse ph : publishingHouses) {
            System.out.println(ph.getId() + ". " + ph.getName());
        }
    }
}
