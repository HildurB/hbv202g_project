public class Student extends User{
  
  private Boolean feePaid;

  public Student(String name, Boolean feePaid) {
    super(name);
    this.feePaid = feePaid;
  }

  public Boolean isFeePaid() {
    return feePaid;
  }

  public void setFeePaid(Boolean feePaid) {
    this.feePaid = feePaid;
  }

}
