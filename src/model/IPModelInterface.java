package model;

/**
 * This represents all types of image processing models, including the main
 * functionality of:
 * brightening, saving, loading, flipping vertically and horizontally.
 */
public interface IPModelInterface {

  /**
   * Returns the width (number of columns) of the image.
   * 
   * @return the width of the image in pixels
   */
  public int getWidth();

  /**
   * Returns the height (number of rows) of the image.
   * 
   * @return the height of the image in pixels
   */
  public int getHeight();

  /**
   * Returns the pixel at the specified row and column.
   * 
   * @param row the row index of the pixel
   * @param col the column index of the pixel
   * @return the pixel at the specified row and column
   */
  public PixelInterface getPixel(int row, int col);

  /**
   * Visualizes an individual color component from the image.
   * 
   * @param color the RGB value that we are visualizing
   * @throws IllegalArgumentException if the user inputs an invalid color
   */
  public void makeColorBoard(String color) throws IllegalArgumentException;

  /**
   * Checks if any of the image's edited versions correspond with the user input
   * image name.
   * 
   * @param currImageName the name of the image that the user wants to perform
   *                      operations on
   * @return true if there is a version of the Pixel[][] that corresponds to the
   *         user input
   */
  public boolean getPrevImages(String currImageName);

  /**
   * Stores a version of the image after being modified by a command from the
   * controller.
   * 
   * @param currImageName the reference name for the edited image (pixel array)
   */
  public void writePrevImages(String currImageName);

  /**
   * Visualizes the luma factor (brightness) of the image.
   */
  public void makeGrayscaleBoard();

  /**
   * Iterates through the image grid and assigns each pixel to its vertical
   * reflection.
   */
  public void flipVertically();

  /**
   * Iterates through the image grid and assigns each pixel to its horizontal
   * reflection.
   */
  public void flipHorizontally();

  /**
   * Iterates through the image grid and brightens or darkens each pixel by the
   * luma factor.
   * 
   * @param lumaFactor the degree to which the image is brightened or darkened
   *                   (positive = bright, negative = dark)
   */
  public void changeBrightness(int lumaFactor);

  /**
   * Creates a sepia tone version of the image.
   */
  public void makeSepiaBoard();

  /**
   * Sharpens the picture.
   */
  public void sharpenPicture();

  /**
   * Applies Gaussian blur to the image.
   */
  public void gaussianBlur();
}