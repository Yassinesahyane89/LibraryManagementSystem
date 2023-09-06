package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableBook {
    public static void main(String[] args) {
        // Establish a database connection
        Connection connection = DatabaseConnection.getConnection();

        // SQL statement to create a "books" table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS books ("
                + "isbn VARCHAR(13) PRIMARY KEY,"
                + "title VARCHAR(255),"
                + "author VARCHAR(255),"
                + "status VARCHAR(50),"
                + "copies INT,"
                + "borrowedCopies INT,"
                + "lostCopies INT"
                + ")";

        try {
            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the SQL statement to create the table
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'books' created successfully");
        } catch (SQLException e) {
            System.err.println("Error creating 'books' table: " + e.getMessage());
        }
    }
}
