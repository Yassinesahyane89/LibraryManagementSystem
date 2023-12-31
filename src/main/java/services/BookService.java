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
        }
    }
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return books;
        }

        try {
            String sql = "SELECT * FROM books";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String status = resultSet.getString("status");
                int copies = resultSet.getInt("copies");
                int borrowedCopies = resultSet.getInt("borrowedCopies");
                int lostCopies = resultSet.getInt("lostCopies");

                books.add(new Book(isbn, title, author, status, copies, borrowedCopies, lostCopies));
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return books;
    }
    public List<Book> viewAvailableBooks() {
        Connection connection = DatabaseConnection.getConnection();
        List<Book> availableBooks = new ArrayList<>();

        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return availableBooks;
        }

        try {
            String sql = "SELECT * FROM books WHERE status = 'Available'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String status = resultSet.getString("status");
                int copies = resultSet.getInt("copies");
                int borrowedCopies = resultSet.getInt("borrowedCopies");
                int lostCopies = resultSet.getInt("lostCopies");

                availableBooks.add(new Book(isbn, title, author, status, copies, borrowedCopies, lostCopies));
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return availableBooks;
    }
    public List<Book> searchBooksByTitle(String title) {
        List<Book> matchingBooks = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return matchingBooks;
        }
        try {
            String sql = "SELECT * FROM books WHERE title LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String bookTitle = resultSet.getString("title");
                String author = resultSet.getString("author");
                String status = resultSet.getString("status");
                int copies = resultSet.getInt("copies");
                int borrowedCopies = resultSet.getInt("borrowedCopies");
                int lostCopies = resultSet.getInt("lostCopies");

                matchingBooks.add(new Book(isbn, bookTitle, author, status, copies, borrowedCopies, lostCopies));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return matchingBooks;
    }
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> matchingBooks = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return matchingBooks;
        }

        try {
            String sql = "SELECT * FROM books WHERE author LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + author + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String bookTitle = resultSet.getString("title");
                String bookAuthor = resultSet.getString("author");
                String status = resultSet.getString("status");
                int copies = resultSet.getInt("copies");
                int borrowedCopies = resultSet.getInt("borrowedCopies");
                int lostCopies = resultSet.getInt("lostCopies");

                matchingBooks.add(new Book(isbn, bookTitle, bookAuthor, status, copies, borrowedCopies, lostCopies));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return matchingBooks;
    }
    public boolean borrowBook(String isbn, String memberNumber) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return false;
        }

        MemberService memberService = new MemberService();

        try {
            // Check if the book with the provided ISBN exists and is available
            Book book = findBookByIsbn(isbn);

            if (book == null) {
                System.out.println("Book with ISBN " + isbn + " not found.");
                return false;
            }  else if (book.getCopies() <= 0) {
                System.out.println("No available copies of the book with ISBN " + isbn + ".");
                return false;
            }

            //check if the borrower
            boolean borrowerExists = memberService.findMemberNumber(memberNumber);

            if (!borrowerExists){
                System.out.println("Borrower with memberNumber " + memberNumber + " not found.");
                return false;
            }

            if (isBookAlreadyBorrowedByUser(isbn, memberNumber)) {
                System.out.println("The user has already borrowed this book.");
                return false;
            }

            // Update the book's status to "Borrowed" and decrement available copies
            String updateSql = "UPDATE books SET copies = copies - 1, borrowedCopies = borrowedCopies + 1 WHERE isbn = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setString(1, isbn);
            updateStatement.executeUpdate();

            // Record borrower information
            String insertSql = "INSERT INTO loan_records (Book_ISBN, Borrower_MemberNumber, loanDate) VALUES (?, ?, NOW())";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, isbn);
            insertStatement.setString(2, memberNumber);
            insertStatement.executeUpdate();

            System.out.println("Book with ISBN " + isbn + " has been successfully borrowed.");
            return true;
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
    // Check if the user already has an active loan for the same book
    private boolean isBookAlreadyBorrowedByUser(String isbn, String memberNumber) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String query = "SELECT * FROM loan_records WHERE Book_ISBN = ? AND Borrower_MemberNumber = ? AND returnDate IS NULL";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, isbn);
        statement.setString(2, memberNumber);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }
    public boolean returnBook(String isbn, String memberNumber) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return false;
        }

        MemberService memberService = new MemberService();
        try {
            // Check if the book with the provided ISBN exists and is borrowed
            Book book = findBookByIsbn(isbn);

            if (book == null) {
                System.out.println("Book with ISBN " + isbn + " not found.");
                return false;
            } else if (book.getCopies() <= 0) {
                System.out.println("Book with ISBN " + isbn + " is not currently borrowed.");
                return false;
            }

            //!book.getStatus().equalsIgnoreCase("Borrowed")

            //check if the borrower
            boolean borrower = memberService.findMemberNumber(memberNumber);

            if (!borrower){
                System.out.println("Borrower with memberNumber " + memberNumber + " not found.");
                return false;
            }

            // Update the book's status to "Available" and increment available copies
            String updateSql = "UPDATE books SET copies = copies + 1, borrowedCopies = borrowedCopies - 1 WHERE isbn = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setString(1, isbn);
            updateStatement.executeUpdate();

            // Record the return date in loan_records
            // Update the loan record to set the return date
            String updateLoanRecordSql = "UPDATE loan_records SET returnDate = NOW() WHERE Book_ISBN = ? AND Borrower_MemberNumber = ?";
            PreparedStatement updateLoanRecordStatement = connection.prepareStatement(updateLoanRecordSql);
            updateLoanRecordStatement.setString(1, isbn);
            updateLoanRecordStatement.setString(2, memberNumber);
            updateLoanRecordStatement.executeUpdate();

            System.out.println("Book with ISBN " + isbn + " has been successfully returned.");
            return true;
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
}