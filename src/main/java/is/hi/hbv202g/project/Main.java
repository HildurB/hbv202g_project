package is.hi.hbv202g.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the library system.
 */
public class Main {
  /**
   * Main method to run the library system.
   *
   * Implements a textual user interface for the library system.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    LibrarySystem myLibrarySystem = new LibrarySystem();

    // Adding books and users to the library system
    addInitialData(myLibrarySystem);

    Scanner scanner = new Scanner(System.in);
    User userLoggedIn = null;
    boolean isFacultyMember = false;

    // Logging in user
    userLoggedIn = logInUser(scanner, myLibrarySystem);

    while (true) {
      displayMenu();
      String choice = scanner.nextLine();
      int choiceInt = 0;

      try {
        choiceInt = Integer.parseInt(choice);
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice. Please enter a number between 1 and 9.");
        continue;
      }

      System.out.println();

      switch (choiceInt) {
        case 1:
          addBook(scanner, myLibrarySystem);
          break;
        case 2:
          addUser(scanner, myLibrarySystem);
          break;
        case 3:
          borrowBook(scanner, myLibrarySystem, userLoggedIn);
          break;
        case 4:
          returnBook(scanner, myLibrarySystem, userLoggedIn);
          break;
        case 5:
          extendLending(scanner, myLibrarySystem, userLoggedIn, isFacultyMember);
          break;
        case 6:
          listBooks(myLibrarySystem);
          break;
        case 7:
          listLendings(myLibrarySystem, userLoggedIn);
          break;
        case 8:
          listUsers(myLibrarySystem);
          break;
        case 9:
          System.out.println("Exiting...");
          scanner.close();
          System.exit(0);
          break;
        default:
          System.out.println("Invalid choice. Please enter a number between 1 and 9.");
      }
      System.out.println("---------------------------------");
    }

  }

  /**
   * Helper method to initialize the library system with some data.
   *
   * @param librarySystem a LibrarySystem object.
   */
  private static void addInitialData(LibrarySystem librarySystem) {
    try {
      librarySystem.addBook("The Hobbit", "J.R.R.Tolkien");
      librarySystem.addBook("The Lord of the Rings",
          List.of(new Author("J.R.R. Tolkien")));
      librarySystem.addBook("The Silmarillion", List.of(new Author("J.R.R. Tolkien")));
      librarySystem.addBook("The Children of Húrin",
          List.of(new Author("J.R.R. Tolkien")));
      librarySystem.addBook("The Fall of Gondolin",
          List.of(new Author("J.R.R. Tolkien")));

      librarySystem.addStudent("Frodo Baggins", true);
      librarySystem.addStudent("Samwise Gamgee", true);
      librarySystem.addStudent("Meriadoc Brandybuck", true);
      librarySystem.addStudent("Peregrin Took", false);

      librarySystem.addFaculty("Gandalf", "Wizard");
      librarySystem.addFaculty("Aragorn", "King");

      librarySystem.borrowBook(
          librarySystem.findUserByName("Frodo Baggins"),
          librarySystem.findBookByTitle("The Hobbit"));

      librarySystem.borrowBook(
          librarySystem.findUserByName("Gandalf"),
          librarySystem.findBookByTitle("The Lord of the Rings"));

      librarySystem.extendLending(
          (FacultyMember) librarySystem.findUserByName("Gandalf"),
          librarySystem.findBookByTitle("The Lord of the Rings"),
          java.time.LocalDate.now().plusDays(30));

      librarySystem.returnBook(
          librarySystem.findUserByName("Frodo Baggins"),
          librarySystem.findBookByTitle("The Hobbit"));

    } catch (UserOrBookDoesNotExistException | EmptyAuthorListException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Helper method to display the main menu.
   */
  private static void displayMenu() {
    System.out.println("\nSelect an action:");
    System.out.println("1. Add a book");
    System.out.println("2. Add a user");
    System.out.println("3. Borrow a book");
    System.out.println("4. Return a book");
    System.out.println("5. Extend lending");
    System.out.println("6. See list of books");
    System.out.println("7. See list of lendings");
    System.out.println("8. See list of users");
    System.out.println("9. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * Helper method to log in a user.
   *
   * @param scanner       Scanner object for user input.
   * @param librarySystem The library system.
   * @return The logged-in user.
   */
  private static User logInUser(Scanner scanner, LibrarySystem librarySystem) {
    User currentUser = null;
    System.out.print("Enter user name: ");
    String userName = scanner.nextLine();
    System.out.print("Are you a faculty member? (true/false): ");

    String isFaucultyString = scanner.nextLine();
    boolean isFacultyMember = false;

    try {
      isFacultyMember = Boolean.parseBoolean(isFaucultyString);
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Assuming you are not a faculty member.");
    }

    if (isFacultyMember) {
      System.out.print("Enter department: ");
      String department = scanner.nextLine();
      librarySystem.addFaculty(userName, department);
      System.out.println("Faculty member added successfully!");
    } else {
      librarySystem.addStudent(userName, false);
      System.out.println("Student added successfully!");
    }
    try {
      currentUser = librarySystem.findUserByName(userName);
    } catch (UserOrBookDoesNotExistException e) {
      System.out.println("User does not exist: " + e.getMessage());
    }
    return currentUser;
  }

  /**
   * Helper method to add a book to the library system.
   *
   * @param scanner       Scanner object for user input.
   * @param librarySystem The library system.
   */
  private static void addBook(Scanner scanner, LibrarySystem librarySystem) {
    System.out.print("Enter book title: ");
    String title = scanner.nextLine();
    System.out.print("Enter author(s) separated by ';' : ");
    String authorsInput = scanner.nextLine();
    String[] authors = authorsInput.split(";");
    List<Author> authorList = new ArrayList<>();
    for (String author : authors) {
      authorList.add(new Author(author.trim()));
    }
    try {
      if (authorList.size() == 1) {
        librarySystem.addBook(title, authors[0].trim());
      } else {
        librarySystem.addBook(title, authorList);
      }
      System.out.println("Book added successfully!");
    } catch (EmptyAuthorListException e) {
      System.out.println("Could not add book: " + e.getMessage());
    }
  }

  /**
   * Helper method to add a user to the library system.
   *
   * @param scanner       Scanner object for user input.
   * @param librarySystem The library system.
   */
  private static void addUser(Scanner scanner, LibrarySystem librarySystem) {
    System.out.print("Enter user name: ");
    String userName = scanner.nextLine();
    System.out.print("Is the user a faculty member? (true/false): ");
    boolean isFaculty = scanner.nextBoolean();
    scanner.nextLine(); // Consume newline
    if (isFaculty) {
      System.out.print("Enter department: ");
      String department = scanner.nextLine();
      librarySystem.addFaculty(userName, department);
      System.out.println("Faculty member added successfully!");
    } else {
      librarySystem.addStudent(userName, false);
      System.out.println("Student added successfully!");
    }
  }

  /**
   * Helper method to borrow a book from the library system.
   *
   * @param scanner       Scanner object for user input.
   * @param librarySystem The library system.
   * @param userLoggedIn  The user who is logged in.
   */
  private static void borrowBook(Scanner scanner, LibrarySystem librarySystem, User userLoggedIn) {
    System.out.print("Which book do you want to borrow? ");
    listBooks(librarySystem);
    System.out.print("Enter your choice: ");
    int bookIndex = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    if (bookIndex < 1 || bookIndex > librarySystem.getAllBooks().size()) {
      System.out.println("Invalid book number. Please enter a valid number.");
      return;
    }
    Book selectedBook = librarySystem.getAllBooks().get(bookIndex - 1);
    try {
      librarySystem.borrowBook(userLoggedIn, selectedBook);
      System.out.println("Book borrowed successfully!");
    } catch (UserOrBookDoesNotExistException e) {
      System.out.println("Could not borrow book: " + e.getMessage());
    }
  }

  /**
   * Helper method to return a book to the library system.
   *
   * @param scanner       Scanner object for user input.
   * @param librarySystem The library system.
   * @param userLoggedIn  The user who is logged in.
   */
  private static void returnBook(Scanner scanner, LibrarySystem librarySystem, User userLoggedIn) {
    System.out.print("Which book do you want to return?");
    listLendings(librarySystem, userLoggedIn);
    System.out.print("Enter your choice: ");
    int lendingIndex = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    if (lendingIndex < 1 || lendingIndex > librarySystem.getAllBooks().size()) {
      System.out.println("Invalid book number. Please enter a valid number.");
      return;
    }
    Book selectedLending = librarySystem.getAllBooks().get(lendingIndex - 1);
    try {
      librarySystem.returnBook(userLoggedIn, selectedLending);
      System.out.println("Book returned successfully!");
    } catch (UserOrBookDoesNotExistException e) {
      System.out.println("Could not return book: " + e.getMessage());
    }
  }

  /**
   * Helper method to extend lending of a book by a faculty member.
   *
   * @param scanner         Scanner object for user input.
   * @param librarySystem   The library system.
   * @param userLoggedIn    The user who is logged in.
   * @param isFacultyMember Indicates whether the logged-in user is a faculty
   *                        member.
   */
  private static void extendLending(Scanner scanner, LibrarySystem librarySystem, User userLoggedIn,
      boolean isFacultyMember) {
    if (isFacultyMember) {
      System.out.print("Which book do you want to extend the lending on?");
      listLendings(librarySystem, userLoggedIn);
      System.out.print("Enter your choice: ");
      int extendLendingIndex = scanner.nextInt();
      scanner.nextLine(); // Consume newline
      if (extendLendingIndex < 1 || extendLendingIndex > librarySystem.getAllBooks().size()) {
        System.out.println("Invalid book number. Please enter a valid number.");
        return;
      }
      Book selectedExtendedLending = librarySystem.getAllBooks().get(extendLendingIndex - 1);
      try {
        librarySystem.extendLending((FacultyMember) userLoggedIn, selectedExtendedLending,
            java.time.LocalDate.now().plusDays(30));
      } catch (UserOrBookDoesNotExistException e) {
        System.out.println("Could not extend lending: " + e.getMessage());
        return;
      }
      System.out.println("Book returned successfully!");
    } else {
      System.out.println("Only faculty members can extend lending");
    }
  }

  /**
   * Helper method to list all books in the library system.
   *
   * @param librarySystem The library system.
   */
  private static void listBooks(LibrarySystem librarySystem) {
    List<Book> books = librarySystem.getAllBooks();
    System.out.println("Books in the library:");
    for (int i = 0; i < books.size(); i++) {
      Book book = books.get(i);
      System.out.print((i + 1) + ". " + book.getTitle() + " by ");
      List<Author> authors = book.getAuthors();
      for (int j = 0; j < authors.size(); j++) {
        if (j > 0) {
          System.out.print(", ");
        }
        System.out.print(authors.get(j).getName());
      }
      System.out.println();
    }
  }

  /**
   * Helper method to list all users in the library system.
   *
   * @param librarySystem The library system.
   */
  private static void listUsers(LibrarySystem librarySystem) {
    List<User> users = librarySystem.getAllUsers();
    System.out.println("Users registered:");
    for (int i = 0; i < users.size(); i++) {
      System.out.println(users.get(i).getName());
    }
  }

  /**
   * Helper method to list all lendings of a specific user.
   *
   * @param librarySystem The library system.
   * @param userLoggedIn  The user who is logged in.
   */
  private static void listLendings(LibrarySystem librarySystem, User userLoggedIn) {
    List<Lending> lendings = librarySystem.getAllLendingsForUser(userLoggedIn);
    System.out.println("Books that you have lended:");
    for (int i = 0; i < lendings.size(); i++) {
      Lending lending = lendings.get(i);
      Book book = lending.getBook();
      System.out.print((i + 1) + ". " + book.getTitle() + " by ");
      List<Author> authors = book.getAuthors();
      for (int j = 0; j < authors.size(); j++) {
        if (j > 0) {
          System.out.print(", ");
        }
        System.out.print(authors.get(j).getName());
      }
      System.out.println();
    }
  }
}
