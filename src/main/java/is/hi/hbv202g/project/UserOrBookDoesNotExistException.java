package is.hi.hbv202g.project;

/**
 * Exception that is thrown when a user or book does not exist.
 *
 * @see User Click here for more info on the User class
 * @see Book Click here for more info on the Book class
 */
public class UserOrBookDoesNotExistException extends Exception {
  /**
   * Constructor for the exception.
   * 
   * @param message The message to be displayed when the exception is thrown.
   */
  public UserOrBookDoesNotExistException(String message) {
    super(message);
  }
}
