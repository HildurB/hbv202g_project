package is.hi.hbv202g.project;

public abstract class User {
  private String name;

  User(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}