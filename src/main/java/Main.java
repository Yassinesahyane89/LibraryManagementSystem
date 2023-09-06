import models.Book;
import services.BookService;
import services.MemberService;
import java.util.Scanner;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService();

        while (true) {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a book
                    Book newBook = createBookFromUserInput(scanner);
                    bookService.addNewBook(newBook);
                    break;
                case 2:
                    // Exit the program
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Book createBookFromUserInput(Scanner scanner) {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.next();

        System.out.print("Enter Title: ");
        String title = scanner.next();

        System.out.print("Enter Author: ");
        String author = scanner.next();

        System.out.print("Enter Status: ");
        String status = scanner.next();

        System.out.print("Enter Number of Copies: ");
        int copies = scanner.nextInt();

        System.out.print("Enter Number of Borrowed Copies: ");
        int borrowedCopies = scanner.nextInt();

        System.out.print("Enter Number of lost Copies: ");
        int lostCopies = scanner.nextInt();

        return new Book(isbn, title, author, status, copies, borrowedCopies, lostCopies);
    }
}
