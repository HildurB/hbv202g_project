package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

public class Book {
  private String title;
  private List<Author> authors = new ArrayList<Author>();

  public Book(String title, String authorName) {
    this.title = title;
    this.authors.add(new Author(authorName));
  }

  public Book(String title, List<Author> authors) throws EmptyAuthorListException {
    this.title = title;
    if (authors.isEmpty()) {
      throw new EmptyAuthorListException("No authors found for book: " + this.title);
    }
    this.authors = authors;
  }

  public List<Author> getAuthors() {
    return this.authors;
  }

  public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
    if (authors.isEmpty()) {
      throw new EmptyAuthorListException("No authors found for book: " + this.title);
    }
    this.authors = authors;
  }

  public void addAuthor(Author author) {
    this.authors.add(author);
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
