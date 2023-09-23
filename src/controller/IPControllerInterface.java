package controller;

/**
 * This interface represents the controller for the image processing program.
 * It handles user input and delegates to respective commands using the command design pattern. 
 * It saves all versions of the image to a map.
 */
public interface IPControllerInterface {

  /**
   * Performs commands on the image:
   * - Save
   * - Load
   * - Brighten/Darken
   * - Horizontally flip
   * - Vertically flip
   * - Visualize color components (RGB values)
   * - Kernel-based filter operations (sepia and grayscale)
   * - Quit the program
   * 
   * Saves all loaded images in the HashMap.
   * 
   * Handles InputMismatchException and IllegalArgumentException.
   */
  public void imageProcessing();
}