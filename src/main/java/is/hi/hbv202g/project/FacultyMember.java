package is.hi.hbv202g.project;

public class FacultyMember extends User {
  private String department;

  public FacultyMember(String name, String department) {
    super(name);
    this.department = department;
  }

  public String getDepartment() {
    return this.department; 
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
