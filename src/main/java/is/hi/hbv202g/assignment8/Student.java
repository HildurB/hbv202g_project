package is.hi.hbv202g.assignment8;

public class Student extends User {
  private boolean feePaid;

  public Student(String name, boolean feePaid) {
    super(name);
    this.feePaid = feePaid;
  }

  public boolean isFeePaid() {
    return this.feePaid;
  }

  public void setFeePaid(boolean feePaid) {
    this.feePaid = feePaid;
  }
}
