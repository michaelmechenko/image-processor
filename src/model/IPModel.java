package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This represents the model of a PPM image as a 2D array of pixels. Performs
 * operations
 * implemented in the controller (see README.md for more detail on commands).
 */
public class IPModel implements IPModelInterface {
  private AbstractPixel[][] imageGrid;
  private Map<String, AbstractPixel[][]> prevImages;

  /**
   * Constructs an IPModel object with the specified image grid and name of the
   * current image.
   *
   * @param board           the 2D representation of the array in terms of Pixels
   * @param nameOfCurrImage the image's name as referenced in the map
   * @throws IllegalArgumentException if the inputted (board or nameOfCurrImage)
   *                                  args are null
   */
  public IPModel(AbstractPixel[][] board, String nameOfCurrImage)
      throws IllegalArgumentException {
    if (board == null || nameOfCurrImage == null) {
      throw new IllegalArgumentException("One or more arguments is null");
    }
    this.imageGrid = board;
    this.prevImages = new HashMap<>();
    prevImages.put(nameOfCurrImage, imageGrid);
  }

  /**
   * This method returns the width (number of cols) of the board.
   *
   * @return The width of the board in pixels.
   */
  public int getWidth() {
    return this.imageGrid[0].length;
  }

  /**
   * This method returns the height (number of rows) of the board.
   *
   * @return The height of the board in pixels.
   */
  public int getHeight() {
    return this.imageGrid.length;
  }

