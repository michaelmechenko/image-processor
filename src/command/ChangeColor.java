package command;

import model.IPModelInterface;

/**
 * color visualizing command: this calls the change color methods in the format
 * that is compatible with the command design pattern.
 */
public class ChangeColor implements IPCommandInterface {
  private String color;

  /**
   * takes in the color, to be passed into the corresponding method.
   * @param color the color that we are visualizing (one of R,G,B)
   */
  public ChangeColor(String color) {
    this.color = color;
  }

  /**
   * visualizes the individual color components of the board by calling
   * the method on the model that is given.
   * @param model the image that the method is getting called on
   */
  public void goCommand(IPModelInterface model) {
    model.makeColorBoard(color);
  }
}
