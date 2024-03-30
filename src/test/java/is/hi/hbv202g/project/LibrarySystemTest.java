package is.hi.hbv202g.project;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LibrarySystemTest {

  private LibrarySystem librarySystem;
  private Book bookTest;
  private FacultyMember facultyMemberTest;

  @Before
  public void setUp() {
    librarySystem = new LibrarySystem();
    addInitialData(librarySystem);
  }

  @Test
  public void testAddBookWithSingleAuthor() {
    librarySystem.addBook("Book1", "Author1");
  }

  @Test
  public void testAddBookWithMultipleAuthors() throws EmptyAuthorListException {
    List<Author> authors = new ArrayList<>();
    authors.add(new Author("Author1"));
    authors.add(new Author("Author2"));
    librarySystem.addBook("Book1", authors);
  }

  @Test(expected = EmptyAuthorListException.class)
  public void testAddBookWithEmptyListOfAuthors() throws EmptyAuthorListException {
    List<Author> authors = new ArrayList<>();
    librarySystem.addBook("Book1", authors);
  }

  @Test
  public void testAddStudentUser() throws UserOrBookDoesNotExistException {
    librarySystem.addStudent("Student", true);
    User user = librarySystem.findUserByName("Student");
    assertNotNull(user);
  }

  @Test
  public void testAddFacultyMemberUser() throws UserOrBookDoesNotExistException {
    librarySystem.addFaculty("Faculty", "Department");
    User user = librarySystem.findUserByName("Faculty");
    assertNotNull(user);
  }

  @Test
  public void testFindBookByTitle() throws UserOrBookDoesNotExistException {
    librarySystem.findBookByTitle("Book1");
  }

  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testFindBookByNonExistingTitle() throws UserOrBookDoesNotExistException {
    librarySystem.findBookByTitle("This Book should not be found");
  }

  @Test
  public void testFindUserByName() throws UserOrBookDoesNotExistException {
    librarySystem.findUserByName("Student1");
  }

  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testFindUserByNonExistingName() throws UserOrBookDoesNotExistException {
    librarySystem.findUserByName("Student Not There");
  }

  @Test
  public void testBorrowBook() throws UserOrBookDoesNotExistException {
    User user = librarySystem.findUserByName("Student1");
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.borrowBook(user, book);
  }

  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testBorrowNonExistingBook() throws UserOrBookDoesNotExistException {
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.borrowBook(facultyMemberTest, book);
  }

  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testBorrowNonExistingUser() throws UserOrBookDoesNotExistException {
    User user = librarySystem.findUserByName("Student1");
    librarySystem.borrowBook(user, bookTest);
  }

  @Test
  public void testExtendLending() throws UserOrBookDoesNotExistException {
    FacultyMember user = (FacultyMember) librarySystem.findUserByName("Faculty1");
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.extendLending(user, book, java.time.LocalDate.now().plusDays(30));
  }

  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testExtendLendingByNonExistingFacultyMember() throws UserOrBookDoesNotExistException {
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.extendLending(facultyMemberTest, book, java.time.LocalDate.now().plusDays(30));
  }

  @Test(expected = UserOrBookDoesNotExistException.class)
  public void testExtendLendingByNonExistingBook() throws UserOrBookDoesNotExistException {
    FacultyMember user = (FacultyMember) librarySystem.findUserByName("Faculty1");
    librarySystem.extendLending(user, bookTest, java.time.LocalDate.now().plusDays(30));
  }

  @Test
  public void testReturnBook() throws UserOrBookDoesNotExistException {
    FacultyMember user = (FacultyMember) librarySystem.findUserByName("Faculty1");
    Book book = librarySystem.findBookByTitle("Book1");
    librarySystem.borrowBook(user, book);
    librarySystem.returnBook(user, book);
  }

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
