package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final Connection connection;

    public BookDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addBook(Book book) {
        if (!exists(book.getTitle(), book.getLanguage(), book.getPublicationDate(), book.getNumberOfPages(), book.getAuthorId())) {
            String sql = "INSERT INTO books(title, language, publication_date, number_of_pages, author_id) VALUES(?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getLanguage());
                pstmt.setDate(3, java.sql.Date.valueOf(book.getPublicationDate()));
                pstmt.setInt(4, book.getNumberOfPages());
                pstmt.setInt(5, book.getAuthorId());

                pstmt.executeUpdate();
                System.out.println("Book \"" + book.getTitle() + "\" has been successfully added!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The book \"" + book.getTitle() + "\" already exists in the database.");
        }
    }

    public void importBooksFromCSV(String filePath) {
        String line = "";
        String csvSplitBy = ",";
        boolean isFirstLine = true;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy"); // Custom date format

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                //skip the first line (column headers)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(csvSplitBy);

                //necessary information for creating a Book object from the CSV file
                int id = Integer.parseInt(data[0]);
                String title = data[1];
                String language = data[6];
                int numPages = Integer.parseInt(data[7]);
                LocalDate publicationDate = LocalDate.parse(data[10], dateFormatter);
                int authorId = 2; //a default author ID

                Book book = new Book(id, title, language, publicationDate, numPages, authorId);

                addBook(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooksByAuthor(String authorName) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.language, b.publication_date, b.number_of_pages, b.author_id " +
                "FROM books b " +
                "JOIN authors a ON b.author_id = a.author_id " +
                "WHERE a.name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, authorName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("language"),
                            rs.getDate("publication_date").toLocalDate(),
                            rs.getInt("number_of_pages"),
                            rs.getInt("author_id")
                    );
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public boolean exists(String title, String language, LocalDate publicationDate, int numberOfPages, int authorId) {
        String query = "SELECT COUNT(*) FROM books WHERE title = ? AND language = ? AND publication_date = ? AND number_of_pages = ? AND author_id = ?";
        boolean exists = false;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, language);
            preparedStatement.setDate(3, Date.valueOf(publicationDate));
            preparedStatement.setInt(4, numberOfPages);
            preparedStatement.setInt(5, authorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    exists = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return exists;
    }


    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("language"),
                        rs.getDate("publication_date").toLocalDate(),
                        rs.getInt("number_of_pages"),
                        rs.getInt("author_id")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
}
}