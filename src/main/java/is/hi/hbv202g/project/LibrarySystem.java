package is.hi.hbv202g.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * LibrarySystem class - Represents a library system with books, users and
 * lendings
 * 
 * Contains functions to add books and users to the system, borrow and return
 * books and extend lendings
 * 
 * @see Book Click here for more info on the Book class
 * @see User Click here for more info on the User class
 * @see Lending Click here for more info on the Lending class
 * @see EmptyAuthorListException Click here for more info on the
 *      EmptyAuthorListException class
 * @see UserOrBookDoesNotExistException Click here for more info on the
 *      UserOrBookDoesNotExistException class
 */
public class LibrarySystem {
  private List<Lending> lendings = new ArrayList<Lending>();
  private List<Book> books = new ArrayList<Book>();
  private List<User> users = new ArrayList<User>();

  /**
   * Constructor for LibrarySystem class
   */
  public LibrarySystem() {

  }

  /**
   * Adds a book to the library system with a title and an single authors name
   * 
   * @param title      Title of the book
   * @param authorName Name of the author
   */
  public void addBook(String title, String authorName) {
    this.books.add(new Book(title, authorName));
  }

  /**
   * Adds a book to the library system with a title and a list of authors
   * 
   * @param title   Title of the book
   * @param authors List of authors
   * @throws EmptyAuthorListException - Thrown if the author list is empty
   */
  public void addBook(String title, List<Author> authors) throws EmptyAuthorListException {
    if (authors.isEmpty()) {
      throw new EmptyAuthorListException("Author list cannot be empty.");
    }
    this.books.add(new Book(title, authors));
  }

  /**
   * Adds a student to the library system
   * 
   * @param name    Name of the student
   * @param feePaid Whether the student has paid the fee
   */
  public void addStudent(String name, Boolean feePaid) {
    this.users.add(new Student(name, feePaid));
  }

  /**
   * Adds a faculty member to the library system
   * 
   * @param name       Name of the faculty member
   * @param department Department of the faculty member
   */
  public void addFaculty(String name, String department) {
    this.users.add(new FacultyMember(name, department));
  }

  /**
   * Finds a book by its title
   * 
   * @param title Title of the book
   * @return Book with the given title
   * @throws UserOrBookDoesNotExistException - Thrown if the book does not exist
   */
  public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
    for (Book book : this.books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    throw new UserOrBookDoesNotExistException("No book found with title: " + title);
  }

  /**
   * Finds a user by their name
   * 
   * @param name Name of the user
   * @return User with the given name
   * @throws UserOrBookDoesNotExistException - Thrown if the user does not exist
   */
  public User findUserByName(String name) throws UserOrBookDoesNotExistException {
    for (User user : this.users) {
      if (user.getName().equals(name)) {
        return user;
      }
    }
    throw new UserOrBookDoesNotExistException("No user found with name: " + name);
  }

  /**
   * Borrows a book for a user
   * 
   * @param user User borrowing the book
   * @param book Book being borrowed
   * @throws UserOrBookDoesNotExistException - Thrown if the user or book does not
   *                                         exist
   */
  public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
    if (!this.users.contains(user)) {
      throw new UserOrBookDoesNotExistException("User does not exist in the system");
    }
    if (!this.books.contains(book)) {
      throw new UserOrBookDoesNotExistException("Book does not exist in the system");
    }
    this.lendings.add(new Lending(book, user));
  }

  /**
   * Extends the lending period for a book for a faculty member
   * 
   * @param facultyMember Faculty member extending the lending
   * @param book          Book being extended
   * @param newDueDate    New due date for the book
   * @throws UserOrBookDoesNotExistException - Thrown if the user or book does not
   *                                         exist
   */
  public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate)
      throws UserOrBookDoesNotExistException {
    if (!this.books.contains(book)) {
      throw new UserOrBookDoesNotExistException("Book does not exist in the system");
    }
    if (!this.users.contains(facultyMember)) {
      throw new UserOrBookDoesNotExistException("User does not exist in the system");
    }
    for (Lending lending : this.lendings) {
      if (lending.getBook().equals(book) && lending.getUser().equals(facultyMember)) {
        lending.setDueDate(newDueDate);
        return;
      }
    }

  }

  /**
   * Returns a book for a given user
   * 
   * @param user User returning the book
   * @param book Book being returned
   * @throws UserOrBookDoesNotExistException - Thrown if the user or book does not
   *                                         exist
   */
  public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
    if (!this.users.contains(user)) {
      throw new UserOrBookDoesNotExistException("User does not exist in the system");
    }
    if (!this.books.contains(book)) {
      throw new UserOrBookDoesNotExistException("Book " + book.getTitle() + " not found");
    }
    for (Lending lending : this.lendings) {
      if (lending.getBook().equals(book) && lending.getUser().equals(user)) {
        this.lendings.remove(lending);
        return;
      }
    }
    throw new UserOrBookDoesNotExistException("User has not borrowed book");
  }

  /**
   * Getter for all books in the library system
   * 
   * @return List of books
   */
  public List<Book> getAllBooks() {
    return new ArrayList<>(books); // Return a copy of the list to prevent modification of the original list
  }

  /**
   * Getter for all users in the library system
   * 
   * @return List of users
   */
  public List<User> getAllUsers() {
    return new ArrayList<>(users); // Return a copy of the list to prevent modification of the original list
  }

  /**
   * Getter for all lendings in the library system
   * 
   * @return List of lendings
   */
  public List<Lending> getAllLendingsForUser(User user) {
    List<Lending> lendings = new ArrayList<Lending>();
    for (Lending lending : this.lendings) {
      if (lending.getUser().equals(user)) {
        lendings.add(lending);
      }
    }
    return lendings;
  }
}
