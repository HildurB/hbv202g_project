package is.hi.hbv202g.project;

/**
 * Author class - Represents an author with a name
 */
public class Author {
  private String name;

  /**
   * Author constructor
   * 
   * @param name Name of the author
   */
  public Author(String name) {
    this.name = name;
  }

  /**
   * Getter for the name of the author
   * 
   * @return Name of the author
   */
  public String getName() {
    return this.name;
  }

  /**
   * Setter the name of the author
   * 
   * @param name Name of the author
   */
  public void setName(String name) {
    this.name = name;
  }
}
