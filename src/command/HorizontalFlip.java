package command;

import model.IPModelInterface;

/**
 * horizontal flip command: this calls the horizontal flip method in the format
 * that is compatible with the command design pattern.
 */
public class HorizontalFlip implements IPCommandInterface {
  /**
   * flips the board horizontally by calling the corresponding method on the model that is given.
   * @param model the image that the method is getting called on
   */
  public void goCommand(IPModelInterface model) {
    model.flipHorizontally();
  }
}
