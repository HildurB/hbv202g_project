package is.hi.hbv202g.project;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class FacultyMemberTest {
    private FacultyMember facultyMember;

    @Before
    public void setUp() {
        facultyMember = new FacultyMember("John Doe", "Computer Science");
    }

    @Test
    public void testGetNameFaculty() {
        assertEquals("John Doe", facultyMember.getName());
    }

    @Test
    public void testSetNameFaculty  () {
        String newName = "Jane Doe";
        facultyMember.setName(newName);
        assertEquals(newName, facultyMember.getName());
    }

    @Test
    public void testGetDepartment() {
        assertEquals("Computer Science", facultyMember.getDepartment());
    }

    @Test
    public void testSetDepartment() {
        facultyMember.setDepartment("Electrical Engineering");
        assertEquals("Electrical Engineering", facultyMember.getDepartment());
    }
}
