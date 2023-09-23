package command;

import model.IPModelInterface;

/**
 * sharpen command: this calls the method that sharpens the image in the format
 * that is compatible
 * with the command design pattern.
 */
public class Sharpen implements IPCommandInterface {

  /**
   * sharpens an image (increases contrast) by calling the method on the model
   * that is given.
   * 
   * @param model the model of the image that the operation is being performed on
   */
  public void goCommand(IPModelInterface model) {
    // Call the sharpenPicture() method on the model to sharpen the image
    model.sharpenPicture();
  }
}