package services;

import models.Book;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    public boolean addNewBook(Book book) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return false;
        }

        try {
            String sql = "INSERT INTO books (isbn, title, author, status, copies, borrowedCopies) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getStatus());
            statement.setInt(5, book.getCopies());
            statement.setInt(6, book.getBorrowedCopies());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Book added successfully: " + book.getTitle());
                return true;
            } else {
                System.err.println("Error adding book: " + book.getTitle());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }
}