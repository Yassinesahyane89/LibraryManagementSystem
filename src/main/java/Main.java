import models.Book;
import services.BookService;
import services.StatisticsService;
import java.util.Scanner;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService();

        while (true) {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Update a Book");
            System.out.println("3. Remove a Book");
            System.out.println("4. View all Books");
            System.out.println("5. View all Books available");
            System.out.println("6. Search by Title");
            System.out.println("7. Search by Author");
            System.out.println("8. Borrow a Book");
            System.out.println("9. retunr a Book");
            System.out.println("10. library statistics");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a book
                    Book newBook = createBookFromUserInput(scanner);
                    bookService.addNewBook(newBook);
                    break;
                case 2:
                    // Update a book
                    System.out.print("Enter ISBN of the book to update: ");
                    String isbnToUpdate = scanner.next();
                    Book bookToUpdate = bookService.findBookByIsbn(isbnToUpdate);
                    if (bookToUpdate != null) {
                        System.out.println("Previous book data:");
                        System.out.println(bookToUpdate);

                        System.out.println("Enter new data:");
                        Book updatedBook = updateBookFromUserInput(scanner,bookToUpdate.getIsbn());
                        bookService.updateBook(updatedBook);
                    } else {
                        System.out.println("Book with ISBN " + isbnToUpdate + " not found.");
                    }
                    break;
                case 3:
                    // Remove a book
                    System.out.print("Enter ISBN of the book to remove: ");
                    String isbnToRemove = scanner.next();
                    bookService.removeBook(isbnToRemove);
                    break;
                case 4:
                    // View all books
                    List<Book> allBooks = bookService.getAllBooks();
                    if (!allBooks.isEmpty()) {
                        System.out.println("List of All Books:");
                        int i =1;
                        for (Book book : allBooks) {
                            System.out.println("\nbook " + i + "\n");
                            System.out.println(book);
                            i++;
                        }
                    } else {
                        System.out.println("No books found in the database.");
                    }
                    break;
                case 5:
                    // view all books available
                    List<Book> allBooksAvailable = bookService.viewAvailableBooks();
                    if (!allBooksAvailable.isEmpty()) {
                        System.out.println("List of All Books Available:");
                        int i =1;
                        for (Book book : allBooksAvailable) {
                            System.out.println("\nbook " + i + "\n");
                            System.out.println(book);
                            i++;
                        }
                    } else {
                        System.out.println("No books found in the database.");
                    }
                    break;
                case 6:
                    //Search by title
                    scanner.nextLine();
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    List<Book> booksByTitle = bookService.searchBooksByTitle(searchTitle);

                    if (booksByTitle.isEmpty()) {
                        System.out.println("No books found with the specified title.");
                    } else {
                        System.out.println("Books with matching title:");
                        int i=1;
                        for (Book book : booksByTitle) {
                            System.out.println("\nbook " + i + "\n");
                            System.out.println(book);
                            i++;
                        }
                    }
                    break;
                case 7:
                    //Search by Author
                    scanner.nextLine();
                    System.out.print("Enter author to search: ");
                    String searchAuthor = scanner.nextLine();
                    List<Book> booksByAuthor = bookService.searchBooksByAuthor(searchAuthor);

                    if (booksByAuthor.isEmpty()) {
                        System.out.println("No books found by the specified author.");
                    } else {
                        System.out.println("Books by matching author:");
                        int i = 1;
                        for (Book book : booksByAuthor) {
                            System.out.println("\nbook " + i + "\n");
                            System.out.println(book);
                            i++;
                        }
                    }
                    break;
                case 8:
                    // Borrow a book
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String isbnToBorrow = scanner.next();

                    System.out.print("Enter Member Number: ");
                    String memberNumber = scanner.next();

                    boolean success = bookService.borrowBook(isbnToBorrow, memberNumber);

                    if (success) {
                        System.out.println("Book has been borrowed successfully.");
                    } else {
                        System.out.println("Failed to borrow the book.");
                    }
                    break;
                case 9:
                    // Return a book
                    System.out.print("Enter ISBN of the book to return: ");
                    String isbnToReturn = scanner.next();

                    System.out.print("Enter Member Number: ");
                    String memberNumberToReturn = scanner.next();

                    boolean returnSuccess = bookService.returnBook(isbnToReturn, memberNumberToReturn);

                    if (returnSuccess) {
                        System.out.println("Book has been successfully returned.");
                    } else {
                        System.out.println("Failed to return the book.");
                    }
                    break;
                case 10:
                    // Generate library statistics
                    StatisticsService statisticsService = new StatisticsService();
                    statisticsService.generateLibraryStatistics();
                    break;

                case 11:
                    // Exit the program
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Book createBookFromUserInput(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        /*int copies = readIntegerInput("Enter Number of Copies: ");
        int borrowedCopies = readIntegerInput("Enter Number of Borrowed Copies: ");
        int lostCopies = readIntegerInput("Enter Number of Lost Copies: ");*/

        System.out.print("Enter Number of Copies: ");
        int copies = scanner.nextInt();

        System.out.print("Enter Number of Borrowed Copies: ");
        int borrowedCopies = scanner.nextInt();

        System.out.print("Enter Number of lost Copies: ");
        int lostCopies = scanner.nextInt();

        return new Book(isbn, title, author, "available", copies, borrowedCopies, lostCopies);
    }

    private static Book updateBookFromUserInput(Scanner scanner,String ISBN) {
        scanner.nextLine();
        System.out.print("Enter ISBN: "+ISBN+"\n");
        String isbn = ISBN;

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Status: ");
        String status = scanner.nextLine();

        int copies = readIntegerInput("Enter Number of Copies: ");
        int borrowedCopies = readIntegerInput("Enter Number of Borrowed Copies: ");
        int lostCopies = readIntegerInput("Enter Number of Lost Copies: ");

        return new Book(isbn, title, author, status, copies, borrowedCopies, lostCopies);
    }

    private static int readIntegerInput(String message) {
        while (true) {
            System.out.print(message);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            try {
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
