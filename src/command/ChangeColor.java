package command;

import model.IPModelInterface;

/**
 * ChangeColor command: This class implements the IPCommandInterface and
 * represents a command to change the color visualization in a compatible format
 * with the command design pattern.
 */
public class ChangeColor implements IPCommandInterface {
  private String color;

  /**
   * Constructor for ChangeColor command.
   * 
   * @param color The color to be visualized (one of R, G, B).
   */
  public ChangeColor(String color) {
    this.color = color;
  }

  /**
   * Executes the ChangeColor command by calling the makeColorBoard method on the
   * given model.
   * 
   * @param model The model on which the method is called.
   */
  public void goCommand(IPModelInterface model) {
    model.makeColorBoard(color);
  }
}