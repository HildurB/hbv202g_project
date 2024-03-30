package is.hi.hbv202g.project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * BookTest - Tests the Book class
 */
public class BookTest {
  private Book book1;
  private List<Author> authors;

  /**
   * Constructs a Book object before each test
   */
  @Before
  public void setUp() {
    book1 = new Book("Book 1", "John Doe");
    authors = new ArrayList<>();
    authors.add(new Author("John Doe"));
    authors.add(new Author("Jane Doe"));
  }

  /**
   * Tests the Book constructor with two authors
   */
  @Test
  public void initializeBookWithMultipleAuthors() throws EmptyAuthorListException {
    Book book2 = new Book("Book 2", authors);
    assertNotNull(book2);
  }

  /**
   * Tests the Book constructor with an empty authors list
   */
  @Test(expected = EmptyAuthorListException.class)
  public void initializeBookWithEmptyAuthorsList() throws EmptyAuthorListException {
    Book book2 = new Book("Book 2", new ArrayList<>());
    assertNotNull(book2);
  }

  /**
   * Tests the Book class getTitle method
   */
  @Test
  public void testGetTitle() {
    assertEquals("Book 1", book1.getTitle());
  }

  /**
   * Tests the Book class setTitle method
   */
  @Test
  public void testSetTitle() {
    book1.setTitle("New Title");
    assertEquals("New Title", book1.getTitle());
  }

  /**
   * Tests the Book class getAuthors method
   */
  @Test
  public void testGetAuthorsSize() {
    List<Author> authors = book1.getAuthors();
    assertEquals(1, authors.size());
  }

  /**
   * Tests the Book class getAuthors method
   */
  @Test
  public void testGetAuthorsName() {
    List<Author> authors = book1.getAuthors();
    assertEquals("John Doe", authors.get(0).getName());
  }

  /**
   * Tests the Book class getAuthors method
   */
  @Test
  public void testSetAuthorsSize() throws EmptyAuthorListException {
    List<Author> newAuthors = new ArrayList<>();
    newAuthors.add(new Author("Jane Smith"));
    book1.setAuthors(newAuthors);
    assertEquals(1, book1.getAuthors().size());
  }

  /**
   * Tests setting the author's name
   */
  @Test
  public void testSetAuthorsName() throws EmptyAuthorListException {
    List<Author> newAuthors = new ArrayList<>();
    newAuthors.add(new Author("Jane Smith"));
    book1.setAuthors(newAuthors);
    assertEquals("Jane Smith", book1.getAuthors().get(0).getName());
  }

  /**
   * Tests throwing an exception when setting an empty author list
   */
  @Test(expected = EmptyAuthorListException.class)
  public void testSetAuthorsWithEmptyList() throws EmptyAuthorListException {
    book1.setAuthors(new ArrayList<>());
  }

  /**
   * Tests the Book class addAuthor method
   */
  @Test
  public void testAddAuthorSize() {
    Author newAuthor = new Author("Jane Smith");
    book1.addAuthor(newAuthor);
    assertEquals(2, book1.getAuthors().size());
  }

  /**
   * Tests the Book class getAuthor method
   */
  @Test
  public void testAddAuthorName() {
    Author newAuthor = new Author("Jane Smith");
    book1.addAuthor(newAuthor);
    assertEquals("Jane Smith", book1.getAuthors().get(1).getName());
  }

}
