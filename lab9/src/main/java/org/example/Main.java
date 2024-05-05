package org.example;

import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.util.EntityManagerUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Obține instanța de EntityManagerUtil
        EntityManagerUtil entityManagerUtil = EntityManagerUtil.getInstance();
        BookRepository bookRepository = new BookRepository(entityManagerUtil);

        try {
            Book book1 = new Book();
            book1.setTitle("Harry Potter");
            bookRepository.create(book1);

            // Find a book by ID
            Book foundBook = bookRepository.findById(1);
            if (foundBook != null) {
                System.out.println("Found book: " + foundBook.getTitle());
            } else {
                System.out.println("Book not found.");
            }

            // Find books by name pattern
            List<Book> booksByName = bookRepository.findByName("Harry Potter");
            System.out.println("Books by name pattern:");
            if (!booksByName.isEmpty()) {
                for (Book book : booksByName) {
                    System.out.println(book.getId() + ". " + book.getTitle());
                }
            } else {
                System.out.println("No books found with the specified name pattern.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Închide EntityManagerUtil
            entityManagerUtil. closePersistenceUnit();
        }
    }
}
