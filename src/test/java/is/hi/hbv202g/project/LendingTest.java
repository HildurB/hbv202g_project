package is.hi.hbv202g.project;

import java.time.LocalDate;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * LendingTest - Tests the Lending class
 */
public class LendingTest {
  private Lending lending;
  private Book book;
  private User user;

  /**
   * Constructs a Lending object before each test
   */
  @Before
  public void setUp() {
    book = new Book("Test Book", "Test Author");
    user = new FacultyMember("Test User", "Test Department");
    lending = new Lending(book, user);
  }

  /**
   * Tests the default date of a lending
   */
  @Test
  public void testGetDueDate() {
    LocalDate expectedDueDate = LocalDate.now().plusDays(30);
    assertEquals(expectedDueDate, lending.getDueDate());
  }

  /**
   * Tests setting a new due date
   */
  @Test
  public void testSetDueDate() {
    LocalDate newDueDate = LocalDate.now().plusDays(10);
    lending.setDueDate(newDueDate);
    assertEquals(newDueDate, lending.getDueDate());
  }

  /**
   * Tests the getBook method
   */
  @Test
  public void testGetBook() {
    assertEquals(book, lending.getBook());
  }

  /**
   * Tests the setBook method
   */
  @Test
  public void testSetBook() {
    Book newBook = new Book("New Book", "New Author");
    lending.setBook(newBook);
    assertEquals(newBook, lending.getBook());
  }

  /**
   * Tests the getUser method
   */
  @Test
  public void testGetUser() {
    assertEquals(user, lending.getUser());
  }

  /**
   * Tests the setUser method
   */
  @Test
  public void testSetUser() {
    User newUser = new FacultyMember("New User", "New Department");
    lending.setUser(newUser);
    assertEquals(newUser, lending.getUser());
  }
}