  /**
   * Returns the pixel at a certain row and column.
   * 
   * @param row the row index of the pixel
   * @param col the column index of the pixel
   * @return the pixel at the specified row and column
   * @throws IllegalArgumentException if the pixel is out of bounds of the board
   */
  public AbstractPixel getPixel(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= imageGrid.length || col < 0 || col >= imageGrid[0].length) {
      throw new IllegalArgumentException("Invalid index");
    }
    return this.imageGrid[row][col];
  }

  /**
   * Returns the entire pixel board.
   * 
   * @return the two-dimensional array representing the pixel board
   */
  public AbstractPixel[][] getPixelBoard() {
    return this.imageGrid;
  }

  /**
   * Visualizes an individual color component from the picture.
   *
   * @param color the RGB value that we are visualizing
   * @throws IllegalArgumentException if the user inputs an invalid color
   */
  public void makeColorBoard(String color) throws IllegalArgumentException {
    // Create a new 2D array to store the color board
    AbstractPixel[][] colorBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];

    // Iterate over each pixel in the image grid
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        // Create a color pixel with the specified color and assign it to the
        // corresponding position in the color board
        colorBoard[row][col] = this.imageGrid[row][col].makeColorPixel(color);
      }
    }

    // Update the image grid with the newly created color board
    this.imageGrid = colorBoard;
  }

  /**
   * Checks if any of the image's edited versions correspond with user input image
   * name.
   *
   * @param currImageName name of the image that the user wants to perform
   *                      operations on
   * @return true if there is a version of the Pixel[][] that corresponds with
   *         user input, false otherwise
   */
  public boolean getPrevImages(String currImageName) {
    // Initialize the current image grid to null
    AbstractPixel[][] currImageGrid = null;

    // Iterate over each entry in the previous images map
    for (String imageName : this.prevImages.keySet()) {
      // Check if the current image name matches the user input (case-insensitive)
      if (imageName.equalsIgnoreCase(currImageName)) {
        // If there is a match, assign the corresponding image grid to the current image
        // grid variable and break out of the loop
        currImageGrid = prevImages.get(imageName);
        break;
      }
    }

    // Check if the current image grid is still null
    if (currImageGrid == null) {
      return false; // No matching image grid found
    } else {
      // Update the image grid with the current image grid
      this.imageGrid = currImageGrid;
      return true; // Matching image grid found
    }
  }

  /**
   * This method stores a version of the image after being modified by a command
   * from the controller.
   *
   * @param currImageName Reference name for the edited image (pixel array)
   */
  public void writePrevImages(String currImageName) {
    prevImages.put(currImageName, this.imageGrid);
  }

  /**
   * This method iterates through the image grid and assigns each pixel to its
   * vertical reflection.
   */
  public void flipVertically() {
    // Create a new 2D array to store the vertically flipped image
    AbstractPixel[][] verticalBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];

    // Iterate through the image grid
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        // Assign each pixel to its vertical reflection by flipping the row index
        verticalBoard[imageGrid.length - row - 1][col] = this.imageGrid[row][col];
      }
      System.out.print("\n");
    }

    // Update the image grid with the vertically flipped image
    this.imageGrid = verticalBoard;
  }

  /**
   * This method iterates through the image grid and assigns each pixel to its
   * horizontal reflection.
   */
  public void flipHorizontally() {
    // Create a new 2D array to store the horizontally flipped image
    AbstractPixel[][] horizontalBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];

    // Iterate through the image grid
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        // Assign each pixel to its horizontal reflection by flipping the column index
        horizontalBoard[row][imageGrid[0].length - col - 1] = this.imageGrid[row][col];
      }
    }

    // Update the image grid with the horizontally flipped image
    this.imageGrid = horizontalBoard;
  }

  /**
   * Adds a sharpen filter to the image board by applying a specific sharpen
   * kernel to each pixel
   * and its neighbors.
   */
  public void sharpenPicture() {
    // Define the sharpen kernel
    double[][] sharpen = new double[][] { { -(1.0 / 8), -(1.0 / 8), -(1.0 / 8), -(1.0 / 8),
        -(1.0 / 8) }, { -(1.0 / 8), (1.0 / 4), (1.0 / 4), (1.0 / 4), -(1.0 / 8) },
        { -(1.0 / 8), (1.0 / 4), (1.0), (1.0 / 4), -(1.0 / 8) },
        { -(1.0 / 8), (1.0 / 4), (1.0 / 4), (1.0 / 4), -(1.0 / 8) },
        { -(1.0 / 8), -(1.0 / 8), -(1.0 / 8), -(1.0 / 8), -(1.0 / 8) } };

    // Create a new board to hold the sharpened image
    AbstractPixel[][] sharpenBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];

    // Apply the sharpen filter to each pixel in the image grid
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        sharpenBoard[row][col] = contrastPixel(row, col, sharpen);
      }
    }

    // Update the image grid with the sharpened image
    this.imageGrid = sharpenBoard;
  }

  /**
   * Applies contrast to a pixel using a given kernel.
   *
   * @param row    The row index of the pixel.
   * @param col    The column index of the pixel.
   * @param kernel The kernel used to calculate the contrast.
   * @return The pixel with the contrast applied.
   */
  private AbstractPixel contrastPixel(int row, int col, double[][] kernel) {
    // Collect the neighbors of the pixel
    AbstractPixel[] neighbors = new AbstractPixel[kernel.length * kernel[0].length];

    // Initialize color channels
    double redChannel = 0;
    double greenChannel = 0;
    double blueChannel = 0;

    // Count of neighbors
    int count = 0;

    // Iterate over the kernel
    for (int i = -(kernel.length / 2); i <= (kernel.length / 2); i++) {
      for (int j = -(kernel.length / 2); j <= (kernel.length / 2); j++) {
        try {
          // Get the pixel at the specified position
          neighbors[count] = getPixel(row + i, col + j);
        } catch (IllegalArgumentException e) {
          // If the position is out of bounds, create a black pixel
          neighbors[count] = new AbstractPixel(0, 0, 0, this.getPixel(row, col).getAlpha());
        }
        count++;
      }
    }

    // Reset the counter variable
    count = 0;

    // Calculate the weighted sum of color channels
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        redChannel = redChannel + (neighbors[count].getColorValue("red") * kernel[i][j]);
        greenChannel = greenChannel + (neighbors[count].getColorValue("green") * kernel[i][j]);
        blueChannel = blueChannel + (neighbors[count].getColorValue("blue") * kernel[i][j]);

        count++;
      }
    }

    // Clamp the pixel values and create a new pixel
    AbstractPixel pixel = this.clampPixelValues((int) redChannel, (int) greenChannel,
        (int) blueChannel,
        imageGrid[row][col].getAlpha());
    return pixel;
  }

  /**
   * This method clamps pixel values so that they are no larger than 255 and
   * nonnegative.
   *
   * @param red   the red value of the pixel
   * @param green the green value of the pixel
   * @param blue  the blue value of the pixel
   * @param alpha the alpha (transparency) value of the pixel
   * @return a new pixel with the clamped color and alpha values.
   */
  private AbstractPixel clampPixelValues(int red, int green, int blue, int alpha) {
    return new AbstractPixel(clampColorValue(red), clampColorValue(green),
        clampColorValue(blue), alpha);
  }

  /**
   * This method clamps an individual color value.
   *
   * @param color the color value
   * @return the clamped color value.
   */
  private int clampColorValue(int color) {
    if (color > 255) {
      color = 255;
    } else if (color < 0) {
      color = 0;
    }
    return color;
  }

  /**
   * This method iterates through the board and brightens or darkens each pixel by
   * the luma factor.
   *
   * @param lumaFactor the degree to which the image is brightened or darkened
   *                   (positive = bright,
   *                   negative = dark)
   */
  public void changeBrightness(int lumaFactor) {
    // Create a new grid to store the modified pixels
    AbstractPixel[][] brightImageGrid = new AbstractPixel[imageGrid.length][imageGrid[0].length];

    // Iterate through each pixel in the image grid
    for (int row = 0; row < this.imageGrid.length; row++) {
      for (int col = 0; col < this.imageGrid[0].length; col++) {
        // Brighten or darken the pixel using the luma factor
        brightImageGrid[row][col] = this.imageGrid[row][col].changeBrightnessPixel(lumaFactor);
      }
    }

    // Update the image grid with the modified pixels
    this.imageGrid = brightImageGrid;
  }

  /**
   * This method adds a gaussian blur filter to the image board by applying a
   * specific blur kernel to each pixel
   * and its neighbors.
   */
  public void gaussianBlur() {
    // Define the blur kernel
    double[][] kernel = new double[][] { { (1.0 / 16), (1.0 / 8), (1.0 / 16) },
        { (1.0 / 8), (1.0 / 4), (1.0 / 8) },
        { (1.0 / 16), (1.0 / 8), (1.0 / 16) } };

    // Create a new grid to store the blurred pixels
    AbstractPixel[][] gaussianBlurBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];

    // Iterate through each pixel in the image grid
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        // Apply the blur kernel to the pixel and its neighbors
        gaussianBlurBoard[row][col] = contrastPixel(row, col, kernel);
      }
    }

    // Update the image grid with the blurred pixels
    this.imageGrid = gaussianBlurBoard;
  }

  /**
   * Adds a grayscale filter to the image board by applying a specific grayscale
   * kernel to
   * each pixel and its neighbors.
   */
  public void makeGrayscaleBoard() {
    // Create a new 2D array to hold the grayscale image
    AbstractPixel[][] grayscaleBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];

    // Define the grayscale kernel
    double[][] grayscale = new double[][] { { 0.2126, 0.7152, 0.0722 },
        { 0.2126, 0.7152, 0.0722 },
        { 0.2126, 0.7152, 0.0722 } };

    // Apply the grayscale kernel to each pixel in the image grid
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        grayscaleBoard[row][col] = this.imageGrid[row][col].createColorTonePixel(grayscale);
      }
    }

    // Update the image grid with the grayscale image
    this.imageGrid = grayscaleBoard;
  }

  /**
   * Adds a Sepia filter to the image board by applying a specific sepia kernel to
   * each pixel
   * and its neighbors.
   */
  public void makeSepiaBoard() {
    // Create a new 2D array to hold the sepia image
    AbstractPixel[][] sepiaBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];

    // Define the sepia kernel
    double[][] sepia = new double[][] { { 0.292, 0.769, 0.189 },
        { 0.349, 0.686, 0.168 },
        { 0.272, 0.534, 0.131 } };

    // Apply the sepia kernel to each pixel in the image grid
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        sepiaBoard[row][col] = this.imageGrid[row][col].createColorTonePixel(sepia);
      }
    }

    // Update the image grid with the sepia image
    this.imageGrid = sepiaBoard;
  }

  /**
   * Provides data for the R, G, or B frequency histogram.
   * 
   * @param color The color (R, G, B) that we want to get the frequency for.
   * @return An array representing the frequency of color values in the image.
   */
  public int[] makeHistogram(String color) {
    int[] freq = new int[256];

    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        freq[imageGrid[row][col].getColorValue(color)] += 1;
      }
    }
    return freq;
  }

  /**
   * Provides data for the intensity histogram.
   * 
   * @return An array representing the frequency of different luma factor values
   *         in the image.
   */
  public int[] makeBrightnessHistogram() {
    AbstractPixel[][] tempImageGrid = imageGrid;
    makeGrayscaleBoard();
    int[] freq = new int[256];
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        freq[imageGrid[row][col].getColorValue("red")] += 1;
      }
    }

    imageGrid = tempImageGrid;
    return freq;
  }

}