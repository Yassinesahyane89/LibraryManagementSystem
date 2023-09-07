package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateTableBorrower {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();

        String createTableSQL = "CREATE TABLE IF NOT EXISTS borrowers ("
                + "memberNumber VARCHAR(10) PRIMARY KEY,"
                + "name VARCHAR(255)"
                + ")";

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'borrowers' created successfully");
        } catch (SQLException e) {
            System.err.println("Error creating 'borrowers' table: " + e.getMessage());
        }
    }
}
