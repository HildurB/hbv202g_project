package is.hi.hbv202g.project;

/**
 * Faculty Member class - Represents a faculty member with a name and a
 * department
 *
 * Extends the User class
 * 
 * @see User Click here for more info on the User class
 * 
 */
public class FacultyMember extends User {
  private String department;

  /**
   * Constructor for FacultyMember class
   * 
   * @param name       Name of the faculty member
   * @param department Department of the faculty member
   */
  public FacultyMember(String name, String department) {
    super(name);
    this.department = department;
  }

  /**
   * Getter for department of the faculty member
   *
   * @return Department of the faculty member
   */
  public String getDepartment() {
    return this.department;
  }

  /**
   * Setter for department of the faculty member
   *
   * @param department Department of the faculty member
   */
  public void setDepartment(String department) {
    this.department = department;
  }
}
