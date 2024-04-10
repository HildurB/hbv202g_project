package is.hi.hbv202g.project;

public class Main {
  /**
   * Main method to run the library system.
   *
   * Implements a textual user interface for the library system.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    TextUI ui = new TextUI();
    ui.login();

    while (true) {
      ui.menu();
    }
  }
}
