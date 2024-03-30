package is.hi.hbv202g.project;

/**
 * Student class - Represents a student with a name and a feePaid status
 * 
 * Extends the User class
 * 
 * @see User Click here for more info on the User class
 */
public class Student extends User {
  private boolean feePaid;

  /**
   * Constructor for Student class
   * 
   * @param name    Name of the student
   * @param feePaid Fee paid status of the student
   */
  public Student(String name, boolean feePaid) {
    super(name);
    this.feePaid = feePaid;
  }

  /**
   * Getter for feePaid status of the student
   * 
   * @return Fee paid status of the student
   */
  public boolean isFeePaid() {
    return this.feePaid;
  }

  /**
   * Setter for feePaid status of the student
   * 
   * @param feePaid - Fee paid status of the student
   */
  public void setFeePaid(boolean feePaid) {
    this.feePaid = feePaid;
  }
}
