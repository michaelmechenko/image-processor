package command;

import model.IPModelInterface;

/**
 * Command Design Pattern Interface: This represents a command or operation that can be performed
 * on an image. Responsible for delegating the command calls to their respective IPModel methods.
 */
public interface IPCommandInterface {
  /**
   * performs the operation dependent on the input (command) the user gives.
   * @param model the model of the image that the operation is being performed on
  */
  public void goCommand(IPModelInterface model);


}
