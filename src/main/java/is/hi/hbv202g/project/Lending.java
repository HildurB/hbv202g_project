package is.hi.hbv202g.project;

import java.time.LocalDate;

/**
 * Lending class - Represents a lending of a book to a user
 * Contains a due date, a book and a user
 * 
 * @see Book Click here for more info on the book class
 * @see User Click here for more info on the user class
 * @see LocalDate Click here for more info on the LocalDate class
 */
public class Lending {
  private LocalDate dueDate;
  private Book book;
  private User user;

  /**
   * Constructor for Lending class
   *
   * Sets a default due date of 30 days from the current date
   * 
   * @param book Book to be lent
   * @param user User to lend the book to
   */
  public Lending(Book book, User user) {
    this.dueDate = LocalDate.now().plusDays(30);
    this.book = book;
    this.user = user;
  }

  /**
   * Getter for due date of the lending
   *
   * @return Due date of the lending
   */
  public LocalDate getDueDate() {
    return this.dueDate;
  }

  /**
   * Setter for due date of the lending
   *
   * @param dueDate - Due date of the lending
   */
  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * Getter for book of the lending
   *
   * @return Book of the lending
   */
  public Book getBook() {
    return book;
  }

  /**
   * Setter for book of the lending
   *
   * @param book - Book of the lending
   */
  public void setBook(Book book) {
    this.book = book;
  }

  /**
   * Getter for user of the lending
   *
   * @return User of the lending
   */
  public User getUser() {
    return user;
  }

  /**
   * Setter for user of the lending
   *
   * @param user - User of the lending
   */
  public void setUser(User user) {
    this.user = user;
  }
}
