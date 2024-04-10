package is.hi.hbv202g.project;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.stream.Collectors;

public class TextUI {
  private LibrarySystem librarySystem;
  private Scanner scanner;
  private User userLoggedIn;
  private boolean isFacultyMember = false;
  private String messageString = null;

  public TextUI() {
    if (this.librarySystem == null) {
      this.librarySystem = LibrarySystem.getInstance();
      this.scanner = new Scanner(System.in);
      addInitialData();
    }
  }

  /**
   * Interface to provide the adapter pattern for the menu actions.
   */
  interface menuAction {
    void launch();
  }

  private menuAction[] userActions = new menuAction[] {
      this::addBook,
      this::borrowBook,
      this::returnBook,
      this::listBooks,
      this::listLendings,
      this::exit
  };

  private menuAction[] facultyActions = new menuAction[] {
      this::addBook,
      this::borrowBook,
      this::returnBook,
      this::listBooks,
      this::listLendings,
      this::listUsers,
      this::extendLending,
      this::addUser,
      this::exit
  };

  /**
   * Menu for the text user interface.
   */
  public void menu() {
    clearScreen();

    String[] allActions = {
        "Add a book",
        "Borrow a book",
        "Return a book",
        "See list of all books",
        "See list of your lendings",
        "See list of users",
        "Extend lending",
        "Add a user" };

    List<String> actions = new ArrayList<>(List.of(allActions));

    if (!isFacultyMember) {
      actions = actions.subList(0, 5);
    }

    actions.add("Exit");

    for (int i = 0; i < actions.size(); i++) {
      System.out.printf("#%d: %s\n", i + 1, actions.get(i));
    }
    String indexString = prompt("Enter the number of the action you want to perform: ");
    int index;

    try {
      index = Integer.parseInt(indexString);
    } catch (Exception e) {
      System.out.println("Invalid action number. Please enter a valid number.");
      return;
    }

    if (index < 1 || index > actions.size() + 1) {
      System.out.println("Invalid action number. Please enter a valid number.");
      return;
    }

    if (isFacultyMember) {
      facultyActions[index - 1].launch();
    } else {
      userActions[index - 1].launch();
    }

  }

  /**
   * Helper method to log in a user.
   */
  public void login() {

    // if user has already logged in we skip the logic
    User currentUser = userLoggedIn;
    String tempMessage = "";

    while (currentUser == null) {
      clearScreen();
      String username = prompt("Enter username: ");
      if (username.trim().length() == 0) {
        messageString = "User name cannot be empty.";
        continue;
      }

      String isFaucultyString = prompt("Are you a faculty member? (true/false): ");
      isFacultyMember = Boolean.parseBoolean(isFaucultyString);
      if (!isFacultyMember && !isFaucultyString.trim().toLowerCase().equals("false")) {
        tempMessage = "Invalid input. Assuming you are not a faculty member.\n";
      }

      if (isFacultyMember) {
        String department = prompt("Enter department: ");
        if (department.trim().length() == 0) {
          tempMessage = "Department cannot be empty, defaulting to department 'School'.\n";
          department = "School";
        }
        librarySystem.addFaculty(username, department);
        messageString = tempMessage + "Faculty member added successfully!";
      } else {
        librarySystem.addStudent(username, false);
        messageString = tempMessage + "Student added successfully!";
      }

      try {
        currentUser = librarySystem.findUserByName(username);
      } catch (UserOrBookDoesNotExistException e) {
        messageString = "User does not exist: " + e.getMessage();
      }
    }

    this.userLoggedIn = currentUser;
  }

  /**
   * Helper method to add a book to the library system.
   */
  public void addBook() {
    clearScreen();
    String title = prompt("Enter book title, must be atleast one letter: ");
    if (title.trim().length() == 0) {
      messageString = "Title cannot be empty.";
      return;
    }

    String authorString = prompt("Enter name of author(s), if multiple seperated by ';': ");
    if (authorString.trim().length() == 0) {
      messageString = "Author cannot be empty.";
      return;
    }

    String[] authors = authorString.split(";");
    List<Author> authorList = List.of(authors).stream().map(Author::new).toList();

    try {
      librarySystem.addBook(title, authorList);
      messageString = "Book added successfully!";
    } catch (EmptyAuthorListException e) {
      System.out.println("Error: " + e.getMessage());
      messageString = "Error: " + e.getMessage();

    }
  }

