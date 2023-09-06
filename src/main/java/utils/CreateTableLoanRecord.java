package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableLoanRecord {
    public static void main(String[] args) {
        // Establish a database connection
        Connection connection = DatabaseConnection.getConnection();

        // SQL statement to create a "loan_records" table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS loan_records ("
                + "loanId INT AUTO_INCREMENT PRIMARY KEY,"
                + "Book_ISBN VARCHAR(13),"
                + "Borrower_MemberNumber VARCHAR(10),"
                + "loanDate DATE,"
                + "returnDate DATE,"
                + "FOREIGN KEY (Book_ISBN) REFERENCES books(isbn),"
                + "FOREIGN KEY (Borrower_MemberNumber) REFERENCES borrowers(memberNumber)"
                + ")";

        try {
            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the SQL statement to create the table
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'loan_records' created successfully");
        } catch (SQLException e) {
            System.err.println("Error creating 'loan_records' table: " + e.getMessage());
        }
    }
}
