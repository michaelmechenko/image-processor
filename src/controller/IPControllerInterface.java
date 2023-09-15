package controller;

/**
 * this interface represents the controller for the image processing program. It handles user
 * input and delegates to respective commands using command design pattern. It saves all versions
 * of the image to a map.
 */
public interface IPControllerInterface {

  /**
   * performs commands on image: save, load, brighten/darken, horizontally flip, vertically flip,
   * visualizing color components (RGB values), and kernel-based filter operations
   * (sepia and grayscale) and quitting the program. Saves all loaded images in the HashMap.
   * Handles Input Mismatched Exception and Illegal Argument Exceptions.
   */
  public void imageProcessing();
}
