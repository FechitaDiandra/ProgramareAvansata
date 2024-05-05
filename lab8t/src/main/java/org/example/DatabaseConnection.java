package org.example;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/book";
    private static final String USER = "root";
    private static final String PASSWORD = "STUDENT";
    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
    }

    private DatabaseConnection() {}

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
