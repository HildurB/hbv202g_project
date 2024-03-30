package is.hi.hbv202g.project;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * FacultyMemberTest - Tests the FacultyMember class
 */
public class FacultyMemberTest {
  private FacultyMember facultyMember;

  /**
   * Constructs a FacultyMember object before each test
   */
  @Before
  public void setUp() {
    facultyMember = new FacultyMember("John Doe", "Computer Science");
  }

  /**
   * Tests the FacultyMember class getName method
   */
  @Test
  public void testGetNameFaculty() {
    assertEquals("John Doe", facultyMember.getName());
  }

  /**
   * Tests the FacultyMember class setName method
   */
  @Test
  public void testSetNameFaculty() {
    String newName = "Jane Doe";
    facultyMember.setName(newName);
    assertEquals(newName, facultyMember.getName());
  }

  /**
   * Tests the FacultyMember class getDepartment method
   */
  @Test
  public void testGetDepartment() {
    assertEquals("Computer Science", facultyMember.getDepartment());
  }

  /**
   * Tests the FacultyMember class setDepartment method
   */
  @Test
  public void testSetDepartment() {
    facultyMember.setDepartment("Electrical Engineering");
    assertEquals("Electrical Engineering", facultyMember.getDepartment());
  }
}
