package is.hi.hbv202g.project;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AuthorTest {
  private Author author;

  @Before
  public void constructAuthorTest() {
    author = new Author("John Doe");
  }

  @Test
  public void testGetName() {
    String name = "John Doe";
    assertEquals(name, author.getName());
  }

  @Test
  public void testSetName() {
    String newName = "Alice Johnson";
    author.setName(newName);
    assertEquals(newName, author.getName());
  }
}
