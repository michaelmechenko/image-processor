package command;

import model.IPModelInterface;

/**
 * vertical flip Command: this calls the vertical flip method in the format
 * that is compatible with the command design pattern.
 */
public class VerticalFlip implements IPCommandInterface {

  /**
   * flips the board vertically by calling the corresponding method on the model that is given.
   * @param model the image that the method is getting called on
   */
  public void goCommand(IPModelInterface model) {
    model.flipVertically();
  }
}
