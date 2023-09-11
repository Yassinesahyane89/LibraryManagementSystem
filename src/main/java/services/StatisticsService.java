package services;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsService {
    public void generateLibraryStatistics() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return;
        }

        try {
            // Count available books
            int availableBooksCount = countTotalCopies(connection, "copies");

            // Count borrowed books
            int borrowedBooksCount = countTotalCopies(connection, "borrowedCopies");

            // Count lost books
            int lostBooksCount = countTotalCopies(connection, "lostCopies");

            // Print the statistics
            System.out.println("Library Statistics:");
            System.out.println("Available Books: " + availableBooksCount);
            System.out.println("Borrowed Books: " + borrowedBooksCount);
            System.out.println("Lost Books: " + lostBooksCount);
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
    private int countTotalCopies(Connection connection, String column) throws SQLException {
        String sql = "SELECT SUM(" + column + ") FROM Books";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
    private int countBooksByStatus(Connection connection, String status) throws SQLException {
        String sql = "SELECT COUNT(*) FROM books WHERE status = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, status);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
