package command;

import model.IPModelInterface;

/**
 * brightening and darkening command: this calls the methods that change the
 * brightness of the image
 * in the format that is compatible with the command design pattern.
 */
public class ChangeBrightness implements IPCommandInterface {
  private int lumaFactor;

  /**
   * Constructor for ChangeBrightness command.
   *
   * @param lumaFactor the factor determining how much the image gets brightened
   *                   or darkened
   *                   positive = brighter, negative = darker.
   */
  public ChangeBrightness(int lumaFactor) {
    this.lumaFactor = lumaFactor;
  }

  /**
   * Executes the change brightness command on the given model.
   *
   * @param model the image that the method is getting called on
   */
  public void goCommand(IPModelInterface model) {
    model.changeBrightness(lumaFactor);
  }
}