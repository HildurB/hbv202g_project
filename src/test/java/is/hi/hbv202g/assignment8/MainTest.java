package is.hi.hbv202g.assignment8;

import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple Main.
 */
public class MainTest {
   LibrarySystem librarySystem;
  
   @Test
   public void shouldBePossibleToInstantiateLibrarySystem() {
   librarySystem = new LibrarySystem();
   }
  
   @Test
   public void shouldBePossibleToInstantiateBookWithOneAuthor() {
   new Book("The Hobbit", "J.R.R. Tolkien");
   }
  
   @Test
   public void shouldBePossibleToInstantiateBookWithMultipleAuthors() {
   try {
   new Book("The Hobbit", List.of(new Author("J.R.R. Tolkien"), new
   Author("Christopher Tolkien")));
   } catch (Exception e) {
    //TODO: handle exception
   }
   }
  
   @Test
   public void shouldBePossibleToInstantiateAuthor() {
   new Author("J.R.R. Tolkien");
   }
  
   @Test
   public void shouldBeAbleToAddBookToLibrary() {
   try {
   librarySystem.addBookWithTitleAndNameOfSingleAuthor("The Hobbit", "J.R.R.Tolkien");
   librarySystem.addBookWithTitleAndAuthorList("The Hobbit", List.of(new
   Author("J.R.R")));
   } catch (Exception e) {
    //TODO: handle exception
   }
   }
}