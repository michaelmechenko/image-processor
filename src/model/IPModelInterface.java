package model;

/**
 * This represents all types of image processing models, including the main functionality of:
 * brightening, saving, loading, flipping vertically and horizontally.
 */
public interface IPModelInterface {

  /**
   * get width (number of cols) of the board.
   * @return width of board in pixels.
   */
  public int getWidth();

  /**
   * get height (number of rows) of the board.
   * @return height of board in pixels.
   */
  public int getHeight();

  /**
   * Returns the pixel at a certain row and certain column.
   * @return pixel at a certain row and column in the image
   */
  public PixelInterface getPixel(int row, int col);

  /**
   * visualizes an individual color component from the picture.
   * @param color the RGB value that we are visualizing
   * @throws IllegalArgumentException if the user inputs an invalid color
   */
  public void makeColorBoard(String color) throws IllegalArgumentException;

  /**
   * Checks if any of the image's edited versions correspond with user input image name.
   * @param currImageName name of the image that the user wants to perform operations on
   * @return true if there is a version of the Pixel[][] that corresponds with user input
   */
  public boolean getPrevImages(String currImageName);

  /**
   * stores a version of the image after being modified by a command from the controller.
   * @param currImageName reference name for the edited image (pixel array)
   */
  public void writePrevImages(String currImageName);

  /**
   * visualizes the luma factor (brightness) of the image.
   */
  public void makeGrayscaleBoard();

  /**
   * iterates through the image grid and assigns each pixel to its vertical reflection.
   */
  public void flipVertically();

  /**
   * iterates through the image grid and assigns each pixel to its horizontal reflection.
   */
  public void flipHorizontally();

  /**
   * iterates through the board and brightens or darkens each pixel by the luma factor.
   * @param lumaFactor the degree to which the image is brightened or darkened (positive = bright,
   *                   negative = dark)
   */
  public void changeBrightness(int lumaFactor);

  public void makeSepiaBoard();

  public void sharpenPicture();

  public void gaussianBlur();


}