  /**
   * Helper method to add a user to the library system.
   */
  public void addUser() {
    if (!isFacultyMember)
      return;

    clearScreen();
    String username = prompt("Enter name of user: ");
    if (username.trim().length() == 0) {
      messageString = "Username cannot be empty.";
      return;
    }

    String tempMessage = "";
    String isFaucultyString = prompt("Is the user a faculty member? (true/false): ");
    boolean isFacultyMember = Boolean.parseBoolean(isFaucultyString);
    if (!isFacultyMember && !isFaucultyString.trim().toLowerCase().equals("false")) {
      tempMessage = "Invalid input. Assuming user is not a faculty member.\n";
    }

    if (isFacultyMember) {
      String department = prompt("Enter department: ");
      if (department.trim().length() == 0) {
        tempMessage = "Department cannot be empty, defaulting to department 'School'.\n";
        department = "School";
      }
      librarySystem.addFaculty(username, department);
      messageString = tempMessage + "Faculty member added successfully!";
    } else {
      librarySystem.addStudent(username, false);
      messageString = tempMessage + "Student added successfully!";
    }
  }

  /**
   * Helper method to borrow a book from the library.
   */
  public void borrowBook() {
    clearScreen();
    List<Book> books = librarySystem.getAllBooks();
    listBooks("Available books: ", books);
    String bookIndexString = prompt("Enter the number of the book you want to borrow: ");
    int bookIndex;
    try {
      bookIndex = Integer.parseInt(bookIndexString);
    } catch (Exception e) {
      messageString = "Invalid book number. Please enter a valid number.";
      return;
    }

    if (bookIndex < 1 || bookIndex > books.size()) {
      messageString = "Invalid book number. Please enter a valid number.";
      return;
    }

    Book selectedBook = books.get(bookIndex - 1);
    try {
      librarySystem.borrowBook(userLoggedIn, selectedBook);
      messageString = "Book borrowed successfully!";
    } catch (UserOrBookDoesNotExistException e) {
      messageString = "Could not borrow book: " + e.getMessage();
    }
  }

  /**
   * Helper method to return a book to the library system.
   */
  public void returnBook() {
    clearScreen();
    List<Book> books = librarySystem.getAllLendingsForUser(userLoggedIn).stream().map(Lending::getBook).toList();
    listBooks("Books you have borrowed: ", books);

    String bookIndexString = prompt("Enter the number of the book you want to return: ");
    int bookIndex;

    try {
      bookIndex = Integer.parseInt(bookIndexString);
    } catch (Exception e) {
      messageString = "Invalid book number. Please enter a valid number.";
      return;
    }

    if (bookIndex < 1 || bookIndex > books.size()) {
      messageString = "Invalid book number. Please enter a valid number.";
      return;
    }

    Book selectedBook = books.get(bookIndex - 1);
    try {
      librarySystem.returnBook(userLoggedIn, selectedBook);
      messageString = "Book returned successfully!";
    } catch (UserOrBookDoesNotExistException e) {
      messageString = "Could not return book: " + e.getMessage();
    }
  }

  /**
   * Helper method to extend lending of a book by a faculty member.
   */
  public void extendLending() {
    if (!isFacultyMember) {
      messageString = "You need to be a faculty member to extend a lending.";
      return;
    }

    clearScreen();
    List<User> users = listUsersWithLending();
    if (users == null) {
      return;
    }

    String userIndexString = prompt("Enter the number for the user that needs a lending extended: ");
    int userIndex;
    try {
      userIndex = Integer.parseInt(userIndexString);
    } catch (Exception e) {
      messageString = "Invalid user number. Please enter a valid number.";
      return;
    }

    User user = users.get(userIndex - 1);
    List<Lending> lendings = librarySystem.getAllLendingsForUser(user);
    List<Book> books = lendings.stream().map(Lending::getBook).toList();
    listBooks("Books " + user.getName() + " has borrowed: ", books);

    String bookIndexString = prompt("Enter the number of the book you want to extend the lending on: ");
    int bookIndex;
    try {
      bookIndex = Integer.parseInt(bookIndexString);
    } catch (Exception e) {
      messageString = "Invalid book number. Please enter a valid number.";
      return;
    }

    if (bookIndex < 1 || bookIndex > books.size()) {
      messageString = "Invalid book number. Please enter a valid number.";
      return;
    }

    Lending selectedLending = lendings.get(bookIndex - 1);
    try {
      librarySystem.extendLending((FacultyMember) user, selectedLending.getBook(),
          java.time.LocalDate.now().plusDays(30));
      messageString = "Lending extended successfully!";
    } catch (UserOrBookDoesNotExistException e) {
      messageString = "Could not extend lending: " + e.getMessage();
    }
  }

