package models;

import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String status;
    private int copies;
    private int borrowedCopies;
    private int lostCopies;

    private List<LoanRecord> loanRecords;

    public Book(String isbn, String title, String author, String status, int copies, int borrowedCopies, int lostCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = status;
        this.copies = copies;
        this.borrowedCopies = borrowedCopies;
        this.lostCopies = lostCopies;
    }

    // Getter and Setter methods for each attribute
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getBorrowedCopies() {
        return borrowedCopies;
    }

    public void setBorrowedCopies(int borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }

    public int getLostCopies() {
        return lostCopies;
    }

    public void setLostCopies(int lostCopies) {
        this.lostCopies = lostCopies;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + "\n"
                + "Title: " + title + "\n"
                + "Author: " + author + "\n"
                + "Status: " + status + "\n"
                + "Number of Copies: " + copies + "\n"
                + "Number of Borrowed Copies: " + borrowedCopies + "\n"
                + "Number of lost Copies: " + lostCopies;
    }

    // Methode to get a list of loan records for this book
    public List<Borrower> getLoanRecords() {
        return null;
    }

    // Methode borrow a book
    public boolean borrowBook(Book book) {
        return false;
    }

    // Method to return a book
    public boolean returnBook(Book book) {
        return false;
    }

    // Method to add a copy of the book
    public void addCopy() {
    }

    // Method to remove a copy of the book
    public void removeCopy() {
    }

    // Method to update information about the book's copies
    public void updateCopy() {
    }

    // Method to view information about the book's copies
    public Book viewCopy() {
        return this;
    }

    // Method to find a book by its ISBN
    public static Book findBook(String isbn) {
        return null;
    }
}
