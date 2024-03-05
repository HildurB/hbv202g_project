import java.time.LocalDate;

public class Lending {
  
  private LocalDate duDate;
  private Book book;
  private User user;

  public Lending(Book book, User user) {
    this.book = book;
    this.user = user;
    this.duDate = LocalDate.now().plusDays(30);
  }

  public LocalDate getDueDate() {
    return duDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.duDate = dueDate;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }




}
