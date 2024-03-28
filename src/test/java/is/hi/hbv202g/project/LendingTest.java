package is.hi.hbv202g.project;

import java.time.LocalDate;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LendingTest {
    private Lending lending;
    private Book book;
    private User user;

    @Before
    public void setUp() {
        book = new Book("Test Book", "Test Author");
        user = new FacultyMember("Test User", "Test Department");
        lending = new Lending(book, user);
    }

    @Test
    public void testGetDueDate() {
        LocalDate expectedDueDate = LocalDate.now().plusDays(30);
        assertEquals(expectedDueDate, lending.getDueDate());
    }

    @Test
    public void testSetDueDate() {
        LocalDate newDueDate = LocalDate.now().plusDays(10);
        lending.setDueDate(newDueDate);
        assertEquals(newDueDate, lending.getDueDate());
    }

    @Test
    public void testGetBook() {
        assertEquals(book, lending.getBook());
    }

    @Test
    public void testSetBook() {
        Book newBook = new Book("New Book", "New Author");
        lending.setBook(newBook);
        assertEquals(newBook, lending.getBook());
    }

    @Test
    public void testGetUser() {
        assertEquals(user, lending.getUser());
    }

    @Test
    public void testSetUser() {
        User newUser = new FacultyMember("New User", "New Department");
        lending.setUser(newUser);
        assertEquals(newUser, lending.getUser());
    }
}
