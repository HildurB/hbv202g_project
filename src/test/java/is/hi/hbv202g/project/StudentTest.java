package is.hi.hbv202g.project;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {
  private Student student;

  @Before
  public void setUp() {
      student = new Student("Alice", true);
  }

  @Test
  public void testGetNameStudent() {
      assertEquals("Alice", student.getName());
  }

  @Test
  public void testSetNameStudent() {
      String newName = "Jane Doe";
      student.setName(newName);
      assertEquals(newName, student.getName());
  }

  @Test
  public void testIsFeePaid() {
      assertTrue(student.isFeePaid());
  }

  @Test
  public void testSetFeePaid() {
      student.setFeePaid(false);
      assertFalse(student.isFeePaid());
  }
}
