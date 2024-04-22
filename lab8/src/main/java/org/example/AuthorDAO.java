package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO() throws SQLException {
        connection = DatabaseManager.getConnection();
    }

    public void addAuthor(String authorName) throws SQLException {
        String sql = "INSERT INTO authors (author_id, name) VALUES (DEFAULT, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, authorName);
            statement.executeUpdate();
        }
    }

    public void close() throws SQLException {
        DatabaseManager.closeConnection();
    }
}
