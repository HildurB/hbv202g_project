package is.hi.hbv202g.project;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * AuthorTest - Tests the Author class
 */
public class AuthorTest {
  private Author author;

  /**
   * Constructs an Author object before each test
   */
  @Before
  public void constructAuthorTest() {
    author = new Author("John Doe");
  }

  /**
   * Tests the getName method
   */
  @Test
  public void testGetName() {
    String name = "John Doe";
    assertEquals(name, author.getName());
  }

  /**
   * Tests the setName method
   */
  @Test
  public void testSetName() {
    String newName = "Alice Johnson";
    author.setName(newName);
    assertEquals(newName, author.getName());
  }
}
