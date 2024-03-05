package is.hi.hbv202g.assignment8;

import java.util.List;

public class LibrarySystem {

  private List<Book> books = new ArrayList<Book>();
  private List<User> users = new ArrayList<User>();
  private List<Lending> users = new ArrayList<Lending>();

  public LibrarySystem() {

  }

  public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName){
    books.add(new Book(title, new Author(authorName)));
  }

  public void addBookWithTitleAndAuthorList(String title, List<Author>; authors) throws EmptyAuthorListException{
    if (authors == null || authors.isEmpty()) {
      throw new EmptyAuthorListException("Author list cannot be empty");
    }
    books.add(new Book(title, authors));
  }

  public void addFacultyMemberUser(String name, String department){
    users.add(new FacultyMember(name, department));
  }

  public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException{
    for (Book book : books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    throw new UserOrBookDoesNotExistException("Book with title '" + title + "' not found.");
  }

  public User findUserByName(String name) throws UserOrBookDoesNotExistException{
    for (User user : users) {
      if (user.getName().equals(name)) {
        return user;
      }
    }
    throw new UserOrBookDoesNotExistException("User with name '" + name + "' not found."); 
  }

  public void borrowBook(User user, Book book){

  }

  public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate){

  }

  public void returnBook(User user, Book book){

  }
}
