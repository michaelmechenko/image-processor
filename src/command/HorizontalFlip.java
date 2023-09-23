package command;

import model.IPModelInterface;

/**
 * Represents a horizontal flip command.
 * This class implements the IPCommandInterface and provides a method to flip
 * the board horizontally.
 */
public class HorizontalFlip implements IPCommandInterface {

  /**
   * Flips the board horizontally by calling the corresponding method on the
   * provided model.
   * 
   * @param model the image that the method is getting called on
   */
  public void goCommand(IPModelInterface model) {
    model.flipHorizontally();
  }
}