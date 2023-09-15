package view;

/**
 * The view interface. To motivate the methods here
 * think about all the operations that the controller
 * would need to invoke on the view
 */
public interface IView {

  /**
   * gets the commands inputted by the user from the view to the controller.
   * @return commands inputted by a user
   */
  public String getCommand();

}
