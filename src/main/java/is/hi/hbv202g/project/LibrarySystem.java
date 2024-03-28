package is.hi.hbv202g.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
  private List<Lending> lendings = new ArrayList<Lending>();
  private List<Book> books = new ArrayList<Book>();
  private List<User> users = new ArrayList<User>();

  public LibrarySystem() {
    
  }

  public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
    this.books.add(new Book(title, authorName));
  }

  public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
    if(authors.isEmpty()) {
      throw new EmptyAuthorListException("Author list cannot be empty.");
    }
    this.books.add(new Book(title, authors));
  }

  public void addStudentUser(String name, Boolean feePaid) {
    this.users.add(new Student(name, feePaid));
  }

  public void addFacultyMemberUser(String name, String department) {
    this.users.add(new FacultyMember(name, department));
  }

  public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
    for (Book book : this.books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    throw new UserOrBookDoesNotExistException("No book found with title: " + title);
  }

  public User findUserByName(String name) throws UserOrBookDoesNotExistException {
    for (User user : this.users) {
      if (user.getName().equals(name)) {
        return user;
      }
    }
    throw new UserOrBookDoesNotExistException("No user found with name: " + name);
  }

  public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
    if (!this.users.contains(user)) {
      throw new UserOrBookDoesNotExistException("User does not exist in the system");
    }
    if (!this.books.contains(book)) {
      throw new UserOrBookDoesNotExistException("Book does not exist in the system");
    }
    this.lendings.add(new Lending(book, user));
  }

  public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException{
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

  public List<Book> getAllBooks() {
    return new ArrayList<>(books); // Return a copy of the list to prevent modification of the original list
  }

  public List<User> getAllUsers() {
    return new ArrayList<>(users); // Return a copy of the list to prevent modification of the original list
  }

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
