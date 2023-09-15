package command;

import model.IPModelInterface;

/**
 * sepia tone command: this calls the methods that add a sepia filter to the image
 * in the format that is compatible with the command design pattern.
 */
public class SepiaTone implements IPCommandInterface {

  /**
   * adds a sepia tone filter to an image by calling the method on the model that is given.
   * @param model the model of the image that the operation is being performed on
   */
  public void goCommand(IPModelInterface model) {
    model.makeSepiaBoard();
  }
}
