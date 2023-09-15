package command;

import model.IPModelInterface;

/**
 * grayscale command: this calls the method that makes the image in grayscale in the format
 * that is compatible with the command design pattern.
 */
public class MakeGrayscale implements IPCommandInterface {

  /**
   * visualizes the luma of the board by calling the method on the model that is given.
   * @param model the image that the method is getting called on
   */
  public void goCommand(IPModelInterface model) {
    model.makeGrayscaleBoard();
  }
}
