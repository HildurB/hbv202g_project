package is.hi.hbv202g.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Book class - Represents a book with a title and a list of authors
 *
 * @see Author Click here for more info on the Author class
 */
public class Book {
  private String title;
  private List<Author> authors = new ArrayList<Author>();

  /**
   * Constructor for Book class
   * 
   * @param title      Title of the book
   * @param authorName Name of the author
   */
  public Book(String title, String authorName) {
    this.title = title;
    this.authors.add(new Author(authorName));
  }

  /**
   * Constructor for Book class
   * 
   * @param title   Title of the book
   * @param authors List of authors
   * @throws EmptyAuthorListException
   *
   * @see Author for more info on the author class
   * @see EmptyAuthorListException for more info on the exception
   */
  public Book(String title, List<Author> authors) throws EmptyAuthorListException {
    this.title = title;
    if (authors.isEmpty()) {
      throw new EmptyAuthorListException("No authors found for book: " + this.title);
    }
    this.authors = authors;
  }

  /**
   * Getter for authors of the book
   *
   * @return List of authors
   */
  public List<Author> getAuthors() {
    return this.authors;
  }

  /**
   * Setter for authors of the book
   *
   * @param authors - A list of authors
   * @throws EmptyAuthorListException
   */
  public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
    if (authors.isEmpty()) {
      throw new EmptyAuthorListException("No authors found for book: " + this.title);
    }
    this.authors = authors;
  }

  /**
   * Add a single author to the book
   *
   * @param author - The author to add
   *
   * @see Author for more info on the author class
   */
  public void addAuthor(Author author) {
    this.authors.add(author);
  }

  /**
   * Getter for the title of the book
   * 
   * @return Title of the book
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Setter for the title of the book
   * 
   * @param title Title of the book
   */
  public void setTitle(String title) {
    this.title = title;
  }

}
