package is.hi.hbv202g.project;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * StudentTest - Tests the Student class
 */
public class StudentTest {
  private Student student;

  /**
   * Constructs a Student object before each test
   */
  @Before
  public void setUp() {
    student = new Student("Alice", true);
  }

  /**
   * Tests the getName method
   */
  @Test
  public void testGetNameStudent() {
    assertEquals("Alice", student.getName());
  }

  /**
   * Tests the setName method
   */
  @Test
  public void testSetNameStudent() {
    String newName = "Jane Doe";
    student.setName(newName);
    assertEquals(newName, student.getName());
  }

  /**
   * Tests the isFeePaid method
   */
  @Test
  public void testIsFeePaid() {
    assertTrue(student.isFeePaid());
  }

  /**
   * Tests the setFeePaid method
   */
  @Test
  public void testSetFeePaid() {
    student.setFeePaid(false);
    assertFalse(student.isFeePaid());
  }
}
