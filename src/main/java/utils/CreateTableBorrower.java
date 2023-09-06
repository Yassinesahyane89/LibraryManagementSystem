package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateTableBorrower {
    public static void main(String[] args) {
        // Establish a database connection
        Connection connection = DatabaseConnection.getConnection();

        // SQL statement to create a "borrowers" table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS borrowers ("
                + "memberNumber VARCHAR(10) PRIMARY KEY,"
                + "name VARCHAR(255)"
                + ")";

        try {
            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the SQL statement to create the table
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'borrowers' created successfully");
        } catch (SQLException e) {
            System.err.println("Error creating 'borrowers' table: " + e.getMessage());
        }
    }
}
