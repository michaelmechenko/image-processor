package command;

import model.IPModelInterface;

/**
 * sepia tone command: this class represents a command that applies a sepia tone
 * filter to an image.
 * It implements the IPCommandInterface interface.
 */
public class SepiaTone implements IPCommandInterface {

  /**
   * Applies a sepia tone filter to an image by calling the makeSepiaBoard()
   * method on the given model.
   * 
   * @param model the model of the image that the operation is being performed on
   */
  public void goCommand(IPModelInterface model) {
    model.makeSepiaBoard();
  }
}