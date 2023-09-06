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

    public boolean updateBook(Book book) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return false;
        }

        try {
            String sql = "UPDATE books SET title=?, author=?, status=?, copies=?, borrowedCopies=? WHERE isbn=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getStatus());
            statement.setInt(4, book.getCopies());
            statement.setInt(5, book.getBorrowedCopies());
            statement.setString(6, book.getIsbn());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully: " + book.getTitle());
                return true;
            } else {
                System.err.println("Error updating book: " + book.getTitle());
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
    public boolean removeBook(String isbn) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return false;
        }

        try {
            String sql = "DELETE FROM books WHERE isbn=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, isbn);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Book removed successfully");
                return true;
            } else {
                System.err.println("Error removing book with ISBN: " + isbn);
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

    public Book findBookByIsbn(String isbn) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return null;
        }

        try {
            String sql = "SELECT * FROM books WHERE isbn=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, isbn);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String status = resultSet.getString("status");
                int copies = resultSet.getInt("copies");
                int borrowedCopies = resultSet.getInt("borrowedCopies");
                int lostCopies = resultSet.getInt("lostCopies");

                return new Book(isbn, title, author, status, copies, borrowedCopies,lostCopies);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }
}