  /**
   * Helper method to print out a list of given books
   *
   * @param message The message to display to the user.
   * @param books   The list of books to display.
   */
  private void listBooks(String message, List<Book> books) {
    System.out.println(message);
    int index = 1;
    for (Book book : books) {
      System.out.printf(
          "#%d: %s by %s\n",
          index++,
          book.getTitle(),
          book.getAuthors().stream().map(Author::getName).collect(Collectors.joining(", ")));
    }
  }

  /**
   * Helper method that overloads listBooks to list all books in the library.
   */
  private void listBooks() {
    clearScreen();
    listBooks("Books in the library: ", librarySystem.getAllBooks());
    prompt("Press enter to return...");
  }

  /**
   * Helper method to list all lendings for the user logged in.
   */
  private void listLendings() {
    clearScreen();
    listBooks("Your lent books: ",
        librarySystem.getAllLendingsForUser(userLoggedIn).stream().map(Lending::getBook).toList());
    prompt("Press enter to return...");
  }

  /**
   * Helper method to list all users in the library system.
   */
  private void listUsers() {
    clearScreen();
    List<User> users = librarySystem.getAllUsers();
    System.out.println("Users registered:");
    for (int i = 0; i < users.size(); i++) {
      System.out.printf("#%d: %s\n", i + 1, users.get(i).getName());
    }
    prompt("Press enter to return...");
  }

  /**
   * Helper method to list all users in the library system that have lendings.
   */
  private List<User> listUsersWithLending() {
    List<User> users = librarySystem.getAllUsers();
    List<User> usersWithLendings = new ArrayList<User>();

    for (User user : users) {
      if (librarySystem.getAllLendingsForUser(user).size() > 0) {
        usersWithLendings.add(user);
      }
    }

    if (usersWithLendings.size() == 0) {
      messageString = "No users with lendings";
      return null;
    }

    System.out.println("Users registered:");
    for (int i = 0; i < usersWithLendings.size(); i++) {
      System.out.printf("#%d: %s\n", i + 1, usersWithLendings.get(i).getName());
    }

    return usersWithLendings;
  }

  /**
   * Helper method to initialize the library system with some data.
   */
  private void addInitialData() {
    try {

      librarySystem.addBook("The Great Gatsby", "F. Scott Fitzgerald");
      librarySystem.addBook("The Catcher in the Rye", "J.D. Salinger");
      librarySystem.addBook("To Kill a Mockingbird", "Harper Lee");
      librarySystem.addBook("The Hobbit", "J.R.R.Tolkien");
      librarySystem.addBook("The Lord of the Rings", "J.R.R. Tolkien");
      librarySystem.addBook("The Silmarillion", "J.R.R. Tolkien");
      librarySystem.addBook("The Children of Húrin", "J.R.R. Tolkien");
      librarySystem.addBook("The Fall of Gondolin", "J.R.R. Tolkien");
      librarySystem.addBook("The Way of Kings", "Brandon Sanderson");

      librarySystem.addBook("Mistborn the Final Empire", "Brandon Sanderson");
      librarySystem.addBook("Good Omens", List.of(new Author("Neil Gaiman"), new Author("Terry Pratchett")));
      librarySystem.addBook("The wheel of time", List.of(new Author("Robert Jordan"), new Author("Brandon Sanderson")));

      librarySystem.addStudent("Frodo Baggins", true);
      librarySystem.addStudent("Samwise Gamgee", true);
      librarySystem.addStudent("Meriadoc Brandybuck", true);
      librarySystem.addStudent("Peregrin Took", false);

      librarySystem.addFaculty("Gandalf", "Wizard");
      librarySystem.addFaculty("Aragorn", "King");
      librarySystem.addFaculty("Dalinar Kholin", "Bondsmith");
      librarySystem.addFaculty("Vin", "Mistborn");

      librarySystem.borrowBook(
          librarySystem.findUserByName("Frodo Baggins"),
          librarySystem.findBookByTitle("The Hobbit"));

      librarySystem.borrowBook(
          librarySystem.findUserByName("Dalinar Kholin"),
          librarySystem.findBookByTitle("The Way of Kings"));

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
   * TODO: needs UML
   *
   * Helper utility method to prompt the user for input.
   *
   * @param message The message to display to the user.
   * @return The user's input.
   */
  private String prompt(String message) {
    System.out.print(message);
    return scanner.nextLine();
  }

  /**
   * TODO: needs UML
   *
   * Helper method to exit the program.
   */
  private void exit() {
    System.out.println("Exiting...");
    scanner.close();
    System.exit(0);
  }

  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    if (messageString != null) {
      System.out.println(messageString);
      System.out.println("------------------------");
    }
    messageString = null;
  }
}
