package org.example;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            AuthorDAO authorDAO = new AuthorDAO();

            // Adăugare autori
            authorDAO.addAuthor("Stephen King");
            authorDAO.addAuthor("J.K. Rowling");
            authorDAO.addAuthor("George Orwell");
            authorDAO.addAuthor("Harper Lee");
            authorDAO.addAuthor("Agatha Christie");
            authorDAO.addAuthor("Ernest Hemingway");
            authorDAO.addAuthor("Jane Austen");

            authorDAO.close();

            BookDAO bookDAO = new BookDAO();

            // Adăugare cărți
            bookDAO.addBook("The Shining", "English", Date.valueOf("1977-01-28"), 447, 2); // Stephen King
            bookDAO.addBook("Harry Potter and the Philosopher's Stone", "English", Date.valueOf("1997-06-26"), 309, 3); // J.K. Rowling
            bookDAO.addBook("1984", "English", Date.valueOf("1949-06-08"), 328, 4); // George Orwell
            bookDAO.addBook("To Kill a Mockingbird", "English", Date.valueOf("1960-07-11"), 281, 5); // Harper Lee
            bookDAO.addBook("Murder on the Orient Express", "English", Date.valueOf("1934-01-01"), 265, 6); // Agatha Christie
            bookDAO.addBook("The Old Man and the Sea", "English", Date.valueOf("1952-09-01"), 127, 7); // Ernest Hemingway
            bookDAO.addBook("Pride and Prejudice", "English", Date.valueOf("1813-01-28"), 279, 8); // Jane Austen

            bookDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

