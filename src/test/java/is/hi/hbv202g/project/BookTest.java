package is.hi.hbv202g.project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import is.hi.hbv202g.project.Author;
import is.hi.hbv202g.project.Book;
import is.hi.hbv202g.project.EmptyAuthorListException;

import java.util.ArrayList;
import java.util.List;

public class BookTest {
    private Book book1;
    private List<Author> authors;

    @Before
    public void setUp() {
        book1 = new Book("Book 1", "John Doe");
        authors = new ArrayList<>();
        authors.add(new Author("John Doe"));
        authors.add(new Author("Jane Doe"));
    }

    @Test
    public void initializeBookWithMultipleAuthors() throws EmptyAuthorListException {
      Book book2 = new Book("Book 2", authors);
      assertNotNull(book2);
    }

    @Test(expected = EmptyAuthorListException.class)
    public void initializeBookWithEmptyAuthorsList() throws EmptyAuthorListException {
      Book book2 = new Book("Book 2", new ArrayList<>());
      assertNotNull(book2);
    }

    @Test
    public void testGetTitle() {
        assertEquals("Book 1", book1.getTitle());
    }

    @Test
    public void testSetTitle() {
        book1.setTitle("New Title");
        assertEquals("New Title", book1.getTitle());
    }

    @Test
    public void testGetAuthorsSize() {
        List<Author> authors = book1.getAuthors();
        assertEquals(1, authors.size());
    }

    @Test
    public void testGetAuthorsName() {
        List<Author> authors = book1.getAuthors();
        assertEquals("John Doe", authors.get(0).getName());
    }


    @Test
    public void testSetAuthorsSize() throws EmptyAuthorListException {
        List<Author> newAuthors = new ArrayList<>();
        newAuthors.add(new Author("Jane Smith"));
        book1.setAuthors(newAuthors);
        assertEquals(1, book1.getAuthors().size());
    }

    @Test
    public void testSetAuthorsName() throws EmptyAuthorListException {
        List<Author> newAuthors = new ArrayList<>();
        newAuthors.add(new Author("Jane Smith"));
        book1.setAuthors(newAuthors);
        assertEquals("Jane Smith", book1.getAuthors().get(0).getName());
    }

    @Test(expected = EmptyAuthorListException.class)
    public void testSetAuthorsWithEmptyList() throws EmptyAuthorListException {
        book1.setAuthors(new ArrayList<>());
    }

    @Test
    public void testAddAuthorSize() {
        Author newAuthor = new Author("Jane Smith");
        book1.addAuthor(newAuthor);
        assertEquals(2, book1.getAuthors().size());
    }

    @Test
    public void testAddAuthorName() {
        Author newAuthor = new Author("Jane Smith");
        book1.addAuthor(newAuthor);
        assertEquals("Jane Smith", book1.getAuthors().get(1).getName());
    }

}
