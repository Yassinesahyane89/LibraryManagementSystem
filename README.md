# Library Management Console Application

![Library](https://user-images.githubusercontent.com/YourImageURLHere)

## Table of Contents
- [Introduction](#introduction)
- [Project Brief](#project-brief)
- [User Stories](#user-stories)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Library Management Console Application is a Java-based solution designed to address the challenges faced by the municipal library of Paris in manually managing books and lacking an efficient tracking system. This console application aims to automate book management, enhance search capabilities, facilitate borrowing and returning, and generate statistical reports. By doing so, it intends to improve the overall library experience for both users and librarians.

## Project Brief

### Context

The municipal library of Paris encounters several issues due to manual book management and the absence of an efficient tracking system. These issues include:

1. **Inefficient Management**: Librarians must perform all book management tasks manually, leading to errors and time loss. Maintaining an up-to-date database and tracking the status of each book (available or borrowed) is challenging.

2. **Search Difficulties**: Library users struggle to find the books they want to borrow. The absence of an efficient search system makes the process tedious and discouraging.

3. **Lack of Statistics**: There is no easy way to obtain statistics on available books, borrowed books, and lost books. This limits the library's ability to analyze and optimize its collections.

In summary, the need is to develop a Java-based console application for library management, providing automated book management, efficient search, borrowing and returning capabilities, and the generation of statistical reports. This application aims to resolve the issues of inefficient management and search difficulties faced by the library while enhancing user experience.

### Requirements

To address the mentioned problems, the library requires a console application for library management with the following features:

1. **Automated Book Management**: The application should enable librarians to automate book management by allowing them to add new books, update information on existing books, and remove books from the database.

2. **Efficient Search System**: The application should offer users a quick and efficient way to search for books by title or author, facilitating the discovery of available books in the library.

3. **Borrowing and Returning Management**: The application should allow librarians to record book borrowings and returns. It should also maintain a record of each book's status (available or borrowed).

4. **Statistical Report Generation**: The application should generate statistical reports on available books, borrowed books, and lost books. This will enable the library to analyze trends and make informed decisions to optimize its book collection.

## User Stories

### User Story 1: Adding a New Book
**As a user**, I can add a new book to the library by providing its title, author, and ISBN number. The system validates the data and adds the book with an "available" status.

### User Story 2: Displaying Available Books
**As a user**, I can view a list of available books in the library, including their title, author, and status (available or borrowed).

### User Story 3: Searching for a Book
**As a user**, I can search for a book by title or author to quickly find books of interest.

### User Story 4: Borrowing a Book
**As a user**, I can borrow a book by providing its ISBN number. The system checks availability, updates the status to "borrowed," and records borrower information.

### User Story 5: Returning a Book
**As a user**, I can return a borrowed book by providing its ISBN number. The system updates the status to "available" and removes borrower information.

### User Story 6: Displaying Borrowed Books
**As a user**, I can view a list of borrowed books, including borrower information and the date of borrowing.

### User Story 7: Deleting a Book
**As a user**, I can delete a book from the library by providing its ISBN number to remove obsolete or lost books.

### User Story 8: Modifying Book Information
**As a user**, I can edit the information of an existing book (title, author, etc.) by providing its ISBN number.

### User Story 9: Generating Statistical Reports
**As a user**, I can generate a report with statistics on available books, borrowed books, and lost books to track the library's status.

## Getting Started

To get started with the Library Management Console Application, follow these steps:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/library-management-console.git
````

2. Compile the Java code:

```bash
javac LibraryManagementApp.java
````

3. Run the application:

```bash
java LibraryManagementApp
````

## Usage

Once the application is running, follow the prompts and menu options to perform various tasks such as adding books, searching, borrowing, returning, displaying lists, deleting books, modifying book information, and generating reports.

## Contributing

If you'd like to contribute to this project, please follow these guidelines:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and test them thoroughly.
4. Commit your changes with clear and concise commit messages.
5. Push your changes to your forked repository.
6. Create a pull request to the main repository with a description of your changes.


## License

This project is licensed under the MIT License. See the LICENSE file for details.
