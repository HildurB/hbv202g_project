package is.hi.hbv202g.project;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * LibrarySystemTest-Tests the LibrarySystem class
 */
public class LibrarySystemTest {

  private LibrarySystem librarySystem;
  private Book bookTest;
  private FacultyMember facultyMemberTest;

  /**
   * Constructs a LibrarySystem object before each test
   */
  @Before
  public void setUp() {
    if (librarySystem == null) {
      librarySystem = LibrarySystem.getInstance();
    } else {
      librarySystem.reset();
    }
    addInitialData(librarySystem);
  }

  /**
   * Tests the addBook method with a single author
   */
  @Test
  public void testAddBookWithSingleAuthor() {
    librarySystem.addBook("Book1", "Author1");
  }

  /**
   * Tests the addBook method with multiple authors
   */
  @Test
  public void testAddBookWithMultipleAuthors() throws EmptyAuthorListException {
    List<Author> authors = new ArrayList<>();
    authors.add(new Author("Author1"));
    authors.add(new Author("Author2"));
    librarySystem.addBook("Book1", authors);
  }

  /**
   * Tests the addBook method with an empty list of authors
   */
  @Test(expected = EmptyAuthorListException.class)
  public void testAddBookWithEmptyListOfAuthors() throws EmptyAuthorListException {
    List<Author> authors = new ArrayList<>();
    librarySystem.addBook("Book1", authors);
  }

  /**
   * Tests adding a student
   */
  @Test
  public void testAddStudentUser() throws UserOrBookDoesNotExistException {
    librarySystem.addStudent("Student", true);
    User user = librarySystem.findUserByName("Student");
    assertNotNull(user);
  }

  /**
   * Tests adding a faculty member
   */
  @Test
  public void testAddFacultyMemberUser() throws UserOrBookDoesNotExistException {
    librarySystem.addFaculty("Faculty", "Department");
    User user = librarySystem.findUserByName("Faculty");
    assertNotNull(user);
  }

  /**
   * Tests finding a book by title
   */
  @Test
  public void testFindBookByTitle() throws UserOrBookDoesNotExistException {
    librarySystem.findBookByTitle("Book1");
  }

  /**
   * Tests finding a book that does not exist
   */
  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testFindBookByNonExistingTitle() throws UserOrBookDoesNotExistException {
    librarySystem.findBookByTitle("This Book should not be found");
  }

  /**
   * Tests finding a user by name that does exist
   */
  @Test
  public void testFindUserByName() throws UserOrBookDoesNotExistException {
    librarySystem.findUserByName("Student1");
  }

  /**
   * Tests finding a user by name that does not exist
   */
  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testFindUserByNonExistingName() throws UserOrBookDoesNotExistException {
    librarySystem.findUserByName("Student Not There");
  }

  /**
   * Tests borrowing a book
   */
  @Test
  public void testBorrowBook() throws UserOrBookDoesNotExistException {
    User user = librarySystem.findUserByName("Student1");
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.borrowBook(user, book);
  }

  /**
   * Tests borrowing a book that does not exist
   */
  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testBorrowNonExistingBook() throws UserOrBookDoesNotExistException {
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.borrowBook(facultyMemberTest, book);
  }

  /**
   * Tests borrowing a book by a user that does not exist
   */
  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testBorrowNonExistingUser() throws UserOrBookDoesNotExistException {
    User user = librarySystem.findUserByName("Student1");
    librarySystem.borrowBook(user, bookTest);
  }

  /**
   * Tests extending a lending
   */
  @Test
  public void testExtendLending() throws UserOrBookDoesNotExistException {
    FacultyMember user = (FacultyMember) librarySystem.findUserByName("Faculty1");
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.extendLending(user, book, java.time.LocalDate.now().plusDays(30));
  }

  /**
   * Tests extending a lending by a student
   */
  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testExtendLendingByNonExistingFacultyMember() throws UserOrBookDoesNotExistException {
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.extendLending(facultyMemberTest, book, java.time.LocalDate.now().plusDays(30));
  }

  /**
   * Tests extending a lending by a book that does not exist
   */
  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testExtendLendingByNonExistingBook() throws UserOrBookDoesNotExistException {
    FacultyMember user = (FacultyMember) librarySystem.findUserByName("Faculty1");
    librarySystem.extendLending(user, bookTest, java.time.LocalDate.now().plusDays(30));
  }

  /**
   * Tests returning a book
   */
  @Test
  public void testReturnBook() throws UserOrBookDoesNotExistException {
    FacultyMember user = (FacultyMember) librarySystem.findUserByName("Faculty1");
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.borrowBook(user, book);
    librarySystem.returnBook(user, book);
  }

  /**
   * Tests returning a book by a student
   */
  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testReturnNotBorrowedBook() throws UserOrBookDoesNotExistException {
    FacultyMember user = (FacultyMember) librarySystem.findUserByName("Faculty1");
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.returnBook(user, book);
  }

  // Helper method to add initial data for testing
  private void addInitialData(LibrarySystem librarySystem) {
    librarySystem.addBook("Book1", "Author1");
    librarySystem.addBook("Book2", "Author2");
    librarySystem.addStudent("Student1", false);
    librarySystem.addFaculty("Faculty1", "Department1");

    bookTest = new Book("Book1", "Author1");
    facultyMemberTest = new FacultyMember("Faculty1", "Department1");

  }
}
