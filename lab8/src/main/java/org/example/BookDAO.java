package org.example;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO {
    private Connection connection;

    public BookDAO() throws SQLException {
        connection = DatabaseManager.getConnection();
    }

    public void addBook(String title, String language, Date publicationDate, int numPages, int authorId) throws SQLException {
        String sql = "INSERT INTO books (title, language, publication_date, num_pages, author_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, language);
            statement.setDate(3, publicationDate);
            statement.setInt(4, numPages);
            statement.setInt(5, authorId);
            statement.executeUpdate();
        }
    }

    public void close() throws SQLException {
        DatabaseManager.closeConnection();
    }
}
