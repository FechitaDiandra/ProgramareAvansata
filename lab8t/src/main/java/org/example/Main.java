package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testDatabaseConnection();
        testAuthorDAO();
        testBookClass();
        testBookDAO();
        addNewBookAndAuthors();
    }

    private static void addNewBookAndAuthors() {
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.addAuthor("Smith Marceline");
        authorDAO.addAuthor("Leo Tolstoy");

        BookDAO bookDAO = new BookDAO();
        bookDAO.importBooksFromCSV("C:\\Users\\User\\Desktop\\Java\\lab8t\\src\\main\\java\\org\\example\\books.csv");

        System.out.println("Data from the CSV file has been successfully imported!");
    }



    private static void testDatabaseConnection() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connection to the database has been established successfully!");
            } else {
                System.out.println("Connection to the database failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testAuthorDAO() {
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.addAuthor("J.R.R. Tolkien");
        authorDAO.addAuthor("Ernest Hemingway");

        List<String> authors = authorDAO.getAllAuthors();
        System.out.println("List of authors:");
        for (String author : authors) {
            System.out.println(author);
        }
    }

    private static void testBookClass() {
        Book book = new Book(1, "The Great Gatsby", "English", LocalDate.of(1925, 4, 10), 180, 4);

        System.out.println("Book details:");
        System.out.println("ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Language: " + book.getLanguage());
        System.out.println("Publication Date: " + book.getPublicationDate());
        System.out.println("Number of Pages: " + book.getNumberOfPages());
        System.out.println("Author ID: " + book.getAuthorId());
        System.out.println();
    }

    private static void testBookDAO() {
        BookDAO bookDAO = new BookDAO();

        String title = "The Catcher in the Rye";
        String language = "English";
        LocalDate publicationDate = LocalDate.of(1951, 7, 16);
        int numberOfPages = 214;
        int authorId = 5;

        if (!bookDAO.exists(title, language, publicationDate, numberOfPages, authorId)) {
            Book book = new Book(1, title, language, publicationDate, numberOfPages, authorId);
            bookDAO.addBook(book);
        } else {
            System.out.println("The book with title \"" + title + "\" and specified details already exists.");
        }

        List<Book> books = bookDAO.getAllBooks();

        System.out.println("List of books:");
        for (Book b : books) {
            System.out.println("ID: " + b.getId());
            System.out.println("Title: " + b.getTitle());
            System.out.println("Language: " + b.getLanguage());
            System.out.println("Publication Date: " + b.getPublicationDate());
            System.out.println("Number of Pages: " + b.getNumberOfPages());
            System.out.println("Author ID: " + b.getAuthorId());
            System.out.println();
 }
}
}