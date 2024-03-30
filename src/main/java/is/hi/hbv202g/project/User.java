package is.hi.hbv202g.project;

/**
 * User class - Represents a user with a name
 * 
 * Abstract class that is extended by Student and FacultyMember classes
 * 
 * @see Student Click here for more info on the Student class
 * @see FacultyMember Click here for more info on the FacultyMember class
 */
public abstract class User {
  private String name;

  /**
   * Constructor for User class
   * 
   * @param name Name of the user
   */
  User(String name) {
    this.name = name;
  }

  /**
   * Getter for name of the user
   * 
   * @return Name of the user
   */
  public String getName() {
    return this.name;
  }

  /**
   * Setter for name of the user
   * 
   * @param name Name of the user
   */
  public void setName(String name) {
    this.name = name;
  }
}
