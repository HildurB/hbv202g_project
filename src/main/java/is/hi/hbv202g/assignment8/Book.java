public class Book {
  
  private String title;
  private List<Author> authors;

  public Book(String title, List<Author> authors){
    this.title = title;
    this.authors = authors;
  }

  public String getTitle() {
    return title;
  }

  public List<Author> getAuthors() throws EmptyAuthorListException {
    if(authors.isEmpty()){
      throw new EmptyAuthorListException("The list of authors is empty!");
    }
    return authors;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public void addAuthor(Author author) {
    this.authors.add(author);
  }


}
