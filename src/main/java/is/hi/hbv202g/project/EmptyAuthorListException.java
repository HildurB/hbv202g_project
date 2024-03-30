package is.hi.hbv202g.project;

/**
 * Exception that is thrown when an author list is empty.
 *
 * @see Author Click here for more info on the Author class
 */
public class EmptyAuthorListException extends Exception {
  /**
   * Constructor for the exception.
   * 
   * @param message The message to be displayed when the exception is thrown.
   */
  public EmptyAuthorListException(String message) {
    super(message);
  }
}
