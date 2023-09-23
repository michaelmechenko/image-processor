package command;

import model.IPModelInterface;

/**
 * Command to make the image grayscale.
 */
public class MakeGrayscale implements IPCommandInterface {

  /**
   * Executes the command to make the image grayscale.
   * 
   * @param model the image model on which the command is executed
   */
  public void goCommand(IPModelInterface model) {
    model.makeGrayscaleBoard();
  }
}