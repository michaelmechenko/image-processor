package command;

import model.IPModelInterface;

/**
 * VerticalFlip Command: This class represents a command to perform a vertical
 * flip on an image.
 */
public class VerticalFlip implements IPCommandInterface {

  /**
   * Flips the image vertically.
   * 
   * @param model The image model on which the flip operation is performed.
   */
  public void goCommand(IPModelInterface model) {
    model.flipVertically();
  }
}