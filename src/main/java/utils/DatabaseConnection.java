package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/librarymanagementsystem";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    public static Connection getConnection() {

        if (connection == null) {
            try {
                // Create a connection to the database
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database");
            } catch (SQLException e) {
                System.err.println("Error connecting to the database: " + e.getMessage());
            }
        }
        return connection;
    }
}