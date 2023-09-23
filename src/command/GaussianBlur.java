package command;

import model.IPModelInterface;

/**
 * This class represents the blurring command: it calls the image blurring
 * methods in the format
 * that is compatible with the command design pattern.
 */
public class GaussianBlur implements IPCommandInterface {

  /**
   * Blurs an image by calling the method on the model that is given.
   * 
   * @param model the image that the operation is being performed on
   */
  public void goCommand(IPModelInterface model) {
    model.gaussianBlur();
  }
}