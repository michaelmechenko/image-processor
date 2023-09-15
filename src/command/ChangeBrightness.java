package command;

import model.IPModelInterface;

/**
 * brightening and darkening command: this calls the methods that change the brightness of the image
 * in the format that is compatible with the command design pattern.
 */
public class ChangeBrightness implements IPCommandInterface {
  private int lumaFactor;

  /**
   * takes in a luma factor that will be passed into the methods that change image brightness.
   * @param lumaFactor the factor determining how much the image gets brightened or darkened
   *                   positive = brighter, negative = darker.
   */
  public ChangeBrightness(int lumaFactor) {
    this.lumaFactor = lumaFactor;
  }

  /**
   * visualizes the luma component of the board by calling the method on the model
   * that is given.
   * @param model the image that the method is getting called on
   */
  public void goCommand(IPModelInterface model) {
    model.changeBrightness(lumaFactor);
  }


